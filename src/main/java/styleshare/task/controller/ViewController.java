package styleshare.task.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import lombok.extern.slf4j.Slf4j;
import styleshare.task.model.GoodsArray;
import styleshare.task.model.GoodsConvert;

@Slf4j
@Controller
public class ViewController {

    @GetMapping("/")
    public String index() throws IOException {
        return "index";
    }
    
    public static void main(String[] args) throws IOException, ParseException {
    	Gson gson = new Gson();
    	JsonReader reader = new JsonReader(new FileReader("src/main/resources/goods.json"));
    	GoodsArray data = gson.fromJson(reader, GoodsArray.class);
    	ArrayList<GoodsConvert> list = data.getGoods();
    	for (GoodsConvert g : list) {
			System.out.println(g.toString());
		}
	}
}
