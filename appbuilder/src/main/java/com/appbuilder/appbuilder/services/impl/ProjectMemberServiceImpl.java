package com.appbuilder.appbuilder.controllers.impl;

import com.appbuilder.appbuilder.dto.member.InviteMemberRequestDto;
import com.appbuilder.appbuilder.dto.member.MemberResponseDto;
import com.appbuilder.appbuilder.dto.member.UpdateMemberRoleRequestDto;
import com.appbuilder.appbuilder.entity.ProjectMemberEntity;
import com.appbuilder.appbuilder.services.ProjectMemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectMemberControllerImpl implements ProjectMemberService {
    @Override
    public List<ProjectMemberEntity> getProjectMembers(String projectId, String userId) {
        return List.of();
    }

    @Override
    public MemberResponseDto inviteMember(String projectId, InviteMemberRequestDto inviteMemberRequestDto, String userId) {
        return null;
    }

    @Override
    public MemberResponseDto updateMemberRole(String projectId, String memberId, UpdateMemberRoleRequestDto inviteMemberRequestDto, String userId) {
        return null;
    }

    @Override
    public MemberResponseDto deleteProjectMember(String projectId, String memberId, String userId) {
        return null;
    }
}
