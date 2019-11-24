package styleshare.task.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import styleshare.task.mapper.CommerceMapper;
import styleshare.task.model.User;

@Service
@AllArgsConstructor
public class UserService {

	private final CommerceMapper commerceMapper;
	
	public User authenticate(String email, String password) {
		return commerceMapper.getUserInfo(User.builder().email(email).password(password).build());
	}

}
