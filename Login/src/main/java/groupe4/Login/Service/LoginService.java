package groupe4.Login.Service;

import java.awt.PageAttributes.MediaType;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;




@Service
public class LoginService {
	@Autowired
	
	public static List<Integer> authenticateUser = new ArrayList<Integer>();


	LoginService(){
		
	}

	

	public boolean signup(String login, String passwd) {
		AuthDto dto = new AuthDto();
		dto.setLogin(login);
		dto.setPasswd(passwd);
		ResponseEntity<Boolean> reponse = new RestTemplate().postForEntity("http://localhost:8080/user/signup", dto , Boolean.class);
		
		return reponse.getBody();
	}

	
	public static class AuthDto {
		private String login;
		private String passwd;
		
		public AuthDto() {}
		
		public String getLogin() {
			return login;
		}
		public void setLogin(String login) {
			this.login = login;
		}
		public String getPasswd() {
			return passwd;
		}
		public void setPasswd(String passwd) {
			this.passwd = passwd;
		}
		
		
	}

}