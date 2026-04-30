package com.examportal.exam_system.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document
public class Student {

    @Id
   
    private String id;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 60, message = "Age must be less than or equal to 60")
    private int age;

    // Write-only — never returned in API responses
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public Student() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}