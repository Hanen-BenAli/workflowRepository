package com.ncq.workflowapplication.domain;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Workflow.class)
public abstract class Workflow_ {

    // Raw attributes
    public static volatile SingularAttribute<Workflow, Integer> id;
    public static volatile SingularAttribute<Workflow, String> name;
    public static volatile SingularAttribute<Workflow, String> description;
    public static volatile SingularAttribute<Workflow, Integer> enabled;

    //Many to one
    public static volatile SingularAttribute<Workflow, WorkflowCategory> workflowCategory;

    // Many to many
    public static volatile ListAttribute<Workflow, Workflow> variants;
}
