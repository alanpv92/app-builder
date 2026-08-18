package com.appbuilder.appbuilder.services.impl;

import com.appbuilder.appbuilder.dto.member.InviteMemberRequestDto;
import com.appbuilder.appbuilder.dto.member.MemberResponseDto;
import com.appbuilder.appbuilder.dto.member.UpdateMemberRoleRequestDto;
import com.appbuilder.appbuilder.entity.ProjectEntity;
import com.appbuilder.appbuilder.entity.ProjectMemberEntity;
import com.appbuilder.appbuilder.entity.ProjectMemberId;
import com.appbuilder.appbuilder.entity.UserEntity;
import com.appbuilder.appbuilder.repository.ProjectMemberRepository;
import com.appbuilder.appbuilder.repository.ProjectRepository;
import com.appbuilder.appbuilder.repository.UserRepository;
import com.appbuilder.appbuilder.services.ProjectMemberService;
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

    @Override
    public List<MemberResponseDto> getProjectMembers(String projectId, String userId) {

        ArrayList<MemberResponseDto> members = new ArrayList<>();
        final ProjectEntity projectEntity= projectRepository.findProjectByProjectIdAndUserID(projectId, userId).orElseThrow();

        members.add(projectMemberMapper.fromProjectMemberEntity(projectEntity));

        members.addAll(projectMemberRepository.getAllProjectMemberByProjectId(projectId).stream().map(
                projectMemberMapper::fromProjectMemberEntity
        ).toList());

       return members;
    }

    @Override
    public MemberResponseDto inviteMember(String projectId, InviteMemberRequestDto inviteMemberRequestDto, String userId) {
        final ProjectEntity projectEntity= projectRepository.findProjectByProjectIdAndUserID(projectId, userId).orElseThrow();

        if(!projectEntity.getOwner().getId().equals(userId)) {
            throw new RuntimeException("only owner can invite member");
        }

        final UserEntity userToInvite=userRepository.findByEmail(inviteMemberRequestDto.getEmail()).orElseThrow();

        if(userToInvite.getId().equals(userId)) {
            throw new RuntimeException(" owner can invite self");
        }

        if(projectMemberRepository.findByEmail(projectId,inviteMemberRequestDto.getEmail()).isPresent()) {
            throw new RuntimeException(" cannot invite member who is already invited");
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
        final ProjectEntity projectEntity= projectRepository.findProjectByProjectIdAndUserID(projectId, userId).orElseThrow();
        if(!projectEntity.getOwner().getId().equals(userId)) {
            throw new RuntimeException("only owner can update member role");
        }
        final ProjectMemberId projectMemberId=new ProjectMemberId(projectId, memberId);
        final ProjectMemberEntity projectMemberEntity=projectMemberRepository.findById(projectMemberId).orElseThrow();
        projectMemberEntity.setProjectRole(inviteMemberRequestDto.getRole());
        final ProjectMemberEntity savedProjectMemberEntity = projectMemberRepository.save(projectMemberEntity);
        return projectMemberMapper.fromProjectMemberEntity(savedProjectMemberEntity);
    }

    @Override
    public MemberResponseDto deleteProjectMember(String projectId, String memberId, String userId) {
        final ProjectEntity projectEntity= projectRepository.findProjectByProjectIdAndUserID(projectId, userId).orElseThrow();
        if(!projectEntity.getOwner().getId().equals(userId)) {
            throw new RuntimeException("only owner can delete member role");
        }
        if(memberId.equals(userId)) {
            throw new RuntimeException("can remove owner");
        }
        final ProjectMemberId projectMemberId=new ProjectMemberId(projectId, memberId);
        final ProjectMemberEntity projectMemberEntity=projectMemberRepository.findById(projectMemberId).orElseThrow();
        projectMemberRepository.delete(projectMemberEntity);
        return projectMemberMapper.fromProjectMemberEntity(projectMemberEntity);
    }
}
