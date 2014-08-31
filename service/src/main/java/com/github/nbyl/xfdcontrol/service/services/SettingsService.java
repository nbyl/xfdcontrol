package com.github.nbyl.xfdcontrol.service.services;

import com.github.nbyl.xfdcontrol.service.Settings;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {

    private Settings settings;

    public SettingsService() {
        this.settings = new Settings();

        this.settings.setJenkinsUrl("https://buildhive.cloudbees.com/view/My%20Repositories/job/nbyl/job/xfdcontrol");
    }

    public Settings getSettings() {
        return this.settings;
    }

    public void saveSettings(Settings settings) {
        this.settings = settings;
    }
}
