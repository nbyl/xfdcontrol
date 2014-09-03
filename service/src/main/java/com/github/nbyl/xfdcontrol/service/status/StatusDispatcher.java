/*
 * Copyright 2014 Nicolas Byl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.nbyl.xfdcontrol.service.status;

import com.github.nbyl.xfdcontrol.core.status.JobStatus;
import com.github.nbyl.xfdcontrol.core.status.JobStatusChangedEvent;
import com.github.nbyl.xfdcontrol.core.status.JobStatusEvent;
import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StatusDispatcher implements ApplicationListener<JobStatusEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusDispatcher.class);

    @Autowired
    private ApplicationEventPublisher publisher;

    private Optional<JobStatus> lastStatus;

    public StatusDispatcher() {
        this.lastStatus = Optional.absent();
    }

    @Override
    public void onApplicationEvent(JobStatusEvent event) {
        JobStatus jobStatus = event.getStatus();
        LOGGER.debug("Got status: {}", event.getStatus());

        if (!this.lastStatus.isPresent() || !this.lastStatus.get().equals(jobStatus)) {
            JobStatusChangedEvent changedEvent = new JobStatusChangedEvent(this, jobStatus, this.lastStatus);
            this.lastStatus = Optional.fromNullable(jobStatus);

            this.publisher.publishEvent(changedEvent);
        }
    }
}
