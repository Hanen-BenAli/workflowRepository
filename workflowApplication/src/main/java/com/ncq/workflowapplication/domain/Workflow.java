package com.ncq.workflowapplication.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import com.google.common.base.MoreObjects;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "workflow")
public class Workflow implements Serializable {

    private static final long serialVersionUID = 1L;

    // Raw attributes
    private Integer id;
    private String name;
    private String description;
    private Integer enabled;

    // Many to one
    private WorkflowCategory workflowCategory;
    // Many to many
    private List<Workflow> variants = new ArrayList<Workflow>();

    // -- [id] ------------------------
    @Column(name = "ID", precision = 10)
    @GeneratedValue(strategy = IDENTITY)
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Workflow id(Integer id) {
        setId(id);
        return this;
    }
    // -- [name] ------------------------

    @Size(max = 255)
    @Column(name = "NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Workflow name(String name) {
        setName(name);
        return this;
    }
    // -- [description] ------------------------

    @Size(max = 255)
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Workflow description(String description) {
        setDescription(description);
        return this;
    }
    // -- [enabled] ------------------------

    @Digits(integer = 10, fraction = 0)
    @NotNull
    @Column(name = "ENABLED", nullable = false, precision = 10)
    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Workflow enabled(Integer enabled) {
        setEnabled(enabled);
        return this;
    }

    // -----------------------------------------------------------------
    // Many to One support
    // -----------------------------------------------------------------
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // many-to-one: WorkflowCategory.workflowCategory ==> WorkflowCategory.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    @JoinColumn(name = "WORKFLOW_CATEGORY_ID")
    @ManyToOne(cascade = {PERSIST, MERGE}, fetch = LAZY)
    public WorkflowCategory getWorkflowCategory() {
        return workflowCategory;
    }

    /**
     * Set the {@link #parent} without adding this WorkflowCategory instance on
     * the passed {@link #workflowCategory}
     */
    public void setWorkflowCategory(WorkflowCategory workflowCategory) {
        this.workflowCategory = workflowCategory;
    }

    public Workflow workflowCategory(WorkflowCategory workflowCategory) {
        setWorkflowCategory(workflowCategory);
        return this;
    }

    // -----------------------------------------------------------------
    // Many to Many
    // -----------------------------------------------------------------
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
    // many-to-many: workflow ==> variants
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
    /**
     * Returns the {@link #variants} list.
     */
    @JoinTable(name = "workflow_variant", joinColumns = @JoinColumn(name = "WORKFLOW_ID"), inverseJoinColumns = @JoinColumn(name = "VARIANT_ID"))
    @ManyToMany(cascade = {PERSIST, MERGE})
    public List<Workflow> getVariants() {
        return variants;
    }

    /**
     * Set the {@link #variants} list.
     *
     * @param variants the list of Workflow
     */
    public void setVariants(List<Workflow> variants) {
        this.variants = variants;
    }

    /**
     * Helper method to add the passed {@link Workflow} to the {@link #variants}
     * list.
     */
    public boolean addVariant(Workflow variant) {
        return getVariants().add(variant);
    }

    /**
     * Helper method to remove the passed {@link Workflow} from the
     * {@link #variants} list.
     */
    public boolean removeVariant(Workflow variant) {
        return getVariants().remove(variant);
    }

    /**
     * Helper method to determine if the passed {@link Workflow} is present in
     * the {@link #variants} list.
     */
    public boolean containsVariant(Workflow variant) {
        return getVariants() != null && getVariants().contains(variant);
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof Workflow && hashCode() == other.hashCode());
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Construct a readable string representation for this Workflow instance.
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this) //
                .add("id", getId()) //
                .add("name", getName()) //
                .add("description", getDescription()) //
                .add("enabled", getEnabled()) //
                .toString();
    }
}
