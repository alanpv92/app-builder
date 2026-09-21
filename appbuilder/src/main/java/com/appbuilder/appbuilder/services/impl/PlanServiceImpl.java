package com.appbuilder.appbuilder.services.impl;

import com.appbuilder.appbuilder.dto.subscription.PlanRequestDto;
import com.appbuilder.appbuilder.dto.subscription.PlanResponseDto;
import com.appbuilder.appbuilder.entity.PlanEntity;
import com.appbuilder.appbuilder.entity.UserEntity;
import com.appbuilder.appbuilder.entity.enums.SystemRole;
import com.appbuilder.appbuilder.exceptions.BadRequestException;
import com.appbuilder.appbuilder.exceptions.UnAuthorizedAccessException;
import com.appbuilder.appbuilder.repository.PlanRepository;
import com.appbuilder.appbuilder.repository.UserRepository;
import com.appbuilder.appbuilder.services.PlanService;
import com.appbuilder.appbuilder.utils.constants.ErrorMessageConstants;
import com.appbuilder.appbuilder.utils.helpers.SecurityHelper;
import com.appbuilder.appbuilder.utils.mappers.PlanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanMapper planMapper;
    private final UserRepository userRepository;
    private final PlanRepository planRepository;

    @Override
    public List<PlanResponseDto> getAllActivePlans() {
        return planRepository.findAll().stream().map(planMapper::fromPlanEntity).toList();
    }

    @Override
    public PlanResponseDto addPlan(PlanRequestDto planRequestDto) {

        final String userId=SecurityHelper.getId();
        final UserEntity adminUser=userRepository.findById(userId).orElseThrow(
                () -> new BadRequestException(ErrorMessageConstants.USER_NOT_FOUND)
        );

        if(adminUser.getSystemRole() != SystemRole.ADMIN) {
            throw  new UnAuthorizedAccessException(ErrorMessageConstants.ONLY_ADMIN_CAN_CREATE_PLAN);
        }

        final PlanEntity planEntity=planMapper.fromPlanRequestDto(planRequestDto);
        final PlanEntity savedPlanEntity=planRepository.save(planEntity);
        return planMapper.fromPlanEntity(savedPlanEntity);

    }


}
