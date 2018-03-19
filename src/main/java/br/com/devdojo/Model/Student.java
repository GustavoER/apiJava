package br.com.devdojo.Model;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Student extends AbstractEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}