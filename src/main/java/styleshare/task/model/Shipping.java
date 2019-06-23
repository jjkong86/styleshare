package styleshare.task.model;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Shipping {
	
    private String method;
    private Long price;
    private Boolean canBundle;
}
