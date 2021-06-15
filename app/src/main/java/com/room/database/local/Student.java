package com.room.database.local;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.room.database.Constants;

@Entity(tableName = Constants.TABLE_NAME)
public class Student {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Long id;

    @NonNull
    @ColumnInfo(name = "first_name")
    private String firstName;

    @NonNull
    @ColumnInfo(name = "last_name")
    private String lastName;

    @NonNull
    private String branch;

    @NonNull
    @ColumnInfo(name = "roll_number")
    private String rollNumber;

    @NonNull
    private String grade;

    @NonNull
    @ColumnInfo(name = "phone_number")
    private String phoneNumber;

    @NonNull
    @ColumnInfo(name = "passport_photo", typeAffinity = ColumnInfo.BLOB)
    private byte[] passportPhoto;

    @Ignore
    public Student(@NonNull String firstName, @NonNull String lastName, @NonNull String branch, @NonNull String rollNumber, @NonNull String grade, @NonNull String phoneNumber, @NonNull byte[] passportPhoto) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.branch = branch;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.phoneNumber = phoneNumber;
        this.passportPhoto = passportPhoto;
    }

    public Student(@NonNull Long id, @NonNull String firstName, @NonNull String lastName, @NonNull String branch, @NonNull String rollNumber, @NonNull String grade, @NonNull String phoneNumber, @NonNull byte[] passportPhoto) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.branch = branch;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.phoneNumber = phoneNumber;
        this.passportPhoto = passportPhoto;
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    @NonNull
    public String getBranch() {
        return branch;
    }

    public void setBranch(@NonNull String branch) {
        this.branch = branch;
    }

    @NonNull
    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(@NonNull String rollNumber) {
        this.rollNumber = rollNumber;
    }

    @NonNull
    public String getGrade() {
        return grade;
    }

    public void setGrade(@NonNull String grade) {
        this.grade = grade;
    }

    @NonNull
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NonNull String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @NonNull
    public byte[] getPassportPhoto() {
        return passportPhoto;
    }

    public void setPassportPhoto(@NonNull byte[] passportPhoto) {
        this.passportPhoto = passportPhoto;
    }
}