package com.github.nbyl.xfdcontrol.plugins.notification.tray;

import com.github.nbyl.xfdcontrol.core.plugins.AbstractNotificationPlugin;
import com.github.nbyl.xfdcontrol.core.status.JobStatusChangedEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TrayNotificationPlugin extends AbstractNotificationPlugin {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrayNotificationPlugin.class);

    @Override
    public void jobStatusChanged(JobStatusChangedEvent event) {
        LOGGER.info("Job status changed to " + event.getNewStatus());
    }
}
