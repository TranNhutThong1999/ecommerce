package com.tmdt.dto;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.tmdt.entity.StatePost;

public class PostDTO extends CommonDTO{
	@NotBlank(message = "Username can not be blank")
	private String title;
	
	private long timeExpire;
	
	private String shortDescription;
	private int pricePost;
	private StatePost state;
	
	private List<ImageDTO> images;
	
	private FeeDTO fee;
	
	private ContentDTO content;
	
	@Valid
	private AddressDTO address;
	
	private List<EvaluatedDTO> evaluated;
	
	private List<Integer> idUserlike;
	
	//private List<FeedBackDTO> feedBacks;
	private int view;
	private int userId;
	
	private double totalStar;
	
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
	public List<ImageDTO> getImages() {
		return images;
	}
	public void setImages(List<ImageDTO> images) {
		this.images = images;
	}
	public FeeDTO getFee() {
		return fee;
	}
	public void setFee(FeeDTO fee) {
		this.fee = fee;
	}
	public ContentDTO getContent() {
		return content;
	}
	public void setContent(ContentDTO content) {
		this.content = content;
	}
	public AddressDTO getAddress() {
		return address;
	}
	public void setAddress(AddressDTO address) {
		this.address = address;
	}
	public List<EvaluatedDTO> getEvaluated() {
		return evaluated;
	}
	public void setEvaluated(List<EvaluatedDTO> evaluated) {
		this.evaluated = evaluated;
	}
//	public List<FeedBackDTO> getFeedBacks() {
//		return feedBacks;
//	}
//	public void setFeedBacks(List<FeedBackDTO> feedBacks) {
//		this.feedBacks = feedBacks;
//	}
	public double getTotalStar() {
		return totalStar;
	}
	public void setTotalStar(double totalStar) {
		this.totalStar = totalStar;
	}
	@Override
	public String toString() {
		return "PostDTO [evaluated=" + evaluated + ", totalStar=" + totalStar + ", getTitle()=" + getTitle()
				+ ", getShortDescription()=" + getShortDescription() + ", isBrowse()=" ;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<Integer> getIdUserlike() {
		return idUserlike;
	}
	public void setIdUserlike(List<Integer> idUserlike) {
		this.idUserlike = idUserlike;
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
	public long getTimeExpire() {
		return timeExpire;
	}
	public void setTimeExpire(long timeExpire) {
		this.timeExpire = timeExpire;
	}
	


}
