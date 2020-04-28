package styleshare.task;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import styleshare.task.request.PutGoodsToCartRequest;

//@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
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
		mockMvc.perform(get("/goods").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void 상품_카트에_담기() throws Exception {
		PutGoodsToCartRequest param = PutGoodsToCartRequest.builder().amount(3).name("JAVA Round T-Shirts").goods_id(2)
				.goods_detail_id(2001).build();
		
		mockMvc.perform(post("/carts/{goodsId}", 2)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(param)))
		.andExpect(status().isOk()).andDo(print());
		
		mockMvc.perform(get("/carts/{goodsId}", 2).contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
		.andExpect(status().isOk());
	}
}
