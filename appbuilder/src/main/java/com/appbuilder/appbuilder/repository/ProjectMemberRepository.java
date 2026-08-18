package com.appbuilder.appbuilder.repository;

import com.appbuilder.appbuilder.entity.ProjectMemberEntity;
import com.appbuilder.appbuilder.entity.ProjectMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectMemberRepository extends JpaRepository<ProjectMemberEntity, ProjectMemberId> {


    @Query(

            """
            SELECT p from ProjectMemberEntity p
            WHERE p.projectMemberId.projectId =:projectId                   \s
           \s"""
    )
    List<ProjectMemberEntity> getAllProjectMemberByProjectId(@Param("projectId") String projectId);



    @Query("""
    SELECT p
    FROM ProjectMemberEntity p
    WHERE p.projectEntity.id = :projectId
      AND p.userEntity.email = :email
    """)
    Optional<ProjectMemberEntity> findByEmail(@Param("projectId") String projectId,@Param("email") String email);
}
