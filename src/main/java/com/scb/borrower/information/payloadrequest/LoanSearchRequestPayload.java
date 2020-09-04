package com.scb.borrower.information.payloadrequest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.scb.borrower.information.model.LoanInformation;

@JsonInclude(value = Include.NON_NULL)
public class LoanSearchRequestPayload extends LoanInformation {

}
