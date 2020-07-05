package styleshare.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("styleshare.task.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo())
                .globalResponseMessage(RequestMethod.GET,
                        Arrays.asList(
                                new ResponseMessageBuilder().code(500).message("Internal Server Error")
                                        .responseModel(new ModelRef("Error")).build(),
                                new ResponseMessageBuilder().code(400).message("Bad Request").build(),
                                new ResponseMessageBuilder().code(404).message("Not Found").build()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Style Share")
                .description("API documentation for Style Share")
                .version("1.0.0")
                .build();
    }
}

