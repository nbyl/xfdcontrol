package com.github.nbyl.xfdcontrol.core.plugins;

import com.github.nbyl.xfdcontrol.core.status.JobStatusChangedEvent;
import org.springframework.context.ApplicationListener;

public interface NotificationPlugin extends ApplicationListener<JobStatusChangedEvent> {
}
