package styleshare.task.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import lombok.extern.slf4j.Slf4j;
import styleshare.task.mapper.CommerceMapper;
import styleshare.task.model.GoodsArray;
import styleshare.task.model.GoodsConvert;
import styleshare.task.model.GoodsDetail;

@Slf4j
@Controller
public class ViewController {

	private final CommerceMapper commerceMapper;

    public ViewController(CommerceMapper commerceMapper) {
        this.commerceMapper = commerceMapper;
    }
	
    @GetMapping("/")
    public String index() throws IOException {
    	Gson gson = new Gson();
    	JsonReader reader = new JsonReader(new FileReader("src/main/resources/goods.json"));
    	GoodsArray data = gson.fromJson(reader, GoodsArray.class);
    	ArrayList<GoodsConvert> list = data.getGoods();
    	for (GoodsConvert goods : list) {
    		commerceMapper.insertGoods(goods);
    		GoodsDetail[] gds = goods.getOptions();
    		for (GoodsDetail gd : gds) {
				gd.setGoods_id(goods.getId());
				commerceMapper.insertGoodsDetail(gd);
			}
		}
        return "index";
    }
    
    public static void main(String[] args) throws FileNotFoundException {
    	Gson gson = new Gson();
    	JsonReader reader = new JsonReader(new FileReader("src/main/resources/goods.json"));
    	GoodsArray data = gson.fromJson(reader, GoodsArray.class);
    	ArrayList<GoodsConvert> list = data.getGoods();
    	for (GoodsConvert goods : list) {
    		GoodsDetail[] gds = goods.getOptions();
    		System.out.println(goods.toString());
    		for (GoodsDetail gd : gds) {
				gd.setGoods_id(goods.getId());
				System.out.println(gd.toString());
			}
		}
	}
}
