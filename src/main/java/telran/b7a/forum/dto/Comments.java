package telran.b7a.forum.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
public class Comments {
	String user;
	String message;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime dateCreated;
	Integer likes;
	
}
