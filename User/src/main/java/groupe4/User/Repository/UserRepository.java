package groupe4.User.Repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import groupe4.User.Model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	public List<User> findByLogin(String login);

}
