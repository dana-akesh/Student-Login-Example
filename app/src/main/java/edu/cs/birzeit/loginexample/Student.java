package edu.cs.birzeit.loginexample;

import androidx.annotation.NonNull;

public class Student {
    private final String name;
    private final String email;
    private final String gender;
    private final boolean isFinalYear;

    public Student(String name, String email, String gender, boolean isFinalYear) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.isFinalYear = isFinalYear;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public boolean isFinalYear() {
        return isFinalYear;
    }

    @NonNull
    @Override
    public String toString() {
        return "Name: " +
                getName() +
                " Email: " +
                getEmail() +
                " Gender: " +
                getGender() +
                " Is Final Year: " +
                isFinalYear();
    }
}
