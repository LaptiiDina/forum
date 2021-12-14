package telran.b7a.forum.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoResponse {
	String id;
	String title;

	String content;
	String author;
	@JsonFormat(pattern = "yyy-MM-dd'T'HH:mm:ss")
	LocalDateTime dateCreated;
	@Singular
	Set<String> tags;
	Integer likes;
	@Singular
	List<Comments> comments;
}
