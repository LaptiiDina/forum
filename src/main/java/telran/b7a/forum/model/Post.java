package telran.b7a.forum.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(of = { "id" })
@Builder
@Document(collection = "forumPosts")
public class Post {
	String id;
	@Setter
	String title;
	@Setter
	String content;
	@Setter
	String author;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime dateCreated = LocalDateTime.now() ;
	@Singular
	Set<String> tags;
	int likes;
	@Singular
Set<Comments>comments =new HashSet<>();
	public Post(String title, String content, String author, Set<String> tags) {
		
		this.title = title;
		this.content = content;
		this.author = author;
		this.tags = tags;
		dateCreated = LocalDateTime.now();
		
		
	}
	
	public Post(String title, String content, Set<String> tags) {
		this.title=title;
		this.content=content;
		this.tags=tags;
		dateCreated = LocalDateTime.now();
		
		
	}
	
	public Post(String title, String content, String author) {
		this(title, content, author, new HashSet<>());
		
	}
	
	public void addLike() {
		likes++;
	}
	
	public boolean addComment(Comments comment) {
		return comments.add(comment);
	}
	
	public boolean addTag(String tag) {
		return tags.add(tag);
	}
	
	public boolean removeTag(String tag) {
		return tags.remove(tag);
	}
}
