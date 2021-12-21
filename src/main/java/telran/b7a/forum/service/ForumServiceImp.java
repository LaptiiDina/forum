package telran.b7a.forum.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Stream;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat;

import telran.b7a.forum.dto.AddCommentToPostDto;
import telran.b7a.forum.dto.DtoPeriod;
import telran.b7a.forum.dto.DtoResponse;
import telran.b7a.forum.dto.DtoResponse;
import telran.b7a.forum.dto.RequsteDto;

import telran.b7a.forum.execption.ForumExeception;
import telran.b7a.forum.model.Comments;
import telran.b7a.forum.model.Post;
import telran.b7a.forum.repositoriy.ForumRepositoriy;
import telran.b7a.forum.service.logging.PostLogger;


@Service
public class ForumServiceImp implements ForumService {
	
	
	@Autowired
	ForumRepositoriy forumRepositoriy;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public DtoResponse addpost(RequsteDto requsteDto, String author) {

		Post post = modelMapper.map(requsteDto, Post.class);
		post.setAuthor(author);
		forumRepositoriy.save(post);

		DtoResponse response = modelMapper.map(post, DtoResponse.class);

		return response;
	}

	@Override
	public DtoResponse findPostById(String id) {
		Post post = forumRepositoriy.findById(id).orElseThrow(() -> new ForumExeception(id));
		DtoResponse response = modelMapper.map(post, DtoResponse.class);

		return response;
	}

	@Override
	public DtoResponse deletePost(String id) {
		Post post = forumRepositoriy.findById(id).orElseThrow(() -> new ForumExeception(id));
		forumRepositoriy.delete(post);
		DtoResponse response = modelMapper.map(post, DtoResponse.class);
		return response;
	}

	@Override
	public List<DtoResponse> findPostsbyAuthor(String author) {

		return forumRepositoriy.findAll().stream().filter(p -> p.getAuthor().equals(author))
				.map(p -> modelMapper.map(p, DtoResponse.class)).collect(Collectors.toList());
	}

	@Override
	@PostLogger
	public DtoResponse updatePost(String id, RequsteDto requsteDto) {

		Post post = forumRepositoriy.findById(id).orElseThrow(() -> new ForumExeception(id));
		String title = requsteDto.getTitle();
		if (title == null) {
			title = post.getTitle();
		}

		String content = requsteDto.getContent();
		if (content == null) {
			content = post.getContent();
		}

		Set<String> tags = post.getTags();
		tags.addAll(requsteDto.getTags());

		Post nPost = new Post(id, title, content, post.getAuthor(), LocalDateTime.now(), tags, post.getLikes(),
				post.getComments());

		DtoResponse result = modelMapper.map(nPost, DtoResponse.class);
		forumRepositoriy.save(nPost);
		return result;
	}

	@Override
	@PostLogger
	public void addLikeToPost(String id) {
		Post post = forumRepositoriy.findById(id).orElseThrow(() -> new ForumExeception(id));
		post.addLike();

		forumRepositoriy.save(post);

	}

	@Override
	public DtoResponse AddCommentToPost(String id, String author, AddCommentToPostDto addCommentToPostDto) {
		Post post = forumRepositoriy.findById(id).orElseThrow(() -> new ForumExeception(id));
		Comments comment = new Comments(author, addCommentToPostDto.getMessage(), LocalDateTime.now(), 0);
		post.addComment(comment);
		forumRepositoriy.save(post);
		DtoResponse result = modelMapper.map(post, DtoResponse.class);
		return result;
	}

	@Override
	public List<DtoResponse> findPostsByTags(String[] tags) {
		List<Post> allPosts = forumRepositoriy.findAll();

		List<DtoResponse> result = allPosts.stream()
				.filter(p -> Arrays.stream(tags).anyMatch(t->p.getTags().contains(t)))
				.map(p -> modelMapper.map(p, DtoResponse.class)).collect(Collectors.toList());
	
		return result;
	}

	@Override
	public List<DtoResponse> findPostsByPeriod(DtoPeriod dtoPeriod) {

		LocalDateTime first = LocalDateTime.of(dtoPeriod.getDateFrom(), LocalTime.of(0, 0));
		LocalDateTime second = LocalDateTime.of(dtoPeriod.getDateTo(), LocalTime.of(23, 59));
		List<Post> allPosts = forumRepositoriy.findAll();

		return allPosts.stream()

				.filter(p -> p.getDateCreated().isAfter(first) && p.getDateCreated().isBefore(second))
				.map(p -> modelMapper.map(p, DtoResponse.class)).collect(Collectors.toList());
	}

}
