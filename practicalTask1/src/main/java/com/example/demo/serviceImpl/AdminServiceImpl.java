package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.MyException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.CreateProfessorRequest;
import com.example.demo.request.CreateStudentRequest;
import com.example.demo.request.DeleteProfessorRequest;
import com.example.demo.request.DeleteStudentRequest;
import com.example.demo.request.UpdateProfessorRequest;
import com.example.demo.request.UpdateStudentRequest;
import com.example.demo.response.GetProfessorResponse;
import com.example.demo.response.GetStudentResponse;
import com.example.demo.service.UserService;

@Service
public class AdminServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	public List<GetStudentResponse> getAllStudent() {

		RoleEntity studentRole = roleRepository.findById((long) 1).orElseThrow(null);

		List<UserEntity> studentsList = userRepository.findAllByRoleAndStatus(studentRole, 0);

		List<GetStudentResponse> response = studentsList.stream().map(student -> convertToGetStudentResponse(student))
				.collect(Collectors.toList());
		return response;
	}

	@Override
	public List<GetProfessorResponse> getAllProfessors() {

		RoleEntity professorRole = roleRepository.findById((long) 2).orElseThrow(null);

		List<UserEntity> studentsList = userRepository.findAllByRoleAndStatus(professorRole, 0);

		List<GetProfessorResponse> response = studentsList.stream()
				.map(student -> convertToGetProfessorResponse(student)).collect(Collectors.toList());
		return response;
	}

	@Override
	public boolean updateStudent(UpdateStudentRequest request) throws MyException {

		boolean response = false;

		UserEntity admin = userRepository.findById(request.getAdminId()).orElse(null);
		RoleEntity adminRole = roleRepository.findById((long) 3).orElseThrow(null);

		if (admin == null || admin.getRole() != adminRole) {
			throw new MyException("Access is deniend, Role mismatch");
		}

		UserEntity student = userRepository.findById(request.getStudentId()).orElse(null);

		if (student == null) {
			throw new MyException("Student not Exist.");
		}

		else {

			if (request.getAddress() != null) {
				student.setAddress(request.getAddress());
			}
			if (request.getMobileNumber() != null) {
				student.setMobileNumber(request.getMobileNumber());
			}
			if (request.getName() != null) {
				student.setName(request.getName());
			}
			if (request.getPassword() != null) {
				student.setPassword(request.getPassword());
			}
			if (request.getStandard() != null) {
				student.setStandard(request.getStandard());
			}
			if (request.getUsername() != null) {
				student.setUsername(request.getUsername());
			}
			student.setUpdatedAt(new Date());

		}

		try {
			userRepository.save(student);
			response = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response;
	}

	@Override
	public boolean updateProfessor(UpdateProfessorRequest request) throws MyException {

		boolean response = false;

		UserEntity admin = userRepository.findById(request.getAdminId()).orElse(null);
		RoleEntity adminRole = roleRepository.findById((long) 3).orElseThrow(null);

		if (admin == null || admin.getRole() != adminRole) {
			throw new MyException("Access is deniend, Role mismatch");
		}

		UserEntity student = userRepository.findById(request.getProfessorId()).orElse(null);

		if (student == null) {
			throw new MyException("Professor not Exist.");
		}

		else {

			if (request.getAddress() != null) {
				student.setAddress(request.getAddress());
			}
			if (request.getMobileNumber() != null) {
				student.setMobileNumber(request.getMobileNumber());
			}
			if (request.getName() != null) {
				student.setName(request.getName());
			}
			if (request.getPassword() != null) {
				student.setPassword(request.getPassword());
			}
			if (request.getUsername() != null) {
				student.setUsername(request.getUsername());
			}
			student.setUpdatedAt(new Date());

		}

		try {
			userRepository.save(student);
			response = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response;
	}

	public GetStudentResponse convertToGetStudentResponse(UserEntity user) {
		GetStudentResponse response = new GetStudentResponse();
		response.setAddress(user.getAddress());
		response.setCreatedAt(user.getCreatedAt());
		response.setId(user.getId());
		response.setMobileNumber(user.getMobileNumber());
		response.setName(user.getName());
		response.setPassword(user.getPassword());
		response.setRole("Student");
		response.setStandard(user.getStandard());
		response.setUpdatedAt(user.getUpdatedAt());
		response.setStatus(user.getStatus());
		response.setUsername(user.getUsername());
		return response;
	}

	public GetProfessorResponse convertToGetProfessorResponse(UserEntity user) {
		GetProfessorResponse response = new GetProfessorResponse();
		response.setAddress(user.getAddress());
		response.setCreatedAt(user.getCreatedAt());
		response.setId(user.getId());
		response.setMobileNumber(user.getMobileNumber());
		response.setName(user.getName());
		response.setPassword(user.getPassword());
		response.setRole("Professor");
		response.setUpdatedAt(user.getUpdatedAt());
		response.setStatus(user.getStatus());
		response.setUsername(user.getUsername());
		return response;
	}

	@Override
	public boolean createStudent(CreateStudentRequest request) throws MyException {

		UserEntity admin = userRepository.findById(request.getAdminId()).orElse(null);
		RoleEntity adminRole = roleRepository.findById((long) 3).orElseThrow(null);

		if (admin == null || admin.getRole() != adminRole) {
			throw new MyException("Access is deniend, Role mismatch");
		}

		boolean response = false;

		RoleEntity role = roleRepository.findById((long) 1).orElseThrow(null);

		UserEntity student = new UserEntity();
		student.setAddress(request.getAddress());
		student.setCreatedAt(new Date());
		student.setMobileNumber(request.getMobileNumber());
		student.setName(request.getName());
		student.setPassword(request.getPassword());
		student.setRole(role);
		student.setStatus(0);
		student.setUpdatedAt(new Date());
		student.setUsername(request.getUsername());
		student.setStandard(request.getStandard());

		try {
			userRepository.save(student);
			response = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response;
	}

	@Override
	public boolean createProfessor(CreateProfessorRequest request) throws MyException {

		boolean response = false;

		UserEntity admin = userRepository.findById(request.getAdminId()).orElse(null);
		RoleEntity adminRole = roleRepository.findById((long) 3).orElseThrow(null);

		if (admin.getRole() != adminRole) {
			throw new MyException("Access is deniend, Role mismatch");
		}

		RoleEntity role = roleRepository.findById((long) 2).orElseThrow(null);

		UserEntity professor = new UserEntity();
		professor.setAddress(request.getAddress());
		professor.setCreatedAt(new Date());
		professor.setMobileNumber(request.getMobileNumber());
		professor.setName(request.getName());
		professor.setPassword(request.getPassword());
		professor.setRole(role);
		professor.setStatus(0);
		professor.setUpdatedAt(new Date());
		professor.setUsername(request.getUsername());

		try {
			userRepository.save(professor);
			response = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response;
	}

	@Override
	public boolean deleteStudent(DeleteStudentRequest request) throws MyException {
		boolean response = false;

		UserEntity admin = userRepository.findById(request.getAdminId()).orElse(null);
		RoleEntity adminRole = roleRepository.findById((long) 3).orElseThrow(null);

		if (admin.getRole() != adminRole) {
			throw new MyException("Access is deniend, Role mismatch");
		}

		UserEntity student = userRepository.findById(request.getStudentID()).orElse(null);

		if (student == null) {
			throw new MyException("Student not Exist.");
		}

		student.setStatus(1);
		student.setUpdatedAt(new Date());

		try {
			userRepository.save(student);
			response = true;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return response;
	}

	@Override
	public boolean deleteProfessor(DeleteProfessorRequest request) throws MyException {
		boolean response = false;

		UserEntity admin = userRepository.findById(request.getAdminId()).orElse(null);
		RoleEntity adminRole = roleRepository.findById((long) 3).orElseThrow(null);

		if (admin.getRole() != adminRole) {
			throw new MyException("Access is deniend, Role mismatch");
		}

		UserEntity student = userRepository.findById(request.getProfessorId()).orElse(null);

		if (student == null) {
			throw new MyException("Student not Exist.");
		}

		student.setStatus(1);
		student.setUpdatedAt(new Date());

		try {
			userRepository.save(student);
			response = true;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return response;
	}
}
