package styleshare.task.convert;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import styleshare.task.response.ApiCommonResponse;

public class ConvertUtil {
	static final Gson gson = new Gson();
	
	public static <T, S> T fromJson(S param, Class<T> responseType) {
		String str = "";
		if (!(param instanceof String)) {
			str = gson.toJson(param);
		}
		return gson.fromJson(str, new TypeToken<T>() {}.getType());
	}
	
	public static void main(String[] args) {
		ObjectMapper oMapper = new ObjectMapper();
		ApiCommonResponse res = new ApiCommonResponse();
		res.setError("error");
		
		Map<String, Object> map = oMapper.convertValue(res, Map.class);
        System.out.println(map);
        
        
        Map<String, Object> gsonMap = fromJson(res, Map.class);
        System.out.println(gsonMap);
        System.out.println(fromJson(res, Map.class));
        
        Set<String> set = new HashSet<>();
        SortedSet<Integer> sSet = new TreeSet<>();
        sSet.add(1);
        sSet.add(2);
        sSet.add(3);
        
        
        
	}
}
