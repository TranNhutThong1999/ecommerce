package com.tmdt.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tmdt.annotation.Address;

public class PostDTO extends CommonDTO{
	@NotBlank(message = "Username can not be blank")
	private String title;
	
	private String shortDescription;
	
	private boolean browse;
	
	private List<ImageDTO> images;
	
	private FeeDTO fee;
	
	private ContentDTO content;
	
	@Valid
	private AddressDTO address;
	
	private List<EvaluatedDTO> evaluated;
	
	//private List<FeedBackDTO> feedBacks;
	
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
	public boolean isBrowse() {
		return browse;
	}
	public void setBrowse(boolean browse) {
		this.browse = browse;
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

}
