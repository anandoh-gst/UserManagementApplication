package com.nandonecode.fullstack_project.confirmation;

public class ApiResponse {

    private String message;

    public ApiResponse(String message) {
        this.message = message;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
