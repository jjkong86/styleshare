package styleshare.task;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TestClass {
	String uid;
	double customfee;
	List<Result> list;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Result {
		String address;
		double amount;
	}
}
