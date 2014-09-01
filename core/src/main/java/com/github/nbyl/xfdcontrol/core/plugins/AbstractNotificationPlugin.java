package com.github.nbyl.xfdcontrol.core.plugins;

import com.github.nbyl.xfdcontrol.core.status.JobStatusChangedEvent;

public abstract class AbstractNotificationPlugin implements NotificationPlugin {
    @Override
    public void onApplicationEvent(JobStatusChangedEvent event) {
        // TODO: check for enabled here
        this.jobStatusChanged(event);
    }

    protected abstract void jobStatusChanged(JobStatusChangedEvent event);
}