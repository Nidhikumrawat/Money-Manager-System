package com.nidhi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nidhi.entity.CategoryEntity;

@Repository
public interface CategoryRepository  extends JpaRepository<CategoryEntity,Long>{

	//select * from tbl_catagories where profile_id =?1
	List<CategoryEntity> findByProfileId(Long profileId);
	
	//select * from tbl_catagories where id = ?1 and profile_id =?2
	Optional<CategoryEntity> findByIdAndProfileId(Long id, Long profileId);
	
	List<CategoryEntity> findByTypeAndProfileId(String type, Long profileId);
	
	 Boolean existsByNameAndProfileId(String name, Long profileId);
}
