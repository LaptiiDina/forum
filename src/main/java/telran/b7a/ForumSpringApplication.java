package telran.b7a;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import telran.b7a.accaunt.model.User;
import telran.b7a.accaunt.repositoriy.AccauntRepositoriy;

@SpringBootApplication
public class ForumSpringApplication implements CommandLineRunner {
@Autowired
	AccauntRepositoriy repository;
	
	public static void main(String[] args) {
		SpringApplication.run(ForumSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(!repository.existsById("admin")) {
			String password = BCrypt.hashpw("admin", BCrypt.gensalt());
			User user = new User("admin",password," "," ");
			user.addRoles("User");
			user.addRoles("Moderator");
			user.addRoles("Administrator");
			repository.save(user);
			
		}
		
	}

}
