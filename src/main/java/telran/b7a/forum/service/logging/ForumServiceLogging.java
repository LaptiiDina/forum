package telran.b7a.forum.service.logging;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import telran.b7a.forum.dto.DtoResponse;

@Aspect
@Slf4j(topic = "Post service")
@Service
public class ForumServiceLogging {
	@Pointcut("execution(public java.util.List<telran.b7a.forum.dto.DtoResponse> telran.b7a.forum.service.ForumServiceImp.find*(..))")
	public void bulkingFind() {
	}

	@Around("bulkingFind()")
	public Object bulkingFindLogging(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		long t1 = System.currentTimeMillis();
		Object retVal = pjp.proceed(args);
		long t2 = System.currentTimeMillis();
		log.info("method - {}, duration = {}", pjp.getSignature().toLongString(), (t2 - t1));
		return retVal;
	}

	@Pointcut("execution(public telran.b7a.forum.dto.DtoResponse telran.b7a.forum.service.ForumServiceImp.findPostById(String)) && args(id)")
	public void findById(String id) {}
	
	@Before("findById(id)")
	public void getPostLogging(String id) {
		log.info("post with id {} requested",id);
		
	}
	
	@Pointcut("@annotation(PostLogger) && args(id,..)")
	public void annoted(String id) {
		
	}
	@AfterReturning("annoted(id)")
	public void updatePostLogging(String id) {
		log.info("post with id {} updated",id);
	}

}
