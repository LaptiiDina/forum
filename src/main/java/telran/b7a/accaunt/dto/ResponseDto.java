package telran.b7a.accaunt.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto {
	String login;
	String firstName;
	String lastName;
	@Singular
	Set<String>roles = new HashSet<String>();
	

}
