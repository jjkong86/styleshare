package styleshare.task.response;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import styleshare.task.model.Cart;
@Data
@EqualsAndHashCode(callSuper=false)
public class CartResponse extends ApiCommonResponse {
	int count;
	List<Cart> list;
	
	public CartResponse() {}
}
