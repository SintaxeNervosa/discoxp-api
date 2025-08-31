package com.github.sintaxenervosa.discoxp.controller;

import com.github.sintaxenervosa.discoxp.dto.stockist.StockistRequestDto;
import com.github.sintaxenervosa.discoxp.model.Stockist;
import com.github.sintaxenervosa.discoxp.model.User;
import com.github.sintaxenervosa.discoxp.service.AdminService;
import com.github.sintaxenervosa.discoxp.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;
    public AdminController(AdminService  adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public void registerUser(@RequestBody StockistRequestDto  stockist) {
        adminService.create(stockist);
    }
}
