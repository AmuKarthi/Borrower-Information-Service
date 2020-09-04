package com.scb.borrower.information.service;


import java.util.List;

import com.scb.borrower.information.model.LoanInformation;

public interface BorrowerInformationService {

	public List<LoanInformation> searchLoan(String borrower_name, Long loanAmount, String loanNumber)
			throws Exception;
}
