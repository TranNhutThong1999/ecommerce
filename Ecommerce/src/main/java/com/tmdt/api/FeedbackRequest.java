package com.tmdt.api;

public class FeedbackRequest {
		private int idPost;
		private String comment;
		private int star;
		public int getIdPost() {
			return idPost;
		}
		public void setIdPost(int idPost) {
			this.idPost = idPost;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
		public int getStar() {
			return star;
		}
		public void setStar(int star) {
			this.star = star;
		}
		@Override
		public String toString() {
			return "FeedbackRequest [idPost=" + idPost + ", comment=" + comment + ", star=" + star + "]";
		}
		
		
}	
