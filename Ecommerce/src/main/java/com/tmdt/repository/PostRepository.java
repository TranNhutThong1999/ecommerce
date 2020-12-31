package com.tmdt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tmdt.entity.Post;
import com.tmdt.entity.StatePost;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>, JpaSpecificationExecutor<Post> {
	// Page<Post> findAllByBrowse(Pageable p);
	Optional<Post> findOneById(int id);

	@Query(value = "SELECT post.* FROM post ,address,ward,district,provincial,fee\r\n"
			+ "where post.address_id=address.id and address.ward_id = ward.id and address.district_id =district.id and address.provincial_id =provincial.id and post.fee_id = fee.id\r\n"
			+ "and ward.id = :ward and district.id = :district and provincial.id = :provincial and post.state='1' order by fee.price desc limit 10", nativeQuery = true)
	List<Post> findAllRef(@Param("ward") int ward, @Param("district") int district,
			@Param("provincial") int provincial);

	List<Post> findAll(Sort sort);

	Page<Post> findByUser_IdAndState(Pageable p, int id, StatePost b);
	Page<Post> findByUser_Id(Pageable p, int id);
	
}
