package com.example.demo.controller;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.StudentRepo;
import com.example.demo.model.Student;

@RestController
public class StudentController
{
	@Autowired
	StudentRepo repo;

	@RequestMapping("/")
	public String home()
	{
		return "home.jsp";
	}
	
	@DeleteMapping("/student/{studentId}")
	public String deleteStudent(@PathVariable int studentId)
	{
		Student a = repo.getOne(studentId);
		repo.delete(a);
		return "deleted";
	}
	
	@PostMapping(path="/student",consumes= {"application/json"})
	public Student addStudent(@RequestBody Student student)
	{
		repo.save(student);
		return student;
	}
	@GetMapping(path="/students")
	public List<Student> getStudents()
	{
		return repo.findAll();
			
	}
	@PutMapping(path="/student",consumes= {"application/json"})
	public Student saveOrUpdateStudent(@RequestBody Student student)
	{
		repo.save(student);
		return student;
	}
	
	
	@RequestMapping("/student/{studentID}")
	public Optional<Student> getStudent(@PathVariable("studentID")int studentID)
	{
		return repo.findById(studentID);
		
		
	}
}
