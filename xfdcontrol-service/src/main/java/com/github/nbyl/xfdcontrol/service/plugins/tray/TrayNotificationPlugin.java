package com.github.nbyl.xfdcontrol.service.plugins.tray;

import com.github.nbyl.xfdcontrol.service.JobStatus;
import com.github.nbyl.xfdcontrol.service.JobStatusChangedEvent;
import com.github.nbyl.xfdcontrol.service.NotificationPlugin;
import org.springframework.stereotype.Component;

@Component
public class TrayNotificationPlugin implements NotificationPlugin {

    @Override
    public void start() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void jobStatusChanged(JobStatusChangedEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void shutdown() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
