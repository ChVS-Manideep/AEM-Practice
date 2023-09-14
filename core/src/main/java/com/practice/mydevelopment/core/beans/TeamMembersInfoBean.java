package com.practice.mydevelopment.core.beans;

public class TeamMembersInfoBean {

private String name;
private String role;
private String email;
private String bio;

    public TeamMembersInfoBean(String name, String role, String email, String bio) {
        this.name = name;
        this.role = role;
        this.email = email;
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getBio() {
        return bio;
    }
}
