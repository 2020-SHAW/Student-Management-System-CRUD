package net.javaguides.sms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.sms.entity.Student;
import net.javaguides.sms.repository.StudentRepository;
import net.javaguides.sms.service.StudentService;
@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	private StudentRepository studentRepository;
	
	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}
	@Override
	public Student getStudentById(Long id) {
        Optional<Student> optional = studentRepository.findById(id);
        Student student = null;
        if (optional.isPresent()) {
            student = optional.get();
        } else {
            throw new RuntimeException(" Student not found for id :: " + id);
        }
        return student;
	}
	@Override
	public Student updateStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudent(Long id) {
		studentRepository.deleteById(id);
	}
	
}
