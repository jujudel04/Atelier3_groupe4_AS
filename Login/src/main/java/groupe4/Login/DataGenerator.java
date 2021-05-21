package groupe4.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import groupe4.Login.Model.Login;
import groupe4.Login.Repository.LoginRepository;

@Component
public class DataGenerator implements ApplicationRunner {

	@Autowired
	private LoginRepository repository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Login login =new Login();
		login.setId(1);
		login.setLogin("test");
		login.setPasswd("test");
		this.repository.save(login);		
	}

}
