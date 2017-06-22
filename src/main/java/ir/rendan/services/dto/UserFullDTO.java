package ir.rendan.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.rendan.model.User;

/**
 * Created by Amir Shams on 6/23/2017.
 */
public class UserFullDTO {

    @JsonProperty
    private String username;

    @JsonProperty
    private String name;

    @JsonProperty
    private String university;

    @JsonProperty
    private String studentCode;

    @JsonProperty
    private String major;

    @JsonProperty
    private String studentLevel;

    @JsonProperty
    private String previousPassword;

    @JsonProperty
    private String newPassword;

    @JsonProperty
    private String imageAddress;

    @JsonProperty
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPreviousPassword() {
        return previousPassword;
    }

    public void setPreviousPassword(String previousPassword) {
        this.previousPassword = previousPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public static UserFullDTO loadFrom(User user) {
        UserFullDTO result = new UserFullDTO();

        result.setName(user.getName());
        result.setMajor(user.getMajor());
        result.setUsername(user.getUsername());
        result.setStudentCode(user.getStudentCode());
        result.setStudentLevel(user.getStudentLevel());
        result.setUniversity(user.getUniversity());
        result.setImageAddress(user.getImageAddress());
        result.setEmail(user.getEmail());

        return result;
    }
}
