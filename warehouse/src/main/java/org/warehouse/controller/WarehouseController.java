package org.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
//    @GetMapping("compare")
//    public String compare(Model model) {
//        List<IWarehouseDto> warehouseDtos = stockNoteService.getAllWarehouse();
//        model.addAttribute("warehouses", warehouseDtos);
//        return "compare";
//    }
//    @GetMapping("stock")
//    public String getFormCreateStock(
//            @RequestParam(
//                    name = "id",
//                    required = false,
//                    defaultValue = "0") Integer id,
//            @RequestParam(
//                    name = "warehouse-id") Integer warehouseId,
//            Model model) {
//        model.addAttribute("warehouses",
//                stockNoteService.getAllWarehouse());
//        model.addAttribute("salers",
//                stockNoteService.getAllSaler());
//        StockCreateDto stockCreateDto;
//        if (id != 0) {
//            stockCreateDto = stockNoteService.getStockCreate(id);
//        } else {
//            stockCreateDto = new StockCreateDto(warehouseId);
//        }
//        model.addAttribute("stockCreateDto", stockCreateDto);
//        return "createStock";
//    }
//    @PostMapping("stock")
//    public String createStock(
//            @ModelAttribute StockCreateDto stockCreateDto,
//            Model model,
//            BindingResult bindingResult) {
//        stockCreateDto.validate(stockCreateDto, bindingResult);
//        if (bindingResult.hasErrors()){
//            model.addAttribute("warehouses",
//                    stockNoteService.getAllWarehouse());
//            model.addAttribute("salers",
//                    stockNoteService.getAllSaler());
//            return "createStock";
//        } else {
//            stockNoteService.modifyStock(stockCreateDto);
//            return "redirect:/publish/home";
//        }
//    }
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
