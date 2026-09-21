package com.appbuilder.appbuilder.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "plans")
public class PlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private  String id;

    private  String name;

    @Column(nullable = false,unique = true)
    private  String stripePriceId;

    private  Integer maxProjects;

    private  Integer maxTokensPerDay;

    private  Integer maxPreviews;

    private  Boolean unlimitedAi;

    private  Boolean active;
}
