package com.appbuilder.appbuilder.services.impl;

import com.appbuilder.appbuilder.dto.member.InviteMemberRequestDto;
import com.appbuilder.appbuilder.dto.member.MemberResponseDto;
import com.appbuilder.appbuilder.dto.member.UpdateMemberRoleRequestDto;
import com.appbuilder.appbuilder.entity.ProjectEntity;
import com.appbuilder.appbuilder.entity.ProjectMemberEntity;
import com.appbuilder.appbuilder.entity.ProjectMemberId;
import com.appbuilder.appbuilder.entity.UserEntity;
import com.appbuilder.appbuilder.exceptions.BadRequestException;
import com.appbuilder.appbuilder.exceptions.ResourceNotFoundException;
import com.appbuilder.appbuilder.repository.ProjectMemberRepository;
import com.appbuilder.appbuilder.repository.ProjectRepository;
import com.appbuilder.appbuilder.repository.UserRepository;
import com.appbuilder.appbuilder.services.ProjectMemberService;
import com.appbuilder.appbuilder.utils.constants.ErrorMessageConstants;
import com.appbuilder.appbuilder.utils.mappers.ProjectMemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {

    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectMemberMapper projectMemberMapper;
    private final UserRepository userRepository;


    private ProjectEntity getProjectEntityForUserId(String projectId,String userId) {
        return projectRepository.findProjectByProjectIdAndUserID(projectId,userId).orElseThrow(
                () -> new ResourceNotFoundException("Project not found")
        );
    }



    @Override
    public List<MemberResponseDto> getProjectMembers(String projectId, String userId) {

        ArrayList<MemberResponseDto> members = new ArrayList<>();
        final ProjectEntity projectEntity= getProjectEntityForUserId(projectId,userId);

        members.add(projectMemberMapper.fromProjectMemberEntity(projectEntity));

        members.addAll(projectMemberRepository.getAllProjectMemberByProjectId(projectId).stream().map(
                projectMemberMapper::fromProjectMemberEntity
        ).toList());

       return members;
    }

    @Override
    public MemberResponseDto inviteMember(String projectId, InviteMemberRequestDto inviteMemberRequestDto, String userId) {
        final ProjectEntity projectEntity= getProjectEntityForUserId(projectId,userId);

        if(!projectEntity.getOwner().getId().equals(userId)) {
            throw new BadRequestException(ErrorMessageConstants.ONLY_OWNER_CAN_INVITE_MEMBER);
        }

        final UserEntity userToInvite=userRepository.findByEmail(inviteMemberRequestDto.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException(ErrorMessageConstants.INVITE_USER_NOT_FOUND)
        );

        if(userToInvite.getId().equals(userId)) {
            throw new ResourceNotFoundException(ErrorMessageConstants.OWNER_CANNOT_INVITE_SELF);
        }

        if(projectMemberRepository.findByEmail(projectId,inviteMemberRequestDto.getEmail()).isPresent()) {
            throw new ResourceNotFoundException(ErrorMessageConstants.PROJECT_ALREADY_INVITED);
        }
        final ProjectMemberEntity projectMemberEntity=new ProjectMemberEntity();
        projectMemberEntity.setProjectEntity(projectEntity);
        projectMemberEntity.setUserEntity(userToInvite);
        projectMemberEntity.setProjectRole(inviteMemberRequestDto.getRole());
        projectMemberEntity.setInvitedAt(LocalDateTime.now());
        final ProjectMemberEntity savedProjectMemberEntity = projectMemberRepository.save(projectMemberEntity);
        return projectMemberMapper.fromProjectMemberEntity(savedProjectMemberEntity);
    }

    @Override
    public MemberResponseDto updateMemberRole(String projectId, String memberId, UpdateMemberRoleRequestDto inviteMemberRequestDto, String userId) {
        final ProjectEntity projectEntity= getProjectEntityForUserId(projectId,userId);
        if(!projectEntity.getOwner().getId().equals(userId)) {
            throw new ResourceNotFoundException(ErrorMessageConstants.ONLY_OWNER_CAN_UPDATE_MEMBER);
        }
        final ProjectMemberId projectMemberId=new ProjectMemberId(projectId, memberId);
        final ProjectMemberEntity projectMemberEntity=projectMemberRepository.findById(projectMemberId).orElseThrow(
                () ->  new ResourceNotFoundException(ErrorMessageConstants.PROJECT_NOT_FOUND)
        );
        projectMemberEntity.setProjectRole(inviteMemberRequestDto.getRole());
        final ProjectMemberEntity savedProjectMemberEntity = projectMemberRepository.save(projectMemberEntity);
        return projectMemberMapper.fromProjectMemberEntity(savedProjectMemberEntity);
    }

    @Override
    public MemberResponseDto deleteProjectMember(String projectId, String memberId, String userId) {
        final ProjectEntity projectEntity= getProjectEntityForUserId(projectId,userId);
        if(!projectEntity.getOwner().getId().equals(userId)) {
            throw new ResourceNotFoundException(ErrorMessageConstants.ONLY_OWNER_CAN_DELETE_MEMBER);
        }
        if(memberId.equals(userId)) {
            throw new RuntimeException(ErrorMessageConstants.OWNER_CANNOT_BE_DELETED);
        }
        final ProjectMemberId projectMemberId=new ProjectMemberId(projectId, memberId);
        final ProjectMemberEntity projectMemberEntity=projectMemberRepository.findById(projectMemberId).orElseThrow(
                () ->  new ResourceNotFoundException(ErrorMessageConstants.PROJECT_NOT_FOUND)
        );
        projectMemberRepository.delete(projectMemberEntity);
        return projectMemberMapper.fromProjectMemberEntity(projectMemberEntity);
    }
}
