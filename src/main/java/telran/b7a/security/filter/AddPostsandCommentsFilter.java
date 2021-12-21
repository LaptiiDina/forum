package telran.b7a.security.filter;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import telran.b7a.accaunt.repositoriy.AccauntRepositoriy;
import telran.b7a.security.SecurityContex;
import telran.b7a.security.UserProfile;
@Service
@Order(16)
public class AddPostsandCommentsFilter implements Filter {


	AccauntRepositoriy repository;
	SecurityContex contex;
	
	@Autowired
	public AddPostsandCommentsFilter(AccauntRepositoriy repository, SecurityContex contex) {
		this.repository = repository;
		this.contex = contex;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		Principal principal = req.getUserPrincipal();
		
		if(checkEndPoints(req.getServletPath(),req.getMethod())) {
			UserProfile user = contex.getUser(principal.getName());
			String login = user.getLogin();
			String[]uri = req.getRequestURI().split("/");
			if(!uri[uri.length-1].equals(login)) {
				res.sendError(403);
				return;
			}
			
		}
		chain.doFilter(req, res);
		

	}

	private boolean checkEndPoints(String servletPath, String method) {
		///forum/post/{id}/comment/{author}
		///forum/post/{author}
		return ("POST".equalsIgnoreCase(method)&&servletPath.matches("[/]forum[/]post[/]\\w+[/]?")) ||servletPath.matches("[/]forum[/]post[/]\\w+[/]comment[/]\\w+[/]?") ;
	}

}



















