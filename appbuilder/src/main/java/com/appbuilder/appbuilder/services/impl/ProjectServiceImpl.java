package com.appbuilder.appbuilder.services.impl;

import com.appbuilder.appbuilder.dto.auth.UserProfileResponseDto;
import com.appbuilder.appbuilder.dto.project.ProjectCreationRequestDto;
import com.appbuilder.appbuilder.dto.project.ProjectResponseDto;
import com.appbuilder.appbuilder.dto.project.ProjectSummaryResponseDto;
import com.appbuilder.appbuilder.entity.ProjectEntity;
import com.appbuilder.appbuilder.entity.ProjectMemberEntity;
import com.appbuilder.appbuilder.entity.SubscriptionEntity;
import com.appbuilder.appbuilder.entity.UserEntity;
import com.appbuilder.appbuilder.entity.enums.ProjectRole;
import com.appbuilder.appbuilder.exceptions.BadRequestException;
import com.appbuilder.appbuilder.exceptions.ResourceNotFoundException;
import com.appbuilder.appbuilder.repository.ProjectMemberRepository;
import com.appbuilder.appbuilder.repository.ProjectRepository;
import com.appbuilder.appbuilder.repository.SubscriptionRepository;
import com.appbuilder.appbuilder.repository.UserRepository;
import com.appbuilder.appbuilder.services.ProjectService;
import com.appbuilder.appbuilder.utils.constants.ErrorMessageConstants;
import com.appbuilder.appbuilder.utils.mappers.ProjectMapper;
import com.appbuilder.appbuilder.utils.mappers.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

   private final ProjectRepository projectRepository;
   private final ProjectMemberRepository projectMemberRepository;
   private final UserRepository userRepository;
   private final SubscriptionRepository subscriptionRepository;
   private final ProjectMapper projectMapper;
   private final UserMapper userMapper;

   private UserEntity getUserEntity(String userId) {
       return  userRepository.findById(userId).orElseThrow(
               () -> new ResourceNotFoundException(ErrorMessageConstants.USER_NOT_FOUND)
       );
   }

   private ProjectEntity getProjectEntityForUserId(String projectId,String userId) {
      return projectRepository.findProjectByProjectIdAndUserID(projectId,userId).orElseThrow(
              () -> new ResourceNotFoundException("Project not found")
      );
   }

    @Override
    public List<ProjectSummaryResponseDto> getUserProjects(String userId) {
        final UserEntity user= getUserEntity(userId);
        final List<ProjectEntity> projectEntities=projectRepository.findAllForUser(userId);
        return projectEntities.stream().map(
                projectMapper::fromProjectEntity
        ).toList();
    }

    @Override
    public ProjectResponseDto getUserProjectById(String projectId, String userId) {
        final UserEntity user= getUserEntity(userId);
        final ProjectEntity projectEntity = getProjectEntityForUserId(projectId,userId);
        final UserProfileResponseDto userProfileResponseDto=userMapper.fromUserEntity(user);
        return projectMapper.fromProjectEntity(projectEntity,userProfileResponseDto);
    }




    void validateSubscription(String userId) {
       final SubscriptionEntity subscriptionEntity= subscriptionRepository.findByUserId(userId).orElseThrow(
               () -> new BadRequestException(ErrorMessageConstants.PROJECT_OPERATION_SUBSCRIPTION_NOT_FOUND)
       );

       if(projectRepository.findAllForUser(userId).size()>subscriptionEntity.getPlan().getMaxProjects()){
           throw new BadRequestException(ErrorMessageConstants.PROJECT_OPERATION_LIMIT);
       }
    }



    @Transactional
    @Override
    public ProjectResponseDto createProject(ProjectCreationRequestDto projectCreationRequestDto, String userId) {
       //creating project
       //assign user as owner for the project
       //also creating a project member entry


       //before creating check if user is valid
        // if user if vaild check if subscripton is there
        //if subscription is active then check the lenght of projects



        final UserEntity user= getUserEntity(userId);

        validateSubscription(userId);

        final ProjectEntity projectEntity = projectMapper.fromProjectCreationRequestDto(projectCreationRequestDto, user);
        final ProjectEntity savedProjectEntity =projectRepository.save(projectEntity);
        final ProjectMemberEntity projectMemberEntity=new ProjectMemberEntity();
        projectMemberEntity.setProjectEntity(projectEntity);
        projectMemberEntity.setUserEntity(user);
        projectMemberEntity.setProjectRole(ProjectRole.OWNER);
        projectMemberEntity.setInvitedAt(LocalDateTime.now());
        projectMemberRepository.save(projectMemberEntity);
        final UserProfileResponseDto userProfileResponseDto=userMapper.fromUserEntity(user);
        return projectMapper.fromProjectEntity(savedProjectEntity,userProfileResponseDto);
    }

    @Override
    public ProjectResponseDto updateProject(String projectId, ProjectCreationRequestDto request, String userId) {
        final UserEntity user=getUserEntity(userId);
        final ProjectEntity projectEntity = getProjectEntityForUserId(projectId,userId);
        projectEntity.setName(request.getName());
        final ProjectEntity updatedProjectEntity= projectRepository.save(projectEntity);
        return projectMapper.fromProjectEntity(updatedProjectEntity,userMapper.fromUserEntity(user));

    }

    @Override
    public void softDelete(String projectId, String userId) {
        final UserEntity user= getUserEntity(userId);
        final ProjectEntity projectEntity =getProjectEntityForUserId(projectId,userId);
        if(!projectEntity.getOwner().getId().equals(user.getId())) {
            throw new BadRequestException(ErrorMessageConstants.PROJECT_ACCESS_DENIED_FOR_NON_OWNER);
        }
        projectEntity.setDeletedAt(LocalDateTime.now());
        projectRepository.save(projectEntity);
    }


}
