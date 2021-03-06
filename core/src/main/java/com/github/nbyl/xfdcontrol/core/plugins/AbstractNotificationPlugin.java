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

package com.github.nbyl.xfdcontrol.core.plugins;

import com.github.nbyl.xfdcontrol.core.status.JobStatusChangedEvent;
import com.github.nbyl.xfdcontrol.core.status.JobStatusEventBase;
import com.github.nbyl.xfdcontrol.core.status.JobStatusReceivedEvent;

public abstract class AbstractNotificationPlugin implements NotificationPlugin {
    @Override
    public void onApplicationEvent(JobStatusEventBase event) {
        // TODO: check for enabled here
        if (event instanceof JobStatusChangedEvent) {
            this.jobStatusChanged((JobStatusChangedEvent) event);
        } else if (event instanceof JobStatusReceivedEvent) {
            this.jobStatusReceived((JobStatusReceivedEvent) event);
        } else {
            throw new IllegalArgumentException("Not valid job status class: " + event.getClass().toString());
        }
    }

    protected void jobStatusChanged(JobStatusChangedEvent event) {
    }

    protected void jobStatusReceived(JobStatusReceivedEvent event) {
    }
}