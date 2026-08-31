package live.aayush.service;

import live.aayush.dto.CreateStudentRequestDTO;
import live.aayush.dto.CreateStudentResponseDTO;
import live.aayush.dto.UpdateStudentRequestDTO;
import live.aayush.dto.UpdateStudentResponseDTO;
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

    private Student mapToCreateEntity(CreateStudentRequestDTO createStudentRequestDTO)
    {
        Student student = new Student();
        student.setName(createStudentRequestDTO.getName());
        student.setEmail(createStudentRequestDTO.getEmail());
        student.setAge(createStudentRequestDTO.getAge());
        student.setSubject(createStudentRequestDTO.getSubject());
        student.setRollNo(createStudentRequestDTO.getRollNo());
        student.setDeleted(false);


        return student;
    }

    private CreateStudentResponseDTO mapTCreateDTO(Student student, String message)
    {
        CreateStudentResponseDTO createStudentResponseDTO = new CreateStudentResponseDTO();
        createStudentResponseDTO.setId(student.getId());
        createStudentResponseDTO.setName(student.getName());
        createStudentResponseDTO.setAge(student.getAge());
        createStudentResponseDTO.setEmail(student.getEmail());
        createStudentResponseDTO.setSubject(student.getSubject());
        createStudentResponseDTO.setRollNo(student.getRollNo());
        createStudentResponseDTO.setMessage(message);
        createStudentResponseDTO.setCreatedAt(student.getCreatedAt());
        createStudentResponseDTO.setUpdatedAt(student.getUpdatedAt());

        return createStudentResponseDTO;
    }

    private UpdateStudentResponseDTO mapToUpdateDTO(Student student)
    {
        UpdateStudentResponseDTO updateStudentResponseDTO = new UpdateStudentResponseDTO();
        updateStudentResponseDTO.setId(student.getId());
        updateStudentResponseDTO.setName(student.getName());
        updateStudentResponseDTO.setAge(student.getAge());
        updateStudentResponseDTO.setEmail(student.getEmail());
        updateStudentResponseDTO.setSubject(student.getSubject());
        updateStudentResponseDTO.setRollNo(student.getRollNo());
        updateStudentResponseDTO.setMessage("Student updated successfully");
        updateStudentResponseDTO.setUpdatedAt(student.getUpdatedAt());

        return updateStudentResponseDTO;
    }

    public CreateStudentResponseDTO createStudent(CreateStudentRequestDTO createStudentRequestDTO)
    {
        Student student = mapToCreateEntity(createStudentRequestDTO);
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

        return mapTCreateDTO(studentRepository.save(student), "Student saved successfully");
    }

    public CreateStudentResponseDTO getStudent(Long id)
    {
        Optional<Student> studentResponse = studentRepository.findByIdAndDeletedIsFalse(id);
        return studentResponse.map(student -> mapTCreateDTO(student, "Student fetched successfully")).orElse(null);
    }

    public List<CreateStudentResponseDTO> getAllStudent()
    {
        List<Student> studentList = studentRepository.findByDeletedIsFalse();
        return studentList.stream().map(student -> mapTCreateDTO(student, "Student fetched successfully")).toList();
    }

    public UpdateStudentResponseDTO updateStudent(Long id, UpdateStudentRequestDTO createStudentRequestDTO)
    {
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);
        if(existingStudent.isEmpty()) return null;

        Student studentUpdate = existingStudent.get();

        studentUpdate.setName(createStudentRequestDTO.getName());
        studentUpdate.setAge(createStudentRequestDTO.getAge());
        studentUpdate.setSubject(createStudentRequestDTO.getSubject());
        studentUpdate.setRollNo(createStudentRequestDTO.getRollNo());
        studentUpdate.setUpdatedAt(LocalDateTime.now());

        Student savedStudent = studentRepository.save(studentUpdate);

        return mapToUpdateDTO(savedStudent);
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
