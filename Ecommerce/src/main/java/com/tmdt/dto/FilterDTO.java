package com.tmdt.dto;

public class FilterDTO {
		private int idWard;
		private int idDistrict;
		private int idProvincial;
		private String areage;
		private String price;
		
		
		public FilterDTO(int idWard, int idDistrict, int idProvincial, String areage, String price) {
			super();
			this.idWard = idWard;
			this.idDistrict = idDistrict;
			this.idProvincial = idProvincial;
			this.areage = areage;
			this.price = price;
		}
		public int getIdWard() {
			return idWard;
		}
		public void setIdWard(int idWard) {
			this.idWard = idWard;
		}
		public int getIdDistrict() {
			return idDistrict;
		}
		public void setIdDistrict(int idDistrict) {
			this.idDistrict = idDistrict;
		}
		public int getIdProvincial() {
			return idProvincial;
		}
		public void setIdProvincial(int idProvincial) {
			this.idProvincial = idProvincial;
		}
		public String getAreage() {
			return areage;
		}
		public void setAreage(String areage) {
			this.areage = areage;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
		
}
