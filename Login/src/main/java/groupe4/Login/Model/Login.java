package groupe4.Login.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class Login {
	@Id
	@GeneratedValue
	private Integer id;
	private String login;
	private String passwd;

	public Login() {
		super();
	}

	public Login(Integer id, String login, String passwd) {
		this();
		this.id = id;
		this.login = login;
		this.passwd = passwd;

	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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