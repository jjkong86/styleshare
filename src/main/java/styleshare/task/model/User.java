package styleshare.task.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class User {
	private Integer userId;
	private String email;
	private String password;
	private String status;
}
