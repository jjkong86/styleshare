//package styleshare.task.request_logging.filter;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//
//import lombok.extern.slf4j.Slf4j;
//import styleshare.task.request_logging.ReadableRequestWrapper;
//
//@Slf4j
//@WebFilter(urlPatterns = "/*")
//public class ReadableRequestWrapperFilter implements Filter {
//	@Override
//	public void init(FilterConfig filterConfig) {
//		// Do nothing
//	}
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//		throws IOException, ServletException {
//		ReadableRequestWrapper wrapper = new ReadableRequestWrapper((HttpServletRequest)request);
//		chain.doFilter(wrapper, response);
//	}
//
//	@Override
//	public void destroy() {
//		// Do nothing
//	}
//}
