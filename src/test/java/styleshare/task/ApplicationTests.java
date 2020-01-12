package styleshare.task;

import static styleshare.task.constants.Constants.JSON_FILE_PATH;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import lombok.extern.slf4j.Slf4j;
import styleshare.task.mapper.CommerceMapper;
import styleshare.task.model.Goods;
import styleshare.task.model.GoodsDetail;
import styleshare.task.response.GoodsListRespose;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@MapperScan(basePackages = "styleshare.task.mapper")
public class ApplicationTests {

	@Autowired
	private CommerceMapper commerceMapper;

	@Test
	public void goodsInsertToJsonFile() throws FileNotFoundException {
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new FileReader(JSON_FILE_PATH));
		GoodsListRespose data = gson.fromJson(reader, GoodsListRespose.class);
		List<Goods> list = data.getGoods();
		for (Goods goods : list) {
			log.info(goods.toString());
			commerceMapper.insertGoods(goods);
			List<GoodsDetail> gds = goods.getOptions();
			for (GoodsDetail gd : gds) {
				log.info(gd.toString());
				gd.setGoods_id(goods.getId());
				commerceMapper.insertGoodsDetail(gd);
			}
		}
	}

}
