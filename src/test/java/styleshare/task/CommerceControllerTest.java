package styleshare.task;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import styleshare.task.controller.CommerceController;
import styleshare.task.service.CommerceService;

@RunWith(SpringRunner.class)
@WebMvcTest(CommerceController.class)
@EnableAutoConfiguration
public class CommerceControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private CommerceService commerceService;
    
    @Test
    public void testInitCreationForm() throws Exception {
    	
        mockMvc.perform(get("/goods"))
            .andExpect(status().isOk())
            .andDo(print());
    }
}
