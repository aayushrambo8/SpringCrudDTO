package live.aayush.controller;

import live.aayush.dto.StudentRequestDTO;
import live.aayush.dto.StudentResponseDTO;
import live.aayush.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController
{

    private final StudentService studentService;

    public StudentController(StudentService studentService)
    {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<StudentResponseDTO> createStudent(@RequestBody StudentRequestDTO studentRequestDTO)
    {
        StudentResponseDTO createdStudent = studentService.createStudent(studentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @GetMapping("/get")
    public ResponseEntity<StudentResponseDTO> getStudent(@RequestParam Long id)
    {
        StudentResponseDTO studentResponse = studentService.getStudent(id);
        if(studentResponse == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        else return ResponseEntity.ok(studentResponse);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StudentResponseDTO>> getAllStudent()
    {
        List<StudentResponseDTO> studentList = studentService.getAllStudent();
        if(studentList == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        else return ResponseEntity.ok(studentList);
    }

    @PutMapping("/update")
    public ResponseEntity<StudentResponseDTO> updateStudent(@RequestParam Long id,
                                                            @RequestBody StudentRequestDTO studentRequestDTO)
    {
        StudentResponseDTO studentResponse = studentService.updateStudent(id, studentRequestDTO);
        if(studentResponse == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        else return ResponseEntity.ok(studentResponse);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudent(@RequestParam Long id)
    {
        boolean isDeleted = studentService.deleteStudent(id);
        if(!isDeleted) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.ok("Record Deleted");
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllStudent()
    {
        studentService.deleteAllStudent();
        return ResponseEntity.ok("All Records Deleted");
    }

    @PatchMapping("/soft-delete")
    public ResponseEntity<String> softDeleteStudent(@RequestParam Long id)
    {
        boolean isDeleted = studentService.softDeleteStudent(id);
        if(!isDeleted) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.ok("Record Deleted");
    }
}
