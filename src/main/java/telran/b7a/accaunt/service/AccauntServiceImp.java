package telran.b7a.accaunt.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.b7a.accaunt.dto.AddDeleteRoleResponseDto;
import telran.b7a.accaunt.dto.LoginDto;
import telran.b7a.accaunt.dto.PutDto;
import telran.b7a.accaunt.dto.RegisterDto;
import telran.b7a.accaunt.dto.ResponseDto;
import telran.b7a.accaunt.model.User;
import telran.b7a.accaunt.repositoriy.AccauntRepositoriy;
import telran.b7a.forum.execption.ForumExeception;
import telran.b7a.forum.model.Post;
@Service
public class AccauntServiceImp implements AccauntService {
@Autowired
	AccauntRepositoriy acc;
	@Autowired
	ModelMapper modelMapper;
	@Override
	public ResponseDto registerUser(RegisterDto registerDto) {
	User user = modelMapper.map(registerDto, User.class);
	user.addRoles("User");
	acc.save(user);
	
		return modelMapper.map(user, ResponseDto.class);
	}

	@Override
	public ResponseDto loginUser(String login) {
	User user =	acc.findById(login).orElseThrow(() -> new ForumExeception(login));
	
	
		return modelMapper.map(user, ResponseDto.class);
	}

	@Override
	public ResponseDto deleteUser(String login) {
		User user =	acc.findById(login).orElseThrow(() -> new ForumExeception(login));
		acc.delete(user);
		return modelMapper.map(user, ResponseDto.class);
	}

	@Override
	public ResponseDto updateUser(String name, PutDto putDto) {
		User user =	acc.findById(name).orElseThrow(() -> new ForumExeception(name));
		user.setFirstName(putDto.getFirstName());
		user.setLastName(putDto.getLastName());
		acc.save(user);
		return modelMapper.map(user, ResponseDto.class);
	}

	@Override
	public AddDeleteRoleResponseDto addRole(String name, String role) {
		User user =	acc.findById(name).orElseThrow(() -> new ForumExeception(name));
		user.addRoles(role);
		acc.save(user);
		return modelMapper.map(user, AddDeleteRoleResponseDto.class);
	}

	@Override
	public AddDeleteRoleResponseDto deleteRole(String name, String role) {
		User user =	acc.findById(name).orElseThrow(() -> new ForumExeception(name));
		user.deleteRoles(role);
		acc.save(user);
		return modelMapper.map(user, AddDeleteRoleResponseDto.class);
	}

	@Override
	public void changePassword(LoginDto loginDto) {
		User user = acc.findById(loginDto.getLogin()).orElseThrow(() -> new ForumExeception(loginDto.getLogin()));
		user.setPassword(loginDto.getPassword());
		acc.save(user);
		
	}

}
