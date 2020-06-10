package styleshare.task.request_logging.aop;

import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LoggerAspect {
	@Pointcut("execution(* styleshare.task..*Controller.*(..))") // 이런 패턴이 실행될 경우 수행
	public void loggerPointCut() {
	}

	@Around("loggerPointCut()")
	public Object methodLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = proceedingJoinPoint.proceed();
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); // request 정보를 가져온다.

        String controllerName = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = proceedingJoinPoint.getSignature().getName();

        Map<String, Object> params = new LinkedHashMap<>();

        try {
            params.put("request_uri", request.getRequestURI());
            params.put("http_method", request.getMethod());
            params.put("controller", controllerName);
            params.put("method", methodName);
            params.put("params", getParams(request));
            params.put("log_time", new Date());
        } catch (Exception e) {
            log.error("LoggerAspect error", e);
        }
        log.info("params : {}", new GsonBuilder().setPrettyPrinting().create().toJson(params)); // param에 담긴 정보들을 한번에 로깅한다.

        return result;

    }
	
	@AfterReturning(value = "execution(* styleshare.task..*Controller.*(..))", returning = "result")
	public void onAfterReturningLogHandler(JoinPoint joinPoint, Object result) {
		Object[] obj = joinPoint.getArgs();
		log.info("reslut : {}", Arrays.toString(obj) + "\n");
	}

	/**
	 * request 에 담긴 정보를 JSONObject 형태로 반환한다.
	 * @param request
	 * @return
	 */
	private static JSONObject getParams(HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String param = params.nextElement();
			String replaceParam = param.replaceAll("\\.", "-");
			jsonObject.put(replaceParam, request.getParameter(param));
		}
		return jsonObject;
	}
}
