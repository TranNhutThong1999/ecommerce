package com.tmdt.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "post")
public class Post extends Common{
	private String title;
	private Date time;
	@Column(name = "short_description")
	private String shortDescription;
	private StatePost state;
	private Timestamp timeExpire;
	private int pricePost;
	
	//relation
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL, orphanRemoval=true)
	private List<Image> images;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fee_id")
	private Fee fee;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="content_id")
	private Content content;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="address_id")
	private Address address;
	
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL, orphanRemoval=true)
	private List<FeedBack> feedBacks;
	
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval=true )
	private List<Action> actions;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	private int view;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public StatePost getState() {
		return state;
	}
	public void setState(StatePost state) {
		this.state = state;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public Fee getFee() {
		return fee;
	}
	public void setFee(Fee fee) {
		this.fee = fee;
	}
	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<FeedBack> getFeedBacks() {
		return feedBacks;
	}
	public void setFeedBacks(List<FeedBack> feedBacks) {
		this.feedBacks = feedBacks;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Timestamp getTimeExpire() {
		return timeExpire;
	}
	public void setTimeExpire(Timestamp timeExpire) {
		this.timeExpire = timeExpire;
	}
	public List<Action> getActions() {
		return actions;
	}
	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public int getPricePost() {
		return pricePost;
	}
	public void setPricePost(int pricePost) {
		this.pricePost = pricePost;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	
}
