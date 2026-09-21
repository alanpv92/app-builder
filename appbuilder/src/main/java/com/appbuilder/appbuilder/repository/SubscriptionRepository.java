package com.appbuilder.appbuilder.repository;

import com.appbuilder.appbuilder.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity,String> {



    @Query("""
    SELECT s FROM SubscriptionEntity s
    WHERE s.user.email = :email
     """)
    Optional<SubscriptionEntity> findSubscriptionByEmail(@Param("email") String email);



    @Query(
            """
                SELECT s FROM SubscriptionEntity s
                WHERE s.user.id= :userId           \s
           \s
           \s"""
    )
    Optional<SubscriptionEntity> findByUserId(@Param("userId") String userId);
}
