package br.com.devdojo.Endpoint;

import br.com.devdojo.Error.CustomErrorType;
import br.com.devdojo.Error.ResourceNotFoundException;
import br.com.devdojo.Model.Student;
import br.com.devdojo.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/student")
public class StudentEndpoint {


    private final StudentRepository studentDAO;

    @Autowired
    public StudentEndpoint(StudentRepository studentDAO) {
        this.studentDAO = studentDAO;
    }

    @GetMapping
    public ResponseEntity<?> listAll() {

        return new ResponseEntity<>(studentDAO.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") long id) {
        verifyIfStudentExists(id);
        Student student = studentDAO.findOne(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Student student) {
        return new ResponseEntity<>(studentDAO.save(student), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        verifyIfStudentExists(id);
        Student student = studentDAO.findOne(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<?> findStudentsByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(studentDAO.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student student) {
        verifyIfStudentExists(student.getId());
        studentDAO.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void verifyIfStudentExists(Long id) {
        Student student = studentDAO.findOne(id);
        if (student == null) {
            throw new ResourceNotFoundException("Student Not Found for ID: " + id);
        }
    }
}
