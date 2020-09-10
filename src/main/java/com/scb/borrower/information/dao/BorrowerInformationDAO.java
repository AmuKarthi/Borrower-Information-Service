package com.scb.borrower.information.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scb.borrower.information.controller.LoanSearchController;
import com.scb.borrower.information.model.BorrowerInformation;
import com.scb.borrower.information.model.LoanInformation;
import com.scb.borrower.information.repository.BorrowerInformationRepository;
import com.scb.borrower.information.repository.LoanInformationRepository;

@Component
public class BorrowerInformationDAO {
	
	private static Logger log = LoggerFactory.getLogger(BorrowerInformationDAO.class);

	@Autowired
	LoanInformationRepository loanInfoRepository;
	
	@Autowired
	BorrowerInformationRepository borrowerInfoRepository;

	public List<LoanInformation> searchBorrowerLoan(String borrowerFullName,Long loanAmount,String loanNumber){
		log.info("called BorrowerInformationDAO");
		List<LoanInformation> loanDAO=loanInfoRepository.filterByConstraints(borrowerFullName, loanAmount, loanNumber);
		log.info("called BorrowerInformationDAO"+loanDAO.toString());
		return loanDAO;
	}
	

}
