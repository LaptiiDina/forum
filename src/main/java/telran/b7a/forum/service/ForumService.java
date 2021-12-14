package telran.b7a.forum.service;

import java.util.List;

import telran.b7a.forum.dto.AddCommentToPostDto;
import telran.b7a.forum.dto.DtoResponse;
import telran.b7a.forum.dto.RequsteDto;

public interface ForumService {
 DtoResponse addpost(RequsteDto requsteDto);
 DtoResponse findPostById(String id);
 DtoResponse deletePost(String id);
 DtoResponse updatePost(String id, RequsteDto requsteDto);
 void addLikeToPost(String id);
 DtoResponse AddCommentToPost(String id,String author, AddCommentToPostDto addCommentToPostDto);
 List<DtoResponse>  findPostsbyAuthor(String author);
}
