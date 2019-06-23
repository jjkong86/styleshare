package styleshare.task;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import styleshare.task.mapper.CommerceMapper;
import styleshare.task.model.GoodsArray;
import styleshare.task.model.GoodsConvert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTests.class)
@MapperScan(basePackages = "styleshare.task.mapper")
public class ApplicationTests {

	@Autowired
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
    	GoodsArray data = gson.fromJson(reader, GoodsArray.class);
    	ArrayList<GoodsConvert> list = data.getGoods();
    	for (GoodsConvert goods : list) {
    		commerceMapper.insertGoods(goods);
		}
		
	}

}
