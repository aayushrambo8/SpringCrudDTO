package live.aayush.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class CreateStudentRequestDTO
{
    @NotNull(message = "Name field Cannot be blank")
    private String name;

    @NotNull(message = "Age field Cannot be blank")
    @Min(value = 18, message = "Age must be greater than 18")
    @Max(value = 75, message = "Age must be less than 75")
    private Integer age;

    @NotNull(message = "Rollno. field Cannot be blank")
    private int rollNo;

    @NotNull(message = "Email field Cannot be blank")
    @Email
    private String email;

    @NotNull(message = "Subject field Cannot be blank")
    private String subject;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public int getRollNo()
    {
        return rollNo;
    }

    public void setRollNo(int rollNo)
    {
        this.rollNo = rollNo;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt)
    {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt)
    {
        this.updatedAt = updatedAt;
    }
}
