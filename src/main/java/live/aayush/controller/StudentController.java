package live.aayush.controller;

import jakarta.validation.Valid;
import live.aayush.dto.CreateStudentRequestDTO;
import live.aayush.dto.CreateStudentResponseDTO;
import live.aayush.dto.UpdateStudentResponseDTO;
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

    @PostMapping
    public ResponseEntity<CreateStudentResponseDTO> createStudent(@Valid @RequestBody CreateStudentRequestDTO createStudentRequestDTO)
    {
        CreateStudentResponseDTO createdStudent = studentService.createStudent(createStudentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateStudentResponseDTO> getStudent(@PathVariable Long id)
    {
        CreateStudentResponseDTO studentResponse = studentService.getStudent(id);
        if(studentResponse == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        else return ResponseEntity.ok(studentResponse);
    }

    @GetMapping
    public ResponseEntity<List<CreateStudentResponseDTO>> getAllStudent()
    {
        List<CreateStudentResponseDTO> studentList = studentService.getAllStudent();
        if(studentList == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        else return ResponseEntity.ok(studentList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateStudentResponseDTO> updateStudent(@PathVariable Long id,
                                                                  @Valid @RequestBody UpdateStudentResponseDTO updateStudentRequestDTO)
    {
        UpdateStudentResponseDTO studentResponse = studentService.updateStudent(id, updateStudentRequestDTO);
        if(studentResponse == null) return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(null);
        else return ResponseEntity
                .ok(studentResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id)
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

    @PatchMapping("/{id}")
    public ResponseEntity<String> softDeleteStudent(@PathVariable Long id)
    {
        boolean isDeleted = studentService.softDeleteStudent(id);
        if(!isDeleted) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.ok("Record Deleted");
    }
}
