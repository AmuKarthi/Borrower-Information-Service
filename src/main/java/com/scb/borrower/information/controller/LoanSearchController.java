package com.scb.borrower.information.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scb.borrower.information.model.LoanInformation;
import com.scb.borrower.information.payloadrequest.LoanSearchRequestPayload;
import com.scb.borrower.information.service.BorrowerInformationService;
import com.scb.borrower.information.util.RecordNotFoundException;

@RestController
@RequestMapping(value = "/loan")
public class LoanSearchController {

	@Autowired
	private Environment env;
	
	@Autowired
	BorrowerInformationService borrowerInformationService;

	@GetMapping(value = "/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LoanInformation>> searchLoanByBorrowerInformation(
			@RequestBody LoanSearchRequestPayload payload) throws Exception {
		List<LoanInformation> listOfLoanInfor = borrowerInformationService
				.searchLoan(payload.getBorrower().getFullName(), payload.getLoanAmount(), payload.getLoanNumber());
		if(!listOfLoanInfor.isEmpty()) {
			return new ResponseEntity<List<LoanInformation>>(listOfLoanInfor, HttpStatus.OK);
		}else {
			throw new RecordNotFoundException("Record Not found...!");
		}
	}
	
	@GetMapping(value = "/welcome")
	public String eurekaTest() {
		return "Welcome Borrower information service registered in eureka server.. running on port:::"+env.getProperty("local.server.port");
	}
}
