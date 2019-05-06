package com.ncq.workflowapplication.domain;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@Entity
@Table(name = "workflow_category")
public class WorkflowCategory implements Serializable {
    private static final long serialVersionUID = 1L;

    // Raw attributes
    private Integer id;
    private String name;
    private String description;
    private String logo;
    private Date createAt;
    private Date updateAt;
    private Integer enabled;

    // Many to one
    private WorkflowCategory parent;
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

    public WorkflowCategory id(Integer id) {
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

    public WorkflowCategory name(String name) {
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

    public WorkflowCategory description(String description) {
        setDescription(description);
        return this;
    }
    // -- [logo] ------------------------

    @Size(max = 255)
    @Column(name = "LOGO")
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public WorkflowCategory logo(String logo) {
        setLogo(logo);
        return this;
    }
    // -- [createAt] ------------------------

    @NotNull
    @Column(name = "CREATE_AT", nullable = false, length = 19)
    @Temporal(TIMESTAMP)
    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public WorkflowCategory createAt(Date createAt) {
        setCreateAt(createAt);
        return this;
    }
    // -- [updateAt] ------------------------

    @Column(name = "UPDATE_AT", length = 19)
    @Temporal(TIMESTAMP)
    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public WorkflowCategory updateAt(Date updateAt) {
        setUpdateAt(updateAt);
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

    public WorkflowCategory enabled(Integer enabled) {
        setEnabled(enabled);
        return this;
    }

    // -----------------------------------------------------------------
    // Many to One support
    // -----------------------------------------------------------------

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // many-to-one: WorkflowCategory.parent ==> WorkflowCategory.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @JoinColumn(name = "PARENT_ID")
    @ManyToOne(cascade = { PERSIST, MERGE }, fetch = LAZY)
    public WorkflowCategory getParent() {
        return parent;
    }

    /**
     * Set the {@link #parent} without adding this WorkflowCategory instance on the passed {@link #parent}
     */
    public void setParent(WorkflowCategory parent) {
        this.parent = parent;
    }

    public WorkflowCategory parent(WorkflowCategory parent) {
        setParent(parent);
        return this;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof WorkflowCategory && hashCode() == other.hashCode());
    }


    @Override
    public int hashCode() {
       int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Construct a readable string representation for this WorkflowCategory instance.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this) //
                .add("id", getId()) //
                .add("name", getName()) //
                .add("description", getDescription()) //
                .add("logo", getLogo()) //
                .add("createAt", getCreateAt()) //
                .add("updateAt", getUpdateAt()) //
                .add("enabled", getEnabled()) //
                .toString();
    }
}