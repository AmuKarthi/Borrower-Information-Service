package com.scb.borrower.information.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scb.borrower.information.model.LoanInformation;

@Repository
public interface LoanInformationRepository extends JpaRepository<LoanInformation, Long> {

	@Query(value = "select distinct b.BORROWER_FULL_NAME,l.LOAN_NUMBER,l.LOAN_AMOUNT,l.LOAN_TENURE,l.LOAN_INTEREST,l.LOAN_ID,b.BORROWER_ID "
			+ "from borrower_details b, loan_details l " + "where l.BORROWER_ID = b.BORROWER_ID "
			+ "and b.BORROWER_FULL_NAME=:fullName "
			+ "and l.LOAN_AMOUNT=:loanAmount "
			+ "and l.LOAN_NUMBER=:loanNumber", nativeQuery = true)
	List<LoanInformation> filterByConstraints( @Param("fullName") String fullName, 
			@Param("loanAmount") Long loanAmount,
			@Param("loanNumber") String loanNumber);
 }
