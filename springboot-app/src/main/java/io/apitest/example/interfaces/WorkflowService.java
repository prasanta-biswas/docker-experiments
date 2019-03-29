package io.apitest.example.interfaces;

import io.apitest.example.model.Workflow;

import java.util.List;

/**
 * Created by prasantabiswas on 28/06/18.
 */
public interface WorkflowService {
    public List<Workflow> getAllWorkflow();
    public Workflow getWorkflowById(long id);
    public Workflow saveWorkflow(Workflow workflow);
    public void removeWorkflow(Workflow workflow);
}
