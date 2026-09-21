package com.appbuilder.appbuilder.utils.mappers;

import com.appbuilder.appbuilder.dto.subscription.PlanRequestDto;
import com.appbuilder.appbuilder.dto.subscription.PlanResponseDto;
import com.appbuilder.appbuilder.entity.PlanEntity;
import org.springframework.stereotype.Component;

@Component
public class PlanMapper {

   public PlanEntity fromPlanRequestDto(PlanRequestDto planRequestDto) {
        final PlanEntity planEntity = new PlanEntity();
        planEntity.setName(planRequestDto.getName());
        planEntity.setActive(true);
        planEntity.setName(planRequestDto.getName());
        planEntity.setMaxProjects(planRequestDto.getMaxProjects());
        planEntity.setMaxPreviews(planRequestDto.getMaxPreviews());
        planEntity.setMaxTokensPerDay(planRequestDto.getMaxTokensPerDay());
        planEntity.setStripePriceId(planRequestDto.getPriceId());
        planEntity.setUnlimitedAi(planRequestDto.getUnlimitedAi());
        return planEntity;
    }

   public PlanResponseDto fromPlanEntity(PlanEntity planEntity) {
        return  PlanResponseDto.builder()
                .price(planEntity.getStripePriceId())
                .unlimitedAi(planEntity.getUnlimitedAi())
                .name(planEntity.getName())
                .id(planEntity.getId())
                .maxProjects(planEntity.getMaxProjects())
                .maxTokensPerDay(planEntity.getMaxTokensPerDay())
                .build();
    }
}
