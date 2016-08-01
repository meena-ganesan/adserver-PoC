/*
 * Copyright (c) 2009 BarclaycardUS
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information of BarclaycardUS.
 * ("Confidential Information").
 */
package com.cim.adserver.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author mganesan
 */
public class HomeController {
    @RequestMapping("/")
    public String index() {
       return "redirect:/index.html";
    }
}
