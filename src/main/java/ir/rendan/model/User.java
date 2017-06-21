package ir.rendan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.rendan.util.StringGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;
	@Column
	@JsonIgnore
	private String password;
	@Column(unique = true)
	@JsonIgnore
	private String email;
	@Column
	@JsonIgnore
	private short enabled;
	@Column
	@JsonIgnore
	private String role;
	@Column
	@JsonIgnore
	private String activationCode;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public short getEnabled() {
		return enabled;
	}
	public void setEnabled(short enabled) {
		this.enabled = enabled;
	}

	public void validate()
	{
		this.setActivationCode(null);
		this.setEnabled(new Short("1"));
	}

	public User(){}

	public User(String username,String password,String email)
	{
		this.email = email;
		this.username = username;
		this.password = password;

		this.enabled = new Short("0");
		this.role = "USER";
		this.activationCode = StringGenerator.generateValidationCode();
	}
}
