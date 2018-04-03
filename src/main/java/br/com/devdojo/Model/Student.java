package br.com.devdojo.Model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Student extends AbstractEntity {
    @NotEmpty
    private String name;
    @Email
    @NotEmpty
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}