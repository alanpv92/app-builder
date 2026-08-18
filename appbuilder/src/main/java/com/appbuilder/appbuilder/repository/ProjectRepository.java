package com.appbuilder.appbuilder.repository;
import com.appbuilder.appbuilder.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity,String> {


    @Query("""
          SELECT p from ProjectEntity p 
          WHERE p.deletedAt IS null
          AND p.owner.id = :id
          ORDER BY p.createdAt                             
          """
    )
    List<ProjectEntity> findAllForUser(@Param("id") String id);


    @Query("""
     SELECT p from ProjectEntity p 
     LEFT JOIN FETCH p.owner
     WHERE p.id= :projectId
     AND   p.deletedAt is null
     AND   p.owner.id = :userId                 
          
                    """)
    Optional<ProjectEntity> findProjectByProjectIdAndUserID(@Param("projectId") String projectId, @Param("userId") String userId);
}
