package telran.b7a.forum.repositoriy;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.b7a.forum.model.Post;



public interface ForumRepositoriy extends MongoRepository<Post, String> {

}
