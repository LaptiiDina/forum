package telran.b7a.forum.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat;

import telran.b7a.forum.dto.AddCommentToPostDto;

import telran.b7a.forum.dto.DtoResponse;
import telran.b7a.forum.dto.DtoResponse;
import telran.b7a.forum.dto.RequsteDto;
import telran.b7a.forum.execption.ForumExeception;
import telran.b7a.forum.repositoriy.ForumRepositoriy;
import telran.forum.model.Comments;
import telran.forum.model.Post;

@Service
public class ForumServiceImp implements ForumService {
	@Autowired
	ForumRepositoriy forumRepositoriy;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public DtoResponse addpost(RequsteDto requsteDto) {

		Post post = modelMapper.map(requsteDto, Post.class);
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
		 
		
		return forumRepositoriy.findAll()
				.stream()
				.filter(p -> p.getAuthor().equals(author))
				.map(p-> modelMapper.map(p,DtoResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	public DtoResponse updatePost(String id, RequsteDto requsteDto) {
	DtoResponse response =	deletePost(id);
		Post post = new Post(id,requsteDto.getTitle(),
				requsteDto.getContent(),
				response.getAuthor(),
				LocalDateTime.now(),
				requsteDto.getTags(),0,null);
		DtoResponse result = modelMapper.map(post, DtoResponse.class);	
		return result;
	}

	@Override
	public void addLikeToPost(String id) {
		Post post = forumRepositoriy.findById(id).orElseThrow(() -> new ForumExeception(id));
		post.addLike();

	}

	@Override
	public DtoResponse AddCommentToPost(String id, String author, AddCommentToPostDto addCommentToPostDto) {
		Post post = forumRepositoriy.findById(id).orElseThrow(() -> new ForumExeception(id));
		Comments comment = new Comments(post.getAuthor()
				,addCommentToPostDto.getMessage()
				,LocalDateTime.now(),0);
		post.addComment(comment);
		DtoResponse result = modelMapper.map(post, DtoResponse.class);
		return result;
	}


}
