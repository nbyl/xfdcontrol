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

import com.google.common.annotations.VisibleForTesting;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.IOException;

public class Blink1ToolCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(Blink1ToolCommand.class);

    private final CommandLine commandLine;

    public Blink1ToolCommand(String blink1ToolPath) {
        this.commandLine = new CommandLine(blink1ToolPath);
    }

    public Blink1ToolCommand setColor(Color color) {
        this.commandLine.addArgument("--rgb");
        this.commandLine.addArgument(mapColorToRgbArgument(color));
        return this;
    }

    public Blink1ToolCommand setBlinkCount(Integer blinkCount) {
        this.commandLine.addArgument("--blink");
        this.commandLine.addArgument(blinkCount.toString());
        return this;
    }

    public void run() throws IOException {
        LOGGER.debug("Running command: {}", this.commandLine.toString());

        DefaultExecutor executor = new DefaultExecutor();
        executor.execute(this.commandLine);
    }

    @VisibleForTesting
    String mapColorToRgbArgument(Color color) {
        return color.getRed() + "," + color.getGreen() + "," + color.getBlue();
    }
}
