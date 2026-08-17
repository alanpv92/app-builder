package com.appbuilder.appbuilder.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanEntity {

    private  String id;

    private  String name;

    private  String stripePriceId;

    private  Integer maxProjects;

    private  Integer maxTokensPerDay;

    private  Integer maxPreviews;

    private  Boolean unlimitedAi;

    private  Boolean active;
}
