package org.example.dto.post;

public class UserRequest {
    private String name;
    private String job;

    // Default constructor
    public UserRequest() {
    }

    // Constructor
    public UserRequest(String name, String job) {
        this.name = name;
        this.job = job;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
