package org.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.warehouse.dto.CreateInboundDto;
import org.warehouse.dto.CreateOutboundDto;
import org.warehouse.dto.IWarehouseDto;
import org.warehouse.dto.ItemCreateDto;
import org.warehouse.service.IOutboundService;

import java.util.List;

@Controller
@RequestMapping("/publish/")
public class WarehouseController {
    @Autowired
    private IOutboundService outboundService;
    @GetMapping("home")
    public String mainmenu(
            @RequestParam(
                    required = false,
                    defaultValue = "1") Integer isInbound,
            Model model
    ) {
        List<IWarehouseDto> warehouseDtos = outboundService.getAllWarehouse();
        model.addAttribute("warehouses", warehouseDtos);
        model.addAttribute("isInbound", isInbound);
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
            return "redirect:/publish/home?isInbound=1";
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
            return "redirect:/publish/home?isInbound=0";
        }
    }
    @GetMapping("item")
    public String getFormCreateInboundItem(
            @RequestParam(
                    name = "id",
                    required = false,
                    defaultValue = "0") Integer id,
            @RequestParam(
                    name = "stock-id") Integer stockId,
            @RequestParam(
                    name = "is-inbound") int isInbound,
            Model model) {
        ItemCreateDto itemCreateDto;
        if (id != 0) {
            itemCreateDto = outboundService.getItemCreate(id, isInbound == 1);
        } else {
            itemCreateDto = new ItemCreateDto(stockId, isInbound);
        }
        model.addAttribute("itemCreateDto", itemCreateDto);
        model.addAttribute(
                "manufacturers", outboundService.getAllManufacturer());
        return "createItem";
    }
    @PostMapping("item")
    public String createItem (
            @ModelAttribute ItemCreateDto itemCreateDto,
            BindingResult bindingResult, Model model) {
        itemCreateDto.validate(itemCreateDto, bindingResult);
        if (bindingResult.hasErrors()){
            model.addAttribute(
                    "manufacturers", outboundService.getAllManufacturer());
            return "createItem";
        } else {
            outboundService.modifyItem(itemCreateDto);
            if (itemCreateDto.getIsInbound() == 0) {
                return "redirect:/publish/home?isInbound=0";
            } else {
                return "redirect:/publish/home?isInbound=1";
            }
        }
    }
}
