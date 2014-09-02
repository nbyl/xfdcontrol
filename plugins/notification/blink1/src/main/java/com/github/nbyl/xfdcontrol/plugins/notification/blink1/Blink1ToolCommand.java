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
