package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.MyException;
import com.example.demo.request.CreateProfessorRequest;
import com.example.demo.request.CreateStudentRequest;
import com.example.demo.request.DeleteProfessorRequest;
import com.example.demo.request.DeleteStudentRequest;
import com.example.demo.request.UpdateProfessorRequest;
import com.example.demo.request.UpdateStudentRequest;
import com.example.demo.response.GetProfessorResponse;
import com.example.demo.response.GetStudentResponse;
import com.example.demo.service.UserService;

@RestController
public class AdminController {

	@Autowired
	UserService userService;

	@PostMapping("admin/addStudent")
	public boolean createStudent(@RequestBody CreateStudentRequest request) throws MyException {
		return userService.createStudent(request);
	}

	@PostMapping("admin/addProfessor")
	public boolean createProfessor(@RequestBody CreateProfessorRequest request) throws MyException {
		return userService.createProfessor(request);
	}

	@GetMapping("admin/getAllStudent")
	public List<GetStudentResponse> getAllStudent() throws MyException {
		return userService.getAllStudent();
	}

	@GetMapping("admin/getAllProfessor")
	public List<GetProfessorResponse> getAllProfessor() throws MyException {
		return userService.getAllProfessors();
	}

	@PutMapping("admin/updateStudent")
	public boolean updateStudent(@RequestBody UpdateStudentRequest request) throws MyException {
		return userService.updateStudent(request);
	}

	@PutMapping("admin/updateProfessor")
	public boolean updateProfessor(@RequestBody UpdateProfessorRequest request) throws MyException {
		return userService.updateProfessor(request);
	}

	@PostMapping("admin/deleteStudent")
	public boolean deleteStudent(@RequestBody DeleteStudentRequest request) throws MyException {
		return userService.deleteStudent(request);
	}

	@PostMapping("admin/deleteProfessor")
	public boolean deleteProfessor(@RequestBody DeleteProfessorRequest request) throws MyException {
		return userService.deleteProfessor(request);
	}

}
