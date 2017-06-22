package ir.rendan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
	@Column
	private String name;
	@Column
	private String university;
	@Column
	private String studentCode;
	@Column
	private String major;
	@Column
	private String studentLevel;
	@Column
	private String imageAddress;

	public String getImageAddress() {
		return imageAddress;
	}

	public void setImageAddress(String imageAddress) {
		this.imageAddress = imageAddress;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getStudentCode() {
		return studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getStudentLevel() {
		return studentLevel;
	}

	public void setStudentLevel(String studentLevel) {
		this.studentLevel = studentLevel;
	}

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
