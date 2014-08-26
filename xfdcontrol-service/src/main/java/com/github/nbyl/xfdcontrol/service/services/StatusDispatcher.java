package com.github.nbyl.xfdcontrol.service.services;

import com.github.nbyl.xfdcontrol.service.JobStatus;
import com.github.nbyl.xfdcontrol.service.JobStatusChangedEvent;
import com.github.nbyl.xfdcontrol.service.NotificationPlugin;
import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatusDispatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusDispatcher.class);

    private final List<NotificationPlugin> plugins;

    private Optional<JobStatus> lastStatus;

    @Autowired
    public StatusDispatcher(List<NotificationPlugin> plugins) {
        this.plugins = plugins;
        this.lastStatus = Optional.absent();
    }

    public void dispatchStatus(JobStatus status) {
        LOGGER.info("Got status: {}", status);
        if (!this.lastStatus.isPresent() || !this.lastStatus.get().equals(status)) {
            JobStatusChangedEvent event = new JobStatusChangedEvent(this, status, this.lastStatus);
            this.lastStatus = Optional.fromNullable(status);

            fireJobStatusChanged(event);
        }
    }

    private void fireJobStatusChanged(JobStatusChangedEvent event) {
        for (NotificationPlugin plugin : this.plugins) {
            plugin.jobStatusChanged(event);
        }
    }
}
