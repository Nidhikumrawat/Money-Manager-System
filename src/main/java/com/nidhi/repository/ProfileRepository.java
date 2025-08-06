package com.nidhi.repository;
import com.nidhi.entity.ProfileEntity;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity,Long>{

	//select * from tbl_profiles where email =?1
	Optional<ProfileEntity> findByEmail(String email);
	
	boolean existsByEmail(String email);
	
	 Optional<ProfileEntity> findByActivationToken(String activationToken) ;
	
}
