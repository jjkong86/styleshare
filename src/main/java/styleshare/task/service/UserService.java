package styleshare.task.service;

import java.util.List;

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

	public List<String> getAuthorities(User user) {
		return commerceMapper.getAuthorities(user);
	}
}
