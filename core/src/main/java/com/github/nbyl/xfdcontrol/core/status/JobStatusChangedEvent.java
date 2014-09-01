package com.github.nbyl.xfdcontrol.core.status;

import com.google.common.base.Optional;
import org.springframework.context.ApplicationEvent;

public class JobStatusChangedEvent extends ApplicationEvent {

    private final Optional<JobStatus> oldStatus;

    private final JobStatus newStatus;

    public JobStatusChangedEvent(Object source, JobStatus newStatus, Optional<JobStatus> oldStatus) {
        super(source);

        this.newStatus = newStatus;
        this.oldStatus = oldStatus;
    }

    public JobStatus getNewStatus() {
        return newStatus;
    }

    public Optional<JobStatus> getOldStatus() {
        return oldStatus;
    }
}
