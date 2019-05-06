package com.ncq.workflowapplication.controller;

import com.ncq.workflowapplication.domain.Workflow;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ncq.workflowapplication.domain.WorkflowCategory;
import com.ncq.workflowapplication.repository.GenericRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.json.JSONObject;

@RestController
public class ApiRestController {

    @Autowired
    protected GenericRepository genericRepository;

    /**
     *
     * @return all workflowCategories
     */
    @RequestMapping(value = "/workflowCategories", method = RequestMethod.GET)
    public ResponseEntity<List<WorkflowCategory>> listAllWorkflowCategories() {
        List<WorkflowCategory> workflowCategories = genericRepository.findWorkflowCategories();
        if (workflowCategories.isEmpty()) {
            return new ResponseEntity<List<WorkflowCategory>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<WorkflowCategory>>(workflowCategories, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return workflowCategory by ID
     */
    @RequestMapping(value = "/workflowCategory/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkflowCategory> getWorkflowCategory(@PathVariable("id") Integer id) {

        WorkflowCategory workflowCategory = genericRepository.findWorkflowCategoryById(id);
        if (workflowCategory == null) {
            return new ResponseEntity<WorkflowCategory>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<WorkflowCategory>(workflowCategory, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/workflows", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Workflow>> getWorkflowCategory(JSONObject filters) {
        List<Integer> categoryIds = new ArrayList();
        String nameFilter = null;
        Integer enabled = null;
        if (filters.has("catgeoryIds")) {
            List<String> stringIds = Arrays.asList(filters.getString("catgeoryIds").split(","));
            categoryIds = stringIds.stream()
                    .filter(str -> str.matches("[0-9]+"))
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
        }
        if (filters.has("name")) {
            nameFilter = filters.getString("name");
        }
        if (filters.has("enabled")) {
            enabled = filters.getInt("enabled");
        }

        List<Workflow> workflows = genericRepository.findWorkflowsByFilters(categoryIds, nameFilter, enabled);
        if (workflows == null || workflows.isEmpty()) {
            return new ResponseEntity<List<Workflow>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Workflow>>(workflows, HttpStatus.OK);
    }

}
