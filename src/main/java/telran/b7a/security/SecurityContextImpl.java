package telran.b7a.security;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
@Component
public class SecurityContextImpl implements SecurityContex {
Map<String,UserProfile>contex = new ConcurrentHashMap<>();
	@Override
	public boolean addUser(UserProfile user) {
		
		return contex.putIfAbsent(user.login, user)== null;
	}

	@Override
	public UserProfile removeUser(String login) {
	
		return contex.remove(login);
	}

	@Override
	public UserProfile getUser(String login) {
		
		return contex.get(login);
	}

}
