package com.tmdt.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "image")
public class Image extends Common {
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;

	
	public Image() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "Image [name=" + name + ", post=" + post + "]";
	}
	
}
