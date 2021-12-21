package telran.b7a.accaunt.service;

import telran.b7a.accaunt.dto.AddDeleteRoleResponseDto;
import telran.b7a.accaunt.dto.LoginDto;
import telran.b7a.accaunt.dto.PutDto;
import telran.b7a.accaunt.dto.RegisterDto;
import telran.b7a.accaunt.dto.ResponseDto;

public interface AccauntService {
ResponseDto registerUser(RegisterDto registerDto);
ResponseDto loginUser (String login);
ResponseDto deleteUser(String name);
ResponseDto updateUser(String name, PutDto putDto);
AddDeleteRoleResponseDto addRole(String name, String role);
AddDeleteRoleResponseDto deleteRole(String name, String role);
void changePassword (LoginDto loginDto);
}
