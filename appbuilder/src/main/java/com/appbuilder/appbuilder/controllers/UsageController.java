package com.appbuilder.appbuilder.controllers;


import com.appbuilder.appbuilder.dto.subscription.PlanLimitsResponseDto;
import com.appbuilder.appbuilder.dto.subscription.UsageTodayResponseDto;
import com.appbuilder.appbuilder.services.UsageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usage")
public class UsageController {

    private final UsageService usageService;

    @GetMapping("/today")
    public ResponseEntity<UsageTodayResponseDto> getTodayUsage() {
        String userId = "1L";
        return ResponseEntity.ok(usageService.getTodayUsageOfUser(userId));
    }

    @GetMapping("/limits")
    public ResponseEntity<PlanLimitsResponseDto> getPlanLimits() {
        String userId = "1L";
        return ResponseEntity.ok(usageService.getCurrentSubscriptionLimitsOfUser(userId));
    }
}
