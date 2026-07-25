package com.vivek.library.controller;

import com.vivek.library.dto.DashboardResponseDto;
import com.vivek.library.dto.OverdueBookResponseDto;
import com.vivek.library.service.BorrowService;
import com.vivek.library.service.DashboardService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@SecurityRequirement(name="bearerAuth")
public class DashboardController {
    private final DashboardService dashboardService;
    private final BorrowService borrowService;

    public DashboardController(DashboardService dashboardService, BorrowService borrowService) {
        this.dashboardService = dashboardService;
        this.borrowService = borrowService;
    }

    @GetMapping("/dashboard")
    public DashboardResponseDto getDashboardStatistics(){
        return dashboardService.getDashboardStatistics();
    }
    @GetMapping("/overdue-books")
    public Page<OverdueBookResponseDto> getOverdueBooks(@ParameterObject @PageableDefault(
            size = 10,
            sort = "dueDate",
            direction = Sort.Direction.ASC
    ) Pageable pageable){
        return borrowService.getOverdueBooks(pageable);
    }

}
