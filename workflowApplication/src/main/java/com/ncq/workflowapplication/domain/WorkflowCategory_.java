package com.ncq.workflowapplication.domain;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(WorkflowCategory.class)
public abstract class WorkflowCategory_ {

    // Raw attributes
    public static volatile SingularAttribute<WorkflowCategory, Integer> id;
    public static volatile SingularAttribute<WorkflowCategory, String> name;
    public static volatile SingularAttribute<WorkflowCategory, String> description;
    public static volatile SingularAttribute<WorkflowCategory, String> logo;
    public static volatile SingularAttribute<WorkflowCategory, Date> createAt;
    public static volatile SingularAttribute<WorkflowCategory, Date> updateAt;
    public static volatile SingularAttribute<WorkflowCategory, Integer> enabled;

    // Many to one
    public static volatile SingularAttribute<WorkflowCategory, WorkflowCategory> parent;
}