package groupe4.Login.Repisotory;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import groupe4.Login.Model.Login;

public interface LoginRepository extends CrudRepository<Login, Integer> {

	public List<Login> findByLogin(String login);

}
