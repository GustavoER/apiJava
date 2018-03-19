package br.com.devdojo.Repository;

import br.com.devdojo.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNameIgnoreCaseContaining(String name);
}
