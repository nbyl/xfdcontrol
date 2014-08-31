package com.github.nbyl.xfdcontrol.core.status;

import com.google.common.base.Optional;

public class JobStatusChangedEvent  {

    private final Optional<JobStatus> oldStatus;

    private final JobStatus newStatus;

    public JobStatusChangedEvent(JobStatus newStatus, Optional<JobStatus> oldStatus) {
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
