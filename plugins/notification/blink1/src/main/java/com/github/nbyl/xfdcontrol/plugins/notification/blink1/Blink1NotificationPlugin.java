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

package com.github.nbyl.xfdcontrol.plugins.notification.blink1;

import com.github.nbyl.xfdcontrol.core.plugins.AbstractNotificationPlugin;
import com.github.nbyl.xfdcontrol.core.status.JobStatus;
import com.github.nbyl.xfdcontrol.core.status.JobStatusReceivedEvent;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.io.IOException;

@Component
public class Blink1NotificationPlugin extends AbstractNotificationPlugin {

    private static final Logger LOGGER = LoggerFactory.getLogger(Blink1NotificationPlugin.class);

    @Value("${blink1.tool.path}")
    private String blink1ToolPath;

    @Value("${blink1.statusChange.blinkCount:5}")
    private int statusChangeBlinkCount;

    private Optional<JobStatus> lastStatus = Optional.absent();

    @PostConstruct
    public void logValues() {
        LOGGER.debug("Using blink1 tool from {}", this.blink1ToolPath);
    }

    @Override
    protected void jobStatusReceived(JobStatusReceivedEvent event) {
        JobStatus status = event.getStatus();

        boolean changed = hasStatusChanged(status);
        Color statusColor = mapStatusToColor(status.getStatus());
        try {
            if (changed) {
                blink(statusColor);
                this.lastStatus = Optional.fromNullable(status);
            }
            changeToColor(statusColor);
        } catch (IOException e) {
            this.lastStatus = Optional.absent();
            LOGGER.error("There was an error updating the blink1.", e);
        }
    }

    private boolean hasStatusChanged(JobStatus status) {
        return !this.lastStatus.isPresent() || (this.lastStatus.isPresent() && !this.lastStatus.get().equals(status));
    }

    private void blink(Color statusColor) throws IOException {
        new Blink1ToolCommand(this.blink1ToolPath)
                .setColor(statusColor)
                .setBlinkCount(this.statusChangeBlinkCount).run();
    }

    private void changeToColor(Color statusColor) throws IOException {
        new Blink1ToolCommand(this.blink1ToolPath)
                .setColor(statusColor).run();
    }

    @VisibleForTesting
    Color mapStatusToColor(JobStatus.Status status) {
        switch (status) {
            case SUCCESS:
                return Color.green;
            case TESTS_FAILING:
                return Color.YELLOW;
            case FAILED:
                return Color.RED;
        }

        return Color.ORANGE;
    }
}
