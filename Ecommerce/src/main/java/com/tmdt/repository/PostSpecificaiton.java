package com.tmdt.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.tmdt.dto.FilterDTO;
import com.tmdt.entity.Post;

public class PostSpecificaiton implements Specification<Post> {
	private FilterDTO filterDTO;

	public PostSpecificaiton(FilterDTO f) {
		this.filterDTO = f;
	}

	@Override
	public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Predicate p = cb.conjunction();
		if (filterDTO.getPrice().equals("0") && filterDTO.getAreage().equals("0")) {
			p.getExpressions().add(cb.equal(root.get("browse"), true));
			return p;
		} else {
			int price = Integer.valueOf(filterDTO.getPrice().substring(1));
			int areage = Integer.valueOf(filterDTO.getAreage().substring(1));
			if (filterDTO.getPrice().contains("<") && filterDTO.getAreage().contains("<")) {
				p.getExpressions().add(cb.and(cb.lessThanOrEqualTo(root.get("content").get("price"), price),
						cb.lessThanOrEqualTo(root.get("content").get("areage"), areage),
						cb.equal(root.get("address").get("provincial").get("id"), filterDTO.getIdProvincial()),
						cb.equal(root.get("browse"), true)));
				if (filterDTO.getIdWard() != 0) {
					p.getExpressions()
							.add(cb.and(cb.equal(root.get("address").get("ward").get("id"), filterDTO.getIdWard())));
				}
				if (filterDTO.getIdDistrict() != 0) {
					p.getExpressions().add(
							cb.and(cb.equal(root.get("address").get("district").get("id"), filterDTO.getIdDistrict())));
				}
				return p;
			}
			if (filterDTO.getPrice().contains(">") && filterDTO.getAreage().contains(">")) {
				p.getExpressions().add(cb.and(cb.greaterThanOrEqualTo(root.get("content").get("price"), price),
						cb.greaterThanOrEqualTo(root.get("content").get("areage"), areage),
						cb.equal(root.get("address").get("provincial").get("id"), filterDTO.getIdProvincial()),
						cb.equal(root.get("browse"), true)));
				if (filterDTO.getIdWard() != 0) {
					p.getExpressions().add(cb.equal(root.get("address").get("ward").get("id"), filterDTO.getIdWard()));
				}
				if (filterDTO.getIdDistrict() != 0) {
					p.getExpressions()
							.add(cb.equal(root.get("address").get("district").get("id"), filterDTO.getIdDistrict()));
				}
				return p;
			}
			if (filterDTO.getPrice().contains("<") && filterDTO.getAreage().contains(">")) {
				p.getExpressions().add(cb.and(cb.lessThanOrEqualTo(root.get("content").get("price"), price),
						cb.greaterThanOrEqualTo(root.get("content").get("areage"), areage),
						cb.equal(root.get("address").get("provincial").get("id"), filterDTO.getIdProvincial()),
						cb.equal(root.get("browse"), true)));
				if (filterDTO.getIdWard() != 0) {
					p.getExpressions().add(cb.equal(root.get("address").get("ward").get("id"), filterDTO.getIdWard()));
				}
				if (filterDTO.getIdDistrict() != 0) {
					p.getExpressions()
							.add(cb.equal(root.get("address").get("district").get("id"), filterDTO.getIdDistrict()));
				}
				return p;
			}
			if (filterDTO.getPrice().contains(">") && filterDTO.getAreage().contains("<")) {
				p.getExpressions().add(cb.and(cb.greaterThanOrEqualTo(root.get("content").get("price"), price),
						cb.lessThanOrEqualTo(root.get("content").get("areage"), areage),
						cb.equal(root.get("address").get("provincial").get("id"), filterDTO.getIdProvincial()),
						cb.equal(root.get("browse"), true)));
				if (filterDTO.getIdWard() != 0) {
					p.getExpressions().add(cb.equal(root.get("address").get("ward").get("id"), filterDTO.getIdWard()));
				}
				if (filterDTO.getIdDistrict() != 0) {
					p.getExpressions()
							.add(cb.equal(root.get("address").get("district").get("id"), filterDTO.getIdDistrict()));
				}
				return p;
			}
		}
		return null;
	}
}
