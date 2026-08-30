package live.aayush.service;

import live.aayush.dto.StudentRequestDTO;
import live.aayush.dto.StudentResponseDTO;
import live.aayush.entity.Student;
import live.aayush.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService
{
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }

    private Student mapToEntity(StudentRequestDTO studentRequestDTO)
    {
        Student student = new Student();
        student.setName(studentRequestDTO.getName());
        student.setEmail(studentRequestDTO.getEmail());
        student.setAge(studentRequestDTO.getAge());
        student.setSubject(studentRequestDTO.getSubject());
        student.setRollNo(studentRequestDTO.getRollNo());
        student.setDeleted(false);


        return student;
    }

    private StudentResponseDTO mapToDTO(Student student, String message)
    {
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setId(student.getId());
        studentResponseDTO.setName(student.getName());
        studentResponseDTO.setAge(student.getAge());
        studentResponseDTO.setEmail(student.getEmail());
        studentResponseDTO.setSubject(student.getSubject());
        studentResponseDTO.setRollNo(student.getRollNo());
        studentResponseDTO.setMessage(message);
        studentResponseDTO.setCreatedAt(student.getCreatedAt());
        studentResponseDTO.setUpdatedAt(student.getUpdatedAt());

        return studentResponseDTO;
    }


    public StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO)
    {
        Student student = mapToEntity(studentRequestDTO);
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

        return mapToDTO(studentRepository.save(student), "Student saved successfully");
    }

    public StudentResponseDTO getStudent(Long id)
    {
        Optional<Student> studentResponse = studentRepository.findByIdAndDeletedIsFalse(id);
        return studentResponse.map(student -> mapToDTO(student, "Student fetched successfully")).orElse(null);
    }

    public List<StudentResponseDTO> getAllStudent()
    {
        List<Student> studentList = studentRepository.findByDeletedIsFalse();
        return studentList.stream().map(student -> mapToDTO(student, "Student fetched successfully")).toList();
    }

    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO)
    {
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);
        if(existingStudent.isEmpty()) return null;

        Student studentUpdate = existingStudent.get();

        studentUpdate.setName(studentRequestDTO.getName());
        studentUpdate.setAge(studentRequestDTO.getAge());
        studentUpdate.setEmail(studentRequestDTO.getEmail());
        studentUpdate.setSubject(studentRequestDTO.getSubject());
        studentUpdate.setRollNo(studentRequestDTO.getRollNo());
        studentUpdate.setUpdatedAt(LocalDateTime.now());

        Student savedStudent = studentRepository.save(studentUpdate);

        return mapToDTO(savedStudent, "Student updated successfully");
    }

    public boolean deleteStudent(Long id)
    {
        boolean isStudent = studentRepository.existsById(id);
        if(!isStudent) return false;
        studentRepository.deleteById(id);
        return true;
    }

    public void deleteAllStudent()
    {
        studentRepository.deleteAll();
    }

    public boolean softDeleteStudent(Long id)
    {
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);
        if(existingStudent.isEmpty()) return false;
        
        Student student = existingStudent.get();
        student.setDeleted(true);
        student.setUpdatedAt(LocalDateTime.now());
        studentRepository.save(student);
        return true;
    }
}
