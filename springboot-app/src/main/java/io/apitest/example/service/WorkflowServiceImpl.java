package io.apitest.example.service;

import io.apitest.example.interfaces.WorkflowService;
import io.apitest.example.model.Workflow;
import io.apitest.example.repository.WorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by prasantabiswas on 28/06/18.
 */

@Service("workflowService")
public class WorkflowServiceImpl implements WorkflowService {

    @Autowired
    private WorkflowRepository workflowRepository;


    @Override
    public List<Workflow> getAllWorkflow() {
        return workflowRepository.findAll();
    }

    @Override
    public Workflow getWorkflowById(long id) {
        return workflowRepository.findOne(id);
    }

    @Override
    public Workflow saveWorkflow(Workflow workflow) {
        return workflowRepository.save(workflow);
    }

    @Override
    public void removeWorkflow(Workflow workflow) {
        workflowRepository.delete(workflow);
    }
}
