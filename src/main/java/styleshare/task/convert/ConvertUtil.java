package styleshare.task.convert;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
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
}
