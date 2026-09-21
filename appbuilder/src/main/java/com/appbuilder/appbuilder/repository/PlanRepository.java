package com.appbuilder.appbuilder.repository;

import com.appbuilder.appbuilder.entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanRepository extends JpaRepository<PlanEntity,String> {

    Optional<PlanEntity> findByStripePriceId(String stripePriceId);
}
