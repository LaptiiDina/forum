package telran.b7a.accaunt.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Users")
@Setter
@EqualsAndHashCode(of={"login"})
public class User {
@Id
	String login;

	String password;
	String firstName;
	String lastName;
	Set<String> roles = new HashSet<>();

	public User(String login, String password, String firstName, String lastName) {
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public void addRoles(String role) {
		roles.add(role);
	}
	
	public void deleteRoles(String role) {
		roles.remove(role);
	}

}
