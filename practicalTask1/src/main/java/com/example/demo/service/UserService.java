package com.example.demo.service;

import java.util.List;

import com.example.demo.exception.MyException;
import com.example.demo.request.CreateProfessorRequest;
import com.example.demo.request.CreateStudentRequest;
import com.example.demo.request.DeleteProfessorRequest;
import com.example.demo.request.DeleteStudentRequest;
import com.example.demo.request.UpdateProfessorRequest;
import com.example.demo.request.UpdateStudentRequest;
import com.example.demo.response.GetProfessorResponse;
import com.example.demo.response.GetStudentResponse;

public interface UserService {

	boolean createStudent(CreateStudentRequest request) throws MyException;

	boolean createProfessor(CreateProfessorRequest request) throws MyException;

	List<GetStudentResponse> getAllStudent();

	List<GetProfessorResponse> getAllProfessors();

	boolean updateStudent(UpdateStudentRequest request) throws MyException;

	boolean updateProfessor(UpdateProfessorRequest request) throws MyException;

	boolean deleteProfessor(DeleteProfessorRequest request) throws MyException;

	boolean deleteStudent(DeleteStudentRequest request) throws MyException;
}
