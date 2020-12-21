package com.tmdt.dto;


public class ImageDTO extends CommonDTO {
	private String name;
	private int postId;

	
	public ImageDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	@Override
	public String toString() {
		return "ImageDTO [name=" + name + ", postId=" + postId + ", getName()=" + getName() + ", getPostId()="
				+ getPostId() + ", getId()=" + getId() + ", getCreatedDate()=" + getCreatedDate() + ", getCreatedBy()="
				+ getCreatedBy() + ", getModifiedDate()=" + getModifiedDate() + ", getModifedBy()=" + getModifedBy()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}


}
