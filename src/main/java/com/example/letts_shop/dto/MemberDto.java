package com.example.letts_shop.dto;

import com.example.letts_shop.models.Address;
import com.example.letts_shop.validators.UniqueUsername;
import com.example.letts_shop.validators.ValidEmail;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class MemberDto extends RegisterDto {

    @NotNull
    @NotEmpty(message = "Please enter your first name")
    private String firstName;

    @NotNull
    @NotEmpty(message = "Please enter your last name")
    private String lastName;

    private String phone;

    @NotNull(message = "Please choose one")
    private String gender;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date birthDate;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

}
