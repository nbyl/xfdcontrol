package com.github.nbyl.xfdcontrol.service.jenkins;

import com.github.nbyl.xfdcontrol.core.status.JobStatus;
import com.github.nbyl.xfdcontrol.core.status.JobStatusEvent;

public class JenkinsJobStatusEvent extends JobStatusEvent {

    private final JenkinsJobStatus jobStatus;

    public JenkinsJobStatusEvent(Object source, JenkinsJobStatus jobStatus) {
        super(source);

        this.jobStatus = jobStatus;
    }

    @Override
    public JobStatus getStatus() {
        return this.jobStatus;
    }
}
