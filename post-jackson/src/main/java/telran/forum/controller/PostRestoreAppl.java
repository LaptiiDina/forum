package telran.forum.controller;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import telran.forum.model.Post;

public class PostRestoreAppl {
	static ObjectMapper mapper;
	static {
		mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		Post post = mapper.readValue(new File("post.json"), Post.class);
		System.out.println(post);
	}

}
