package styleshare.task.service;

import static styleshare.task.constants.Constants.JSON_FILE_PATH;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import lombok.extern.slf4j.Slf4j;
import styleshare.task.mapper.CommerceMapper;
import styleshare.task.model.Goods;
import styleshare.task.model.GoodsDetail;
import styleshare.task.response.GoodsListRespose;
import styleshare.task.response.GoodsRespose;

@Slf4j
@Service
public class CommerceService {

	private final CommerceMapper commerceMapper;

    public CommerceService(CommerceMapper commerceMapper) {
        this.commerceMapper = commerceMapper;
    }

	public GoodsListRespose goodsAll() throws FileNotFoundException {
		GoodsListRespose goodsList = new GoodsListRespose();
		List<Goods> goods = commerceMapper.goodsAll();
		
    	if (goods.size() < 1) { //중복 insert 방지
    		log.info(" ===== goods is empty. insert into db from \"goods.json\" ===== ");
    		return goodsInsertToJsonFile();
    	}
    	
    	List<GoodsDetail> goodsDetail = commerceMapper.goodsDetailAll();
    	log.info(goodsDetail.toString());
    	
    	Map<Long, Goods> map = new HashMap<>();
    	for (Goods g : goods) {
			map.put(g.getId(), g);
			goodsList.getGoods().add(g);
		}
    	
    	for (int i = 0; i < goodsDetail.size(); i++) {
    		long id = goodsDetail.get(i).getGoods_id();
			if (map.containsKey(id)) {
				map.get(id).getOptions().add(goodsDetail.get(i));
			}
		}
    	
		return goodsList;
	}
	
	public GoodsListRespose goodsInsertToJsonFile() throws FileNotFoundException {
    	Gson gson = new Gson();
    	JsonReader reader = new JsonReader(new FileReader(JSON_FILE_PATH));
    	GoodsListRespose data = gson.fromJson(reader, GoodsListRespose.class);
    	List<Goods> list = data.getGoods();
    	for (Goods goods : list) {
    		commerceMapper.insertGoods(goods);
    		List<GoodsDetail> gds = goods.getOptions();
    		for (GoodsDetail gd : gds) {
				gd.setGoods_id(goods.getId());
				commerceMapper.insertGoodsDetail(gd);
			}
		}
    	return data;
	}

	public GoodsRespose goodsToId(int id) {
		GoodsRespose goods = commerceMapper.goodsToId();
		
		return null;
	}

}
