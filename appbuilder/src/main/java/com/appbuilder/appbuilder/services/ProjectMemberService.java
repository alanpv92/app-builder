package com.appbuilder.appbuilder.services;

import com.appbuilder.appbuilder.dto.member.InviteMemberRequestDto;
import com.appbuilder.appbuilder.dto.member.MemberResponseDto;
import com.appbuilder.appbuilder.dto.member.UpdateMemberRoleRequestDto;
import com.appbuilder.appbuilder.entity.ProjectMemberEntity;

import java.util.List;

public interface ProjectMemberService {

    List<ProjectMemberEntity> getProjectMembers(String projectId,String userId);

    MemberResponseDto inviteMember(String projectId, InviteMemberRequestDto inviteMemberRequestDto, String userId);

    MemberResponseDto updateMemberRole(String projectId, String memberId, UpdateMemberRoleRequestDto inviteMemberRequestDto, String userId);

    MemberResponseDto deleteProjectMember(String projectId, String memberId, String userId);

}
