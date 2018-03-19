package br.com.devdojo.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Student {
    private String name;
    private int id;
    public static List<Student> studentList;

    static{
        studentRepository();
    }
    public Student(String name) {
        this.name = name;
    }
    public Student(String name, int id) {
        this(name);
        this.id = id;
    }
    public Student(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (id != student.id) return false;
        return name != null ? name.equals(student.name) : student.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + id;
        return result;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
    public static void studentRepository(){
        studentList = new ArrayList<>();
        studentList.add(new Student("Todoroki", 2));
        studentList.add(new Student("Gustavo", 3));
        studentList.add(new Student("Deku", 1));
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
