package live.aayush.dto;

import jakarta.validation.constraints.*;

public class UpdateStudentRequestDTO
{
    @NotBlank(message = "Name field Cannot be blank")
    private String name;

    @NotNull(message = "Age field Cannot be blank")
    @Min(value = 18, message = "Age must be greater than 18")
    @Max(value = 75, message = "Age must be less than 75")
    private int age;

    @NotNull(message = "Rollno. field Cannot be blank")
    private int rollNo;

    @NotBlank(message = "Subject field Cannot be blank")
    private String subject;

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

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }
}
