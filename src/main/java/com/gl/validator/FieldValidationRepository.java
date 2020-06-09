package com.gl.validator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldValidationRepository extends JpaRepository<FieldValidation,Integer>,  JpaSpecificationExecutor<FieldValidation>{
	
	public FieldValidation findByFieldName(String fieldName);

}
