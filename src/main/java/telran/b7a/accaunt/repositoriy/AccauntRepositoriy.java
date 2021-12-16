package telran.b7a.accaunt.repositoriy;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.b7a.accaunt.model.User;

public interface AccauntRepositoriy extends MongoRepository<User, String> {

}
