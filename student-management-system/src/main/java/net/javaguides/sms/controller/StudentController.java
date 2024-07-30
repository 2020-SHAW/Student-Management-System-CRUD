package net.javaguides.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import net.javaguides.sms.entity.Student;
import net.javaguides.sms.service.DateService;
import net.javaguides.sms.service.StudentService;

@Controller
public class StudentController {

    private StudentService studentService;
    private DateService dateService;

    public StudentController(StudentService studentService, DateService dateService) {
        super();
        this.studentService = studentService;
        this.dateService = dateService;
    }
    
    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("dateService", dateService);
        return "students";
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student, @RequestParam("dob") String dob) {
        student.setDob(dob);
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student, @RequestParam("dob") String dob) {
        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setId(id);
        existingStudent.setFirstname(student.getFirstname());
        existingStudent.setLastname(student.getLastname());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setGender(student.getGender());
        existingStudent.setDob(student.getDob());
        studentService.updateStudent(existingStudent);
        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}
