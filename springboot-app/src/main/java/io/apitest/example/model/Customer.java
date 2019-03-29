package io.apitest.example.model;

import io.apitest.example.enums.CustomerStatus;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by prasantabiswas on 26/06/18.
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String address;

    private boolean isOnboarded;
    private CustomerStatus status;

    @NotNull
    private long viewId;

    @NotNull
    private long workflowId;

    public Customer() {
        super();
    }

    public Customer(long id, String name, String address, boolean isOnboarded, CustomerStatus status, long viewId, long workflowId) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.isOnboarded = isOnboarded;
        this.status = status;
        this.viewId = viewId;
        this.workflowId = workflowId;
    }

    public Customer(String name, String address, boolean isOnboarded, CustomerStatus status, long viewId, long workflowId) {
        super();
        this.name = name;
        this.address = address;
        this.isOnboarded = isOnboarded;
        this.status = status;
        this.viewId = viewId;
        this.workflowId = workflowId;
    }

    public Customer(String name, String address, CustomerStatus status, long viewId, long workflowId) {
        super();
        this.name = name;
        this.address = address;
        this.isOnboarded = false;
        this.status = status;
        this.viewId = viewId;
        this.workflowId = workflowId;
    }

    public Customer(String name, String address, long viewId, long workflowId) {
        super();
        this.name = name;
        this.address = address;
        this.isOnboarded = false;
        this.status = CustomerStatus.INACTIVE;
        this.viewId = viewId;
        this.workflowId = workflowId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isOnboarded() {
        return isOnboarded;
    }

    public void setOnboarded(boolean onboarded) {
        isOnboarded = onboarded;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    public long getViewId() {
        return viewId;
    }

    public void setViewId(long viewId) {
        this.viewId = viewId;
    }

    public long getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(long workflowId) {
        this.workflowId = workflowId;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", isOnboarded=" + isOnboarded +
                ", status='" + status + '\'' +
                ", viewId=" + viewId +
                ", workflowId=" + workflowId +
                '}';
    }
}
