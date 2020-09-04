package com.scb.borrower.information.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scb.borrower.information.model.LoanInformation;
import com.scb.borrower.information.repository.LoanInformationRepository;

@Component
public class BorrowerInformationDAO {

	@Autowired
	LoanInformationRepository loanInfoRepository;

	public List<LoanInformation> searchBorrowerLoan(String borrowerFullName,Long loanAmount,String loanNumber){
		return loanInfoRepository.filterByConstraints(borrowerFullName, loanAmount, loanNumber);
	}
	

}
