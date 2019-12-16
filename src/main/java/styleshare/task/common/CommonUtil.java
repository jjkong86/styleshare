package styleshare.task.common;

import java.util.List;
import java.util.StringJoiner;

import styleshare.task.request.PutGoodsToCartRequest;

public class CommonUtil {
	
	public static <T> String makeString(T t, List<T> list) {
		StringJoiner sj = new StringJoiner(", ");
		if (t instanceof PutGoodsToCartRequest) {
			for (T p : list) {
				sj.add(((PutGoodsToCartRequest) p).getName());
			}
		}
		return sj.toString();
	}
}
