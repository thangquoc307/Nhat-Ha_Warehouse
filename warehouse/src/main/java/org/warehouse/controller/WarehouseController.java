package org.warehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/publish/")
public class WarehouseController {
    @GetMapping("home")
    public String mainmenu() {

        return "index";
    }

}
