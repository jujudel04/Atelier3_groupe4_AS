package groupe4.User.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String login;
	private String passwd;
	@Column(name = "accountbalance")
	private Integer accountBalance;
	// @OneToMany(mappedBy="cards")
	@Column(name = "cards")
	private String cards; // save as format: "id1:id2:id3" String::split(':')

	public User() {
		super();
	}

	public User(Integer id, String name, String login, String passwd) {
		this();
		this.id = id;
		this.name = name;
		this.login = login;
		this.passwd = passwd;
		this.cards = "";
		this.accountBalance = 5000; // start with 5000

	}

	public Integer getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Integer accountBalance) {
		this.accountBalance = accountBalance;
	}

	public void updateBalance(Integer update) {
		this.accountBalance += update;
	}

	public void addCard(Integer card_id) {
		this.cards = this.cards + card_id + ":";
	}

	public void removeCard(Integer card_id) {
		List<Integer> listc = this.getUserCardList();
		listc.remove(card_id);
		this.cards = "";
		for (Integer id : listc) {
			this.addCard(id);
		}
	}

	public List<Integer> getUserCardList() {
		if (this.cards.length() > 0) {
			List<String> list = Arrays.asList(this.cards.split(":"));
			
			return list.stream().map(Integer::parseInt).collect(Collectors.toList());
		} else {
			ArrayList<Integer> toto = new ArrayList<Integer>();
			return toto;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "User [" + this.id + "]: name:" + this.name + ", login:" + this.login + ", passwd:" + this.passwd;
	}

}
