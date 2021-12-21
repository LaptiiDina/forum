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
import telran.b7a.forum.model.Post;
import telran.b7a.forum.repositoriy.ForumRepositoriy;
import telran.b7a.security.SecurityContex;
import telran.b7a.security.UserProfile;
@Service
@Order(18)
public class DeletePostFilter implements Filter {

	
	SecurityContex contex;
	ForumRepositoriy forum;
	@Autowired
	public DeletePostFilter(SecurityContex contex,ForumRepositoriy forum) {
		
		this.contex = contex;
		this.forum = forum;
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
			Post post = forum.findById(uri[uri.length-1]).orElse(null);
			if(!post.getAuthor().equals(login)&& !user.getRoles().contains("Moderator")) {
				res.sendError(403);
				return;
			}
		}
		chain.doFilter(req, res);

	}
	private boolean checkEndPoints(String servletPath, String method) {
		
		return "Delete".equalsIgnoreCase(method) && servletPath.matches("[/]forum[/]post[/]\\w+[/]?");
	}

}
//"forum/post/{id}"Put, Delete