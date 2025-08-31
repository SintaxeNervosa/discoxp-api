package com.github.sintaxenervosa.discoxp.controller;

import com.github.sintaxenervosa.discoxp.dto.stockist.StockistRequestDto;
import com.github.sintaxenervosa.discoxp.service.AdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    public AdminController(AdminService  adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public void registerStockist(@RequestBody StockistRequestDto stockist) {
        try {
            adminService.createStockist(stockist);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
