package com.appbuilder.appbuilder.controllers;

import com.appbuilder.appbuilder.dto.member.InviteMemberRequestDto;
import com.appbuilder.appbuilder.dto.member.MemberResponseDto;
import com.appbuilder.appbuilder.dto.member.UpdateMemberRoleRequestDto;
import com.appbuilder.appbuilder.entity.ProjectMemberEntity;
import com.appbuilder.appbuilder.services.ProjectMemberService;
import com.appbuilder.appbuilder.utils.helpers.SecurityHelper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}/members")
@RequiredArgsConstructor
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;


    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> getProjectMembers(@PathVariable String projectId) {
        String userId = SecurityHelper.getId();
        return ResponseEntity.ok(projectMemberService.getProjectMembers(projectId, userId));
    }

    @PostMapping
    public ResponseEntity<MemberResponseDto> inviteMember(
            @PathVariable String projectId,
            @RequestBody @Valid InviteMemberRequestDto request
    ) {
        String userId = SecurityHelper.getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(
                projectMemberService.inviteMember(projectId, request, userId)
        );
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto> updateMemberRole(
            @PathVariable String projectId,
            @PathVariable String memberId,
            @RequestBody @Valid UpdateMemberRoleRequestDto request
    ) {
        String userId = SecurityHelper.getId();
        return ResponseEntity.ok(projectMemberService.updateMemberRole(projectId, memberId, request, userId));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto> deleteMember(
            @PathVariable String projectId,
            @PathVariable String memberId
    ) {
        String userId = SecurityHelper.getId();
        return ResponseEntity.ok(projectMemberService.deleteProjectMember(projectId, memberId, userId));
    }

}
