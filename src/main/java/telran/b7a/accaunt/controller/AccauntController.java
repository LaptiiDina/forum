package telran.b7a.accaunt.controller;

import java.security.Principal;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.b7a.accaunt.dto.AddDeleteRoleResponseDto;
import telran.b7a.accaunt.dto.LoginDto;
import telran.b7a.accaunt.dto.PutDto;
import telran.b7a.accaunt.dto.RegisterDto;
import telran.b7a.accaunt.dto.ResponseDto;
import telran.b7a.accaunt.service.AccauntService;

@RestController
@RequestMapping("/account")
public class AccauntController {
	@Autowired
AccauntService acc;
	
	@PostMapping("/register")
	public ResponseDto registerUser(@RequestBody RegisterDto registerDto) {
		
		return acc.registerUser(registerDto);
	}

	@PostMapping("/login")
	public ResponseDto loginUser(Principal principal) {
//		token = token.split(" ")[1];
//	byte[]bytesCode =Base64.getDecoder().decode(token);
//	token = new String(bytesCode);
//	String[]credential = token.split(":");
		return acc.loginUser(principal.getName());
	}

	@DeleteMapping("/user/{user}")
	public ResponseDto deleteUser(@PathVariable String user) {
		
		return acc.deleteUser(user);
	}

	@PutMapping("/user/{user}")
	public ResponseDto updateUser(@PathVariable String user, @RequestBody PutDto putDto) {
		
		return acc.updateUser(user, putDto);
	}

	@PutMapping("/user/{user}/role/{role}")
	public AddDeleteRoleResponseDto addRole(@PathVariable String user, @PathVariable String role) {
		
		return acc.addRole(user, role);
	}

	@DeleteMapping("/user/{user}/role/{role}")
	public AddDeleteRoleResponseDto deleteRole(@PathVariable String user, @PathVariable String role) {
		
		return acc.deleteRole(user, role);
	}

	@PutMapping ("/password")
	public void changePassword(@RequestBody LoginDto loginDto) {
		acc.changePassword(loginDto);
		
	}

}
