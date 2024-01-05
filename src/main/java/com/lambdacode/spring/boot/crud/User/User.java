package com.lambdacode.spring.boot.crud.User;

import java.util.HashSet;
import java.util.Set;

import com.lambdacode.spring.boot.crud.Course.Course;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String userType;
    @ManyToMany(mappedBy = "enrolledUsers")
    private Set<Course> enrolledCourses = new HashSet<>();
    // Other getters and setters
    public Set<Course> getEnrolledCourses() {
        return enrolledCourses;
    }
    public void setEnrolledCourses(Set<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }
    public Integer getUserId() {
        return id;
    }
    public void setUserId(Integer userId) {
        this.id = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }
}
