package com.jsp.spring_boot_simple_project.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.jsp.spring_boot_simple_project.entity.Doctors;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Component
public class DoctorsSpecifications {

    public Specification<Doctors> findByCriteriaSpecilized(String specilization) {
    	
       //root provides access to Doctors entity; CriteriaQuery represent actual query; CriteriaBuilder used to construct Predicate object ,which represent condition of query.
       return (Root<Doctors> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> { //lambda expression
             
    	   //A list of Predicate Object is created to hold the condition of the query.
    	   List<Predicate> predicates = new ArrayList<>();

         if (specilization != null) {
           predicates.add(criteriaBuilder.equal(root.get("specialization"), specilization));
         }
            // for 'doctors' entity to be included in result.
       return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
       };
    }
}
