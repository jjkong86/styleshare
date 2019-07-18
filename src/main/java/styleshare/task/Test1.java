package styleshare.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Stream;

import com.google.gson.internal.LinkedTreeMap;

import styleshare.task.TestClass.Result;

public class Test1 {
	public static void main(String[] args) {
		TestClass test = new TestClass();
		List<TestClass.Result> list = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			list.add(Result.builder().address(String.valueOf(i)+"abc").amount(i).build());
		}
		test.setList(list);
//		System.out.println(test.toString());
		
    	List<Result> items = test.getList();
    	String[] params = new String[2];
    	
    	StringBuilder sb = new StringBuilder();
    	StringJoiner sj = new StringJoiner(", ");
    	items.forEach(item->sj.add("{\""+item.getAddress() +"\":"+ item.getAmount()+"}"));
//    	sb.append("\"]\"");
    	sb.append(sj.toString());
    	params[1] = sb.toString();
    	System.out.println(sb.toString());
    	
    	LinkedTreeMap<String, Object> map = new LinkedTreeMap<>();
    	map.put("aaa", 123);
    	map.put("bbb", "bnbb");
    	map.forEach((K, V) -> System.out.println(K + "::"+V));
	}
}
