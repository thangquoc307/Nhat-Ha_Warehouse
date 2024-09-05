package org.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.warehouse.dto.IWarehouseDto;
import org.warehouse.service.IStockNoteService;

import java.util.List;

@Controller
@RequestMapping("/publish/")
public class WarehouseController {
    @Autowired
    private IStockNoteService stockNoteService;
    @GetMapping("home")
    public String mainmenu(Model model) {
        List<IWarehouseDto> warehouseDtos = stockNoteService.getAllWarehouse();
        model.addAttribute("warehouses", warehouseDtos);
        return "mainpage";
    }
    @GetMapping("compare")
    public String compare(Model model) {
        List<IWarehouseDto> warehouseDtos = stockNoteService.getAllWarehouse();
        model.addAttribute("warehouses", warehouseDtos);
        return "compare";
    }

}
