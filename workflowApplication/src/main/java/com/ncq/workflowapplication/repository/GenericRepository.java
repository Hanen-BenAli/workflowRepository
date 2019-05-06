package com.ncq.workflowapplication.repository;

import com.ncq.workflowapplication.domain.Workflow;
import com.ncq.workflowapplication.domain.WorkflowCategory;
import com.ncq.workflowapplication.domain.Workflow_;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GenericRepository {

    @PersistenceContext
    protected EntityManager em;

    /**
     *
     * @param id
     * @return
     */
    public WorkflowCategory findWorkflowCategoryById(Integer id) {

        return em.find(WorkflowCategory.class, id);
    }

    /**
     *
     * @return
     */
    public List<WorkflowCategory> findWorkflowCategories() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<WorkflowCategory> cq = cb.createQuery(WorkflowCategory.class);

        Root<WorkflowCategory> root = cq.from(WorkflowCategory.class);

        TypedQuery<WorkflowCategory> query = em.createQuery(cq);
        return query.getResultList();
    }

    public Workflow findWorkflowById(Integer id) {
        return em.find(Workflow.class, id);
    }

    public List<Workflow> findWorkflows() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Workflow> cq = cb.createQuery(Workflow.class);

        Root<Workflow> root = cq.from(Workflow.class);

        TypedQuery<Workflow> query = em.createQuery(cq);
        return query.getResultList();
    }

    @Transactional
    public List<Workflow> findWorkflowsByFilters(List<Integer> workflowCategoryIds, String name, Integer enabled) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Workflow> cq = cb.createQuery(Workflow.class);

        Root<Workflow> root = cq.from(Workflow.class);
        List<Predicate> predicates = new ArrayList<>();
        if (workflowCategoryIds != null && !workflowCategoryIds.isEmpty()) {
            Join workflowCategoryJoin = root.join(Workflow_.description);
            Predicate workflowPred = workflowCategoryJoin.in(workflowCategoryIds);
            workflowCategoryJoin.on(workflowPred);
        }
        if (!StringUtils.isBlank(name)) {
            Predicate namePredicate = cb.like(cb.lower(root.get(Workflow_.name)), name.toLowerCase()); //cb.equal(cb.lower(root.get(Workflow_.name)), cb.lower(name));
            predicates.add(namePredicate);
        }

        if (enabled != null) {
            Predicate enabledPredicate = cb.equal(root.get(Workflow_.enabled), enabled);
            predicates.add(enabledPredicate);
        }
        cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        TypedQuery<Workflow> query = em.createQuery(cq);
        List<Workflow> result = query.getResultList();
        result.forEach(workflow -> {
            workflow.getWorkflowCategory().getName();
            workflow.getVariants().toString();
        });
        return result;
    }
}
