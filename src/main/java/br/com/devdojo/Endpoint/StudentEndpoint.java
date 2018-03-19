package br.com.devdojo.Endpoint;

import br.com.devdojo.Error.CustomErrorType;
import br.com.devdojo.Model.Student;
import br.com.devdojo.Util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentEndpoint {


    private DateUtil dateUtil;
    @Autowired
    public StudentEndpoint(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }

    @GetMapping
    public ResponseEntity<?> listAll() {

        return new ResponseEntity<>(Student.studentList, HttpStatus.OK);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") int id) {
        Student student = Student.studentList.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .get();
        return student == null? new ResponseEntity<>(new CustomErrorType("Not Found"), HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(student,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Student student){
        Student.studentList.add(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Student student){
        Student.studentList.remove(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student student){
        Optional<Student> studanteUpdate = Student.studentList.stream()
                .filter(s -> s.getId() == student.getId())
                .findFirst();
        if (!studanteUpdate.isPresent()){
            return new ResponseEntity<>(new CustomErrorType("Não Foi encontrado registro"), HttpStatus.NOT_FOUND);
        }
        Student atualizar = studanteUpdate.get();
        atualizar.setId(student.getId());
        atualizar.setName(student.getName());
        return new ResponseEntity<>(Student.studentList, HttpStatus.OK);
    }
}
