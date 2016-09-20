package com.ezdi.pbac.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by EZDI\ganesh.s on 10/9/16.
 */
public class SecurityContext {
    private String userId;
    private String entityType;
    private Map<String, String> contextParams;

    public SecurityContext() {
        this.userId = "";
        this.entityType = "";
        this.contextParams = new HashMap<>();
    }

    public SecurityContext(String userId, String entityType, Map<String, String> contextParams) {
        this.userId = userId;
        this.entityType = entityType;
        this.contextParams = contextParams;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public Map<String, String> getContextParams() {
        return contextParams;
    }

    public void setContextParams(Map<String, String> contextParams) {
        this.contextParams = contextParams;
    }

    @Override
    public String toString() {
        return "SecurityContext{" +
                "userId='" + userId + '\'' +
                ", entityType='" + entityType + '\'' +
                ", contextParams=" + contextParams +
                '}';
    }
}
