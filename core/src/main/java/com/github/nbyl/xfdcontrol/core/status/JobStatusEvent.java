package com.github.nbyl.xfdcontrol.core.status;

import org.springframework.context.ApplicationEvent;

public abstract class JobStatusEvent extends ApplicationEvent {

    public JobStatusEvent(Object source) {
        super(source);
    }

    public abstract JobStatus getStatus();
}
