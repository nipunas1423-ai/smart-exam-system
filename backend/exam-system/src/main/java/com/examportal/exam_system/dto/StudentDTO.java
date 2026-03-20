package com.examportal.exam_system.dto;

import jakarta.validation.constraints.*;

public class StudentDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    private int age;

    @NotBlank(message = "Password is required")
    private String password;

    public StudentDTO() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}