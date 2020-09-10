package com.scb.borrower.information.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.scb.borrower.information.controller.LoanSearchController;
/*import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;*/
import com.scb.borrower.information.dao.BorrowerInformationDAO;
import com.scb.borrower.information.model.BorrowerInformation;
import com.scb.borrower.information.model.LoanInformation;
import com.scb.borrower.information.service.BorrowerInformationService;

@Service
public class BorrowerInformationServiceImpl implements BorrowerInformationService{
	
	private static Logger log = LoggerFactory.getLogger(BorrowerInformationServiceImpl.class);
	
	@Autowired
	BorrowerInformationDAO borrowerInformationDAO;

	@HystrixCommand(fallbackMethod = "getLoanSearchFallback",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000")
			})
	@Override
	public List<LoanInformation> searchLoan(String borrower_name,
			Long loanAmount, String loanNumber)
			throws Exception {
		log.info("Calling BorrowerInformationServiceImpl ");
		List<LoanInformation> loan=borrowerInformationDAO.searchBorrowerLoan(borrower_name, loanAmount,
				 loanNumber);
		log.info("BorrowerInformationServiceImpl return:::"+loan.toString());
		return loan;
	}
	
	public List<LoanInformation> getLoanSearchFallback(String borrower_name,
			Long loanAmount, String loanNumber){
		LoanInformation loanInformation=new LoanInformation();
		loanInformation.setLoanAmount(0L);
		loanInformation.setLoanNumber("0");
		loanInformation.setLoanInterest(0.0f);
		loanInformation.setLoanTenure(0);
		BorrowerInformation borrowerInformation=new BorrowerInformation();
		borrowerInformation.setFullName("Dummy");
		borrowerInformation.setContactNumber(0L);
		loanInformation.setBorrower(borrowerInformation);
		
		List<LoanInformation> listOfLoanInfo=new ArrayList<LoanInformation>();
		listOfLoanInfo.add(loanInformation);
		
		return listOfLoanInfo;
	}
	

	/*
	 * @Override public List<BorrowerInformation> searchLoan(String borrower_name,
	 * Long loanAmount, String loanNumber) throws Exception { return
	 * borrowerInformationDAO.searchBorrowerLoan(borrower_name, loanAmount,
	 * loanNumber); }
	 */

}
