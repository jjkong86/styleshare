package styleshare.task.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class Shipping {
	
    private String method;
    private Long price;
    private Boolean canBundle;
}
