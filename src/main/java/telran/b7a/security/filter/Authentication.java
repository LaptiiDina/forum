package telran.b7a.security.filter;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.mapping.Unwrapped.Nullable;
import org.springframework.stereotype.Service;

import telran.b7a.accaunt.model.User;
import telran.b7a.accaunt.repositoriy.AccauntRepositoriy;
import telran.b7a.security.SecurityContex;
import telran.b7a.security.UserProfile;

@Service
@Order(10)
public class Authentication implements Filter {
	
	AccauntRepositoriy repository;
	SecurityContex contex;
     @Autowired
	public Authentication(AccauntRepositoriy repository,SecurityContex contex) {
		this.repository = repository;
		this.contex = contex;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
//		System.out.println(req.getMethod());
//		System.out.println(req.getServletPath());
		if (checkEndPoints(req.getServletPath(),req.getMethod())) {
			String token = req.getHeader("Authorization");
			if (token == null) {
				res.sendError(401, "Header Autorization not found");
				return;
			}
			String[] credential = getCrendetials(token).orElse(null);
			if (credential == null || credential.length < 2) {
				res.sendError(401, "Token not valid");
				return;
			}
			User user = repository.findById(credential[0]).orElse(null);
			if (user == null) {
				res.sendError(401, "User not found");
				return;
			}
			if (!BCrypt.checkpw(credential[1], user.getPassword())) {
				res.sendError(401, "User or password not valid");
				return;
			} 
			req = new WrappedRequest(req, credential[0]);
			UserProfile userProf = UserProfile.builder()
					.login(user.getLogin())
					.password(user.getPassword())
					.roles(user.getRoles())
					.build();
			contex.addUser(userProf);
		}
		chain.doFilter(req, res);
		

	}

	private boolean checkEndPoints(String servletPath, String method) {
		
		return !(("POST".equalsIgnoreCase(method)&& servletPath.matches("[/]account[/]register[/]?"))
				|| (servletPath.matches("[/]forum[/]posts([/]\\w+)+[/]?")));//([/]\\w+)+   1 или много раз, это комбинация
	}

	private Optional<String[]>  getCrendetials(String token) {
		String[]res =null;
		try {
			token = token.split(" ")[1];
			byte[] bytesCode = Base64.getDecoder().decode(token);
			token = new String(bytesCode);
			res = token.split(":");
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
return Optional.ofNullable(res);
	}
	private class WrappedRequest extends HttpServletRequestWrapper{
		String login;

		public WrappedRequest(HttpServletRequest request, String login) {
			super(request);
			this.login = login;
		}
		@Override
		public Principal getUserPrincipal() {
			return ()-> login;
	}
	}

}
