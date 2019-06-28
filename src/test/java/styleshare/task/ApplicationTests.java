package styleshare.task;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import styleshare.task.mapper.CommerceMapper;
import styleshare.task.model.Goods;
import styleshare.task.model.GoodsDetail;
import styleshare.task.response.GoodsListRespose;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTests.class)
@MapperScan(basePackages = "styleshare.task.mapper")
public class ApplicationTests {

	@MockBean
	private CommerceMapper commerceMapper;
	
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }
	
	
	@Test
	public void jsonToParseAndInsert() throws FileNotFoundException {
    	Gson gson = new Gson();
    	JsonReader reader = new JsonReader(new FileReader("src/main/resources/goods.json"));
    	GoodsListRespose data = gson.fromJson(reader, GoodsListRespose.class);
    	List<Goods> list = data.getGoods();
    	for (Goods goods : list) {
    		System.out.println(goods.toString());
    		for (int i = 0; i < goods.getOptions().size(); i++) {
				System.out.println(goods.getOptions().get(i));
			}
    		System.out.println("=========================================================");
		}
		
	}

}
