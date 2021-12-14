package telran.b7a.forum.repositoriy;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.forum.model.Post;

public interface ForumRepositoriy extends MongoRepository<Post, String> {

}
