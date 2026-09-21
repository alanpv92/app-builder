package com.appbuilder.appbuilder.entity;

import com.appbuilder.appbuilder.entity.enums.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subscriptions")
public class SubscriptionEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserEntity user;


    @ManyToOne(fetch = FetchType.LAZY)
    private PlanEntity plan;

    private SubscriptionStatus status;

    private String stripeCustomerId;

    private String stripeSubscriptionId;

    private LocalDateTime currentPeriodStart;

    private LocalDateTime currentPeriodEnd;

    private  Boolean cancelAtPeriodEnd=false;


    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
