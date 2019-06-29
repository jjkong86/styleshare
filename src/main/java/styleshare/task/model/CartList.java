package styleshare.task.model;

import lombok.Data;

@Data
public class CartList {
    private long id;
    private long goods_id;
    private long goods_detail_id;
    private long amount;
	private String color;
	private String size;
	private long stock;
    private String name;
    private String provider;
    private long price;
    
    private String shipping_method;
    private int shipping_price;
    private boolean shipping_canBundle;
    
}
