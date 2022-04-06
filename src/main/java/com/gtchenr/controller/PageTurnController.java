package com.gtchenr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageTurnController {

    @RequestMapping("home")
    public ModelAndView navigateToHome() {
        System.out.println("navigateToHome--------");
        return new ModelAndView("home");
    }

    @RequestMapping("reportDetail")
    public ModelAndView navigateToReportDetail() {
        System.out.println("navigateToReportDetail--------");
        return new ModelAndView("reportDetail");
    }
}
