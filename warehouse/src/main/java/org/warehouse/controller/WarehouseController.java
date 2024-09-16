package org.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.warehouse.dto.CreateInboundDto;
import org.warehouse.dto.CreateOutboundDto;
import org.warehouse.dto.IWarehouseDto;
import org.warehouse.service.IOutboundService;

import java.util.List;

@Controller
@RequestMapping("/publish/")
public class WarehouseController {
    @Autowired
    private IOutboundService outboundService;
    @GetMapping("home")
    public String mainmenu(Model model) {
        List<IWarehouseDto> warehouseDtos = outboundService.getAllWarehouse();
        model.addAttribute("warehouses", warehouseDtos);
        return "mainpage";
    }
    @GetMapping("compare")
    public String compare(Model model) {
        List<IWarehouseDto> warehouseDtos = outboundService.getAllWarehouse();
        model.addAttribute("warehouses", warehouseDtos);
        return "compare";
    }
    @GetMapping("inbound")
    public String getFormCreateInbound(
            @RequestParam(
                    name = "id",
                    required = false,
                    defaultValue = "0") Integer id,
            @RequestParam(
                    name = "warehouse-id") Integer warehouseId,
            Model model) {
        model.addAttribute("warehouses",
                outboundService.getAllWarehouse());
        CreateInboundDto createInboundDto;
        if (id != 0) {
            createInboundDto = outboundService.getInboundCreate(id);
        } else {
            createInboundDto = new CreateInboundDto(warehouseId);
        }
        model.addAttribute("createInboundDto", createInboundDto);
        return "createInbound";
    }
    @GetMapping("outbound")
    public String getFormCreateOutbound(
            @RequestParam(
                    name = "id",
                    required = false,
                    defaultValue = "0") Integer id,
            @RequestParam(
                    name = "warehouse-id") Integer warehouseId,
            Model model) {
        model.addAttribute("warehouses",
                outboundService.getAllWarehouse());
        model.addAttribute("salers",
                outboundService.getAllSaler());
        CreateOutboundDto createOutboundDto;
        if (id != 0) {
            createOutboundDto = outboundService.getOutboundCreate(id);
        } else {
            createOutboundDto = new CreateOutboundDto(warehouseId);
        }
        model.addAttribute("createOutboundDto", createOutboundDto);
        return "createOutbound";
    }
    @PostMapping("inbound")
    public String createInbound(
            @ModelAttribute CreateInboundDto createInboundDto,
            Model model,
            BindingResult bindingResult) {
        createInboundDto.validate(createInboundDto, bindingResult);
        if (bindingResult.hasErrors()){
            model.addAttribute("warehouses",
                    outboundService.getAllWarehouse());
            return "createInbound";
        } else {
            outboundService.modifyInbound(createInboundDto);
            return "redirect:/publish/home";
        }
    }
    @PostMapping("outbound")
    public String createOutbound(
            @ModelAttribute CreateOutboundDto createOutboundDto,
            Model model,
            BindingResult bindingResult) {
        createOutboundDto.validate(createOutboundDto, bindingResult);
        if (bindingResult.hasErrors()){
            model.addAttribute("warehouses",
                    outboundService.getAllWarehouse());
            model.addAttribute("salers",
                    outboundService.getAllSaler());
            return "createOutbound";
        } else {
            outboundService.modifyOutbound(createOutboundDto);
            return "redirect:/publish/home";
        }
    }
//    @GetMapping("item")
//    public String getFormCreateItem(
//            @RequestParam(
//                    name = "id",
//                    required = false,
//                    defaultValue = "0") Integer id,
//            @RequestParam(
//                    name = "stock-id") Integer stockId,
//            Model model) {
//        ItemCreateDto itemCreateDto;
//        if (id != 0) {
//            itemCreateDto = stockNoteService.getItemCreate(id);
//        } else {
//            itemCreateDto = new ItemCreateDto(stockId);
//        }
//        model.addAttribute("itemCreateDto", itemCreateDto);
//        return "createItem";
//    }
//    @PostMapping("item")
//    public String createItem (
//            @ModelAttribute ItemCreateDto itemCreateDto,
//            BindingResult bindingResult) {
//        itemCreateDto.validate(itemCreateDto, bindingResult);
//        if (bindingResult.hasErrors()){
//            return "createItem";
//        } else {
//            stockNoteService.modifyItem(itemCreateDto);
//            return "redirect:/publish/home";
//        }
//    }
}
