package com.github.nbyl.xfdcontrol.core.plugins;

import com.github.nbyl.xfdcontrol.core.status.JobStatusChangedEvent;

public interface NotificationPlugin {

    void start();

    void jobStatusChanged(JobStatusChangedEvent event);

    void shutdown();
}
