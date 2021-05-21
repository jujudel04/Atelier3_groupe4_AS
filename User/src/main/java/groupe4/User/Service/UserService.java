package groupe4.User.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import groupe4.User.Repository.UserRepository;
import groupe4.User.Model.User;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	public static List<Integer> authenticateUser = new ArrayList<Integer>();

	public static void autorizeUser(Integer userid) {
		UserService.authenticateUser.add(userid);
	}

	public static boolean isAuthenticate(int id) {
		return UserService.authenticateUser.contains(id);
	}

	UserService() {
	}

	public void saveUser(User h) {
		userRepository.save(h);
	}

	public Integer signup(String login, String passwd) {
		List<User> users = userRepository.findByLogin(login);
		for (User user : users) {
			if (user.getPasswd().equals(passwd)) {
				UserService.autorizeUser(user.getId());
				return user.getId();
			}
		}
		return null;
	}

	public User getUser(int id) {
		Optional<User> hOpt = userRepository.findById(id);
		if (hOpt.isPresent()) {
			return hOpt.get();
		}
		return null;
	}


}
