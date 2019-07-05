package styleshare.task;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@MapperScan(basePackages = "styleshare.task")
//@ComponentScan(basePackages = "styleshare.task.request_logging")
@ServletComponentScan(basePackages = "styleshare.task.request_logging.filter")
@SpringBootApplication
@EnableAspectJAutoProxy
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
