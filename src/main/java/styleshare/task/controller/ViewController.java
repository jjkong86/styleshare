package styleshare.task.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import styleshare.task.service.CommerceService;

@RestController
public class ViewController {
	
    private final CommerceService commerceService;

    public ViewController(CommerceService commerceService) {
        this.commerceService = commerceService;
    }
	
    @GetMapping("/")
    public String index() throws IOException {
    	String res = "Hello World! This is index.";
        return res;
    }
}
