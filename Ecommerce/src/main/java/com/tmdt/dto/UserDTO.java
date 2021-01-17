package com.tmdt.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tmdt.annotation.IsExist;

public class UserDTO extends CommonDTO{
	@NotBlank(message = "Username can not be blank")
	@IsExist
	@Size(message = "Between 6 and 20 characters")
	private String userName;
	
	private String name;
	private String image;
	
	@NotBlank(message = "ConfirmPassword can not be blank")
	@Size(message = "Between 9 and 11 characters")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String confirmPassword;
	
	@NotBlank(message = "Password can not be blank")
	@Size(message = "Between 9 and 11 characters")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	@NotBlank(message = "Email can not be blank")
	@Email(message = "Wrong email ")
	private String email;
	
	@NotBlank(message = "Password can not be blank")
	@Size(message = "Between 9 and 11 characters")
	private String phone;
	
	private boolean nonBlock;
	
	private boolean isActive;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private int totalMoney;
	
	private String address;
	
	private List<RoleDTO> roles = new ArrayList<RoleDTO>();
	
	private List<DepositHistoryDTO> depositHistories;
	
	private List<TransactionHistoryDTO> histories;

	private List<ActionDTO> actions;
	
	private List<PostDTO> posts;
	
	public UserDTO() {
		super();
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isNonBlock() {
		return nonBlock;
	}
	public void setNonBlock(boolean nonBlock) {
		this.nonBlock = nonBlock;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public int getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<RoleDTO> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}
	public List<DepositHistoryDTO> getDepositHistories() {
		return depositHistories;
	}
	public void setDepositHistories(List<DepositHistoryDTO> depositHistories) {
		this.depositHistories = depositHistories;
	}
	public List<TransactionHistoryDTO> getHistories() {
		return histories;
	}
	public void setHistories(List<TransactionHistoryDTO> histories) {
		this.histories = histories;
	}
	public List<ActionDTO> getActions() {
		return actions;
	}
	public void setActions(List<ActionDTO> actions) {
		this.actions = actions;
	}
	
	public String getPassword() {
		return password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean confirmPassword() {
		return this.password.equals(this.confirmPassword);
	}

	@Override
	public String toString() {
		return "UserDTO [userName=" + userName + ", name=" + name + ", image=" + image + ", confirmPassword="
				+ confirmPassword + ", password=" + password + ", email=" + email + ", phone=" + phone + ", nonBlock="
				+ nonBlock + ", isActive=" + isActive + ", totalMoney=" + totalMoney + ", address=" + address
				+ ", roles=" + roles + ", depositHistories=" + depositHistories + ", histories=" + histories
				+ ", actions=" + actions + "]";
	}

	public List<PostDTO> getPosts() {
		return posts;
	}

	public void setPosts(List<PostDTO> posts) {
		this.posts = posts;
	}




}
