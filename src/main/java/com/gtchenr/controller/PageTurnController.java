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
        return new ModelAndView("reportDetail1");
    }

    @RequestMapping("publishReport")
    public ModelAndView navigateToPublishReport() {
        System.out.println("navigateToPublishReport-------------");
        return new ModelAndView("publishReport1");
    }

    @RequestMapping("admin")
    public ModelAndView navigateToAdmin(){
        System.out.println("navigateToAdmin");
        return new ModelAndView("admin");
    }

    @RequestMapping("adminLogin")
    public ModelAndView navigateToAdminLogin(){
        System.out.println("navigateToAdminLogin");
        return new ModelAndView("adminLogin");
    }

    @RequestMapping("personal")
    public ModelAndView navigateToPersonal(){
        System.out.println("navigateToAdminLogin");
        return new ModelAndView("personalHome");
    }

    @RequestMapping("login")
    public ModelAndView navigateToLogin(){
        System.out.println("navigateToLogin");
        return new ModelAndView("login");
    }

    @RequestMapping("register")
    public ModelAndView navigateToRegister(){
        System.out.println("navigateToRegister");
        return new ModelAndView("register");
    }

    @RequestMapping("search")
    public ModelAndView navigateToSearch(){
        System.out.println("navigateToSearch");
        return new ModelAndView("search");
    }
}
