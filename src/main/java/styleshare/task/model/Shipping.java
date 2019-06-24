package styleshare.task.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Shipping {
	
    private String method;
    private long price;
    private Boolean canBundle;
}
