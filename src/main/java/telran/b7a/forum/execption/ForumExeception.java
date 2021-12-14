package telran.b7a.forum.execption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
@NoArgsConstructor
public class ForumExeception extends RuntimeException{/**
	 * 
	 */
	private static final long serialVersionUID = 5260166240859388667L;
	  public ForumExeception (String id) {
			super("Student with id "+id+" not found");
		}

}
