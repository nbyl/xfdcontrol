package com.github.nbyl.xfdcontrol.service.web;

import com.github.nbyl.xfdcontrol.service.Settings;
import com.github.nbyl.xfdcontrol.service.services.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SettingsController {

    @Autowired
    private SettingsService service;

    @RequestMapping("/settings")
    public ModelAndView showConfiguration() {
        Settings settings = this.service.getSettings();
        return new ModelAndView("settings", "settings", settings);
    }

    @RequestMapping(value = "/settings", method = RequestMethod.POST)
    public ModelAndView updateSettings(Settings settings, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView("settings", "errors", result.getAllErrors());
        }

        redirect.addFlashAttribute("globalFlashMessage", "Your settings has been saved.");
        return new ModelAndView("redirect:/settings");
    }
}
