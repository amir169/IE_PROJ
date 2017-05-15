package ir.rendan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="users")
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	private short enabled;
	@Column
	private String role;


	public UserInfo(String username, String password, String email, short enabled, String role) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.role = role;
	}

	public UserInfo(String username, String password, short enabled, String role) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
	}

	public UserInfo() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserName() {
		return username;
	}
	public void setUserName(String username) {
		this.username = username;
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
}
