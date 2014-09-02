package com.github.nbyl.xfdcontrol.service.jenkins;

import org.apache.camel.Converter;

@Converter
public class JenkinsJobStatusEventConverter {

    @Converter
    public static JenkinsJobStatusEvent convertToEvent(JenkinsJobStatus jobStatus) {
        return new JenkinsJobStatusEvent(jobStatus, jobStatus);
    }
}
