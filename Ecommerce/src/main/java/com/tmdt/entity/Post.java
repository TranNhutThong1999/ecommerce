package com.tmdt.entity;

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
	
	@Column(name = "short_description")
	private String shortDescription;
	private boolean browse;
	
	//relation
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	private List<Image> images;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fee_id")
	private Fee fee;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="content_id")
	private Content content;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="address_id")
	private Address address;
	
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	private List<Evaluated> evaluated;
	
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	private List<FeedBack> feedBacks;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
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
	public boolean isBrowse() {
		return browse;
	}
	public void setBrowse(boolean browse) {
		this.browse = browse;
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
	public List<Evaluated> getEvaluated() {
		return evaluated;
	}
	public void setEvaluated(List<Evaluated> evaluated) {
		this.evaluated = evaluated;
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
	
	
}
