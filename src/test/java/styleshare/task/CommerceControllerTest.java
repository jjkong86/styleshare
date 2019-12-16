package styleshare.task;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import styleshare.task.controller.CommerceController;
import styleshare.task.request.PutGoodsToCartRequest;
import styleshare.task.response.GoodsListRespose;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@WebAppConfiguration
public class CommerceControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

	@Test
	public void 상품_리스트() throws Exception {
		mockMvc.perform(get("/goods")).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void 상품_카트에_담기() throws Exception {
//		params={"amount":"3","name":"JAVA Round T-Shirts","goods_id":"2","goods_detail_id":"2001"}
		PutGoodsToCartRequest param = PutGoodsToCartRequest.builder()
				.amount(3)
				.name("JAVA Round T-Shirts")
				.goods_id(2)
				.goods_detail_id(2001)
				.build();
		mockMvc.perform(post("/put/cart/2")).andExpect(status().isOk()).andDo(print());
	}
}
