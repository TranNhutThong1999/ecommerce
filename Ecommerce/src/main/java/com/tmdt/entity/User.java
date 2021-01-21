package com.tmdt.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "user")
public class User extends Common {

	@Column(name = "username")
	private String userName;

	private String password;

	private String image;
	
	private String name;

	private String email;

	private String phone;
	
	@Column(name="non_block")
	private boolean nonBlock;
	
	@Column(name="is_active")
	private boolean isActive;
	
	private int totalMoney;
	
	private String token;
	
	private Timestamp expire;
	
	private String address;
	
	//relation
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "User_Role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<Role>();
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<DepositHistory> depositHistories;

	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval=true)
	private List<Action> action;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval=true)
	private List<FeedBack> feedback;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval=true)//,orphanRemoval = true
	private List<Post> posts;
	
	public User() {
		super();
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public List<DepositHistory> getDepositHistories() {
		return depositHistories;
	}
	public void setDepositHistories(List<DepositHistory> depositHistories) {
		this.depositHistories = depositHistories;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public void generateToken() {
		this.token=UUID.randomUUID().toString();
	}
	public boolean isAfterTime() {
		if(this.expire == null) {
			return true;
		}
		Calendar time = Calendar.getInstance();
		Timestamp timetamp = new Timestamp(time.getTime().getTime());
		return timetamp.after(this.expire);
	}

	public void setTimeTokenFuture(int minutes) {
		Calendar time = Calendar.getInstance();
		time.add(Calendar.MINUTE, minutes);
		this.expire = new Timestamp(time.getTime().getTime());
	}
	public Timestamp getExpire() {
		return expire;
	}
	public void setExpire(Timestamp expire) {
		this.expire = expire;
	}
	public List<Action> getAction() {
		return action;
	}
	public void setAction(List<Action> action) {
		this.action = action;
	}
	public List<FeedBack> getFeedback() {
		return feedback;
	}
	public void setFeedback(List<FeedBack> feedback) {
		this.feedback = feedback;
	}
	
}
