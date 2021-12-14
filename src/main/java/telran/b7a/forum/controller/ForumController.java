package telran.b7a.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.b7a.forum.dto.AddCommentToPostDto;
import telran.b7a.forum.dto.DtoResponse;
import telran.b7a.forum.dto.RequsteDto;
import telran.b7a.forum.service.ForumService;

@RestController
public class ForumController {
	@Autowired
	ForumService forumService;

	@PostMapping("/forum/post/JavaFan")
	public DtoResponse addpost(@RequestBody RequsteDto requsteDto) {

		return forumService.addpost(requsteDto);
	}

	@GetMapping("/forum/post/{id}")
	public DtoResponse findPostById(@PathVariable String id) {

		return forumService.findPostById(id);
	}

	@DeleteMapping("/forum/post/{id}")
	public DtoResponse deletePost(@PathVariable String id) {

		return forumService.deletePost(id);
	}

	@GetMapping("/forum/posts/{author}/JavaFan")
	public List<DtoResponse> findPostsbyAuthor(@PathVariable String author) {

		return forumService.findPostsbyAuthor(author);
	}

	@PutMapping("forum/post/{id}")
	public DtoResponse updatePost(@PathVariable String id, @RequestBody RequsteDto requsteDto) {

		return forumService.updatePost(id, requsteDto);

	}

	@PutMapping("/forum/post/{id}/like")
	public void addLikeToPost(@PathVariable String id) {
		forumService.addLikeToPost(id);
	}

	@PutMapping("/forum/post/{id}/comment/{author}")
	public DtoResponse AddCommentToPost(@PathVariable String id, @PathVariable String author,
			@RequestBody AddCommentToPostDto addCommentToPostDto) {

		return forumService.AddCommentToPost(id, author, addCommentToPostDto);
	}
}
