package com.scb.borrower.information.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scb.borrower.information.model.BorrowerInformation;

@Repository
public interface BorrowerInformationRepository extends JpaRepository<BorrowerInformation, Long>{

}
