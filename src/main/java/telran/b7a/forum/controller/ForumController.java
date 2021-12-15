package telran.b7a.forum.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import telran.b7a.forum.dto.AddCommentToPostDto;
import telran.b7a.forum.dto.DtoPeriod;
import telran.b7a.forum.dto.DtoResponse;
import telran.b7a.forum.dto.RequsteDto;
import telran.b7a.forum.service.ForumService;

@RestController
public class ForumController {
	@Autowired
	ForumService forumService;

	@PostMapping("/forum/post/{author}")
	public DtoResponse addpost(@RequestBody RequsteDto requsteDto, @PathVariable String author) {

		return forumService.addpost(requsteDto,author);
	}

	@GetMapping("/forum/post/{id}")
	public DtoResponse findPostById(@PathVariable String id) {

		return forumService.findPostById(id);
	}

	@DeleteMapping("/forum/post/{id}")
	public DtoResponse deletePost(@PathVariable String id) {

		return forumService.deletePost(id);
	}

	@GetMapping("/forum/posts/author/{author}")
	public List<DtoResponse> findPostsbyAuthor(@PathVariable String author) {

		return forumService.findPostsbyAuthor(author);
	}

	@PutMapping("forum/post/{id}")
	public DtoResponse updatePost(@PathVariable String id, @RequestBody RequsteDto requsteDto) {

		return forumService.updatePost(id, requsteDto);

	}

	@PutMapping("/forum/post/{id}/like")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)

	public void addLikeToPost(@PathVariable String id) {
		forumService.addLikeToPost(id);
	}

	@PutMapping("/forum/post/{id}/comment/{author}")
	public DtoResponse AddCommentToPost(@PathVariable String id, @PathVariable String author,
			@RequestBody AddCommentToPostDto addCommentToPostDto) {

		return forumService.AddCommentToPost(id, author, addCommentToPostDto);
	}
	@PostMapping("/forum/posts/tags")
	public List<DtoResponse> findPostsByTags(@RequestBody String[] tags) {
		
		return forumService.findPostsByTags(tags);
	}
	@PostMapping("/forum/posts/period")
	public List<DtoResponse> findPostsByPeriod(@RequestBody DtoPeriod dtoPeriod) {
		
		return forumService.findPostsByPeriod(dtoPeriod);
	}
}
