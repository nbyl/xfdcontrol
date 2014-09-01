package com.github.nbyl.xfdcontrol.service.services;

import com.github.nbyl.xfdcontrol.core.plugins.NotificationPlugin;
import com.github.nbyl.xfdcontrol.core.status.JobStatus;
import com.github.nbyl.xfdcontrol.core.status.JobStatusChangedEvent;
import com.github.nbyl.xfdcontrol.core.status.JobStatusEvent;
import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatusDispatcher implements ApplicationListener<JobStatusEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusDispatcher.class);

    @Autowired(required = false)
    private List<NotificationPlugin> plugins;

    private Optional<JobStatus> lastStatus;

    public StatusDispatcher() {
        this.lastStatus = Optional.absent();
    }

    public void dispatchStatus(JobStatus status) {
        LOGGER.info("Got status: {}", status);
        if (!this.lastStatus.isPresent() || !this.lastStatus.get().equals(status)) {
            JobStatusChangedEvent event = new JobStatusChangedEvent(status, this.lastStatus);
            this.lastStatus = Optional.fromNullable(status);

            fireJobStatusChanged(event);
        }
    }

    private void fireJobStatusChanged(JobStatusChangedEvent event) {
        if (this.plugins != null) {
            for (NotificationPlugin plugin : this.plugins) {
                plugin.jobStatusChanged(event);
            }
        }
    }

    @Override
    public void onApplicationEvent(JobStatusEvent event) {
        LOGGER.info("Got status: {}", event.getStatus());
    }
}
