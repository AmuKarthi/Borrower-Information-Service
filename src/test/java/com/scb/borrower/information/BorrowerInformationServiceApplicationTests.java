package com.scb.borrower.information;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.scb.borrower.information.model.BorrowerInformation;
import com.scb.borrower.information.model.LoanInformation;
import com.scb.borrower.information.payloadrequest.LoanSearchRequestPayload;
import com.scb.borrower.information.repository.BorrowerInformationRepository;
import com.scb.borrower.information.repository.LoanInformationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class BorrowerInformationServiceApplicationTests {

	@Autowired
	BorrowerInformationRepository borrowerInfoRepository;

	@Autowired
	LoanInformationRepository loanInfoRepository;

	@Test
	public void findBorrowerInfoTest() {
		List<BorrowerInformation> informations = borrowerInfoRepository.findAll();
		assertNotNull(informations);
		assertEquals("A", informations.get(0).getFullName());
	}

	// @Test
	public void saveBorrowerInfoTest() {
		BorrowerInformation info = new BorrowerInformation();
		info.setFullName("D");
		info.setContactNumber(9842798471L);
		info.setCreatedBy("admin");
		info.setCreatedTime(new Date());
		// borrowerInfoRepository.save(info);

		LoanInformation loaninfo = new LoanInformation();
		loaninfo.setBorrower(info);
		loaninfo.setLoanAmount(1230000L);
		loaninfo.setLoanInterest(7.0f);
		loaninfo.setLoanTenure(48);
		loaninfo.setLoanNumber("532001200003");
		// loanInfoRepository.save(loaninfo);
	}

	/*
	 * @Test public void countBorrowerInfoTest() { Long row =
	 * borrowerInfoRepository.count(); assertEquals(2, row); }
	 */

	@Test
	public void updateBorrowerInfoTest() {
		Optional<BorrowerInformation> borrowerInformation = borrowerInfoRepository.findById(10L);
		if (borrowerInformation.isPresent()) {
			borrowerInformation.get().setContactNumber(1234567890L);
			borrowerInformation.get().setFullName("E");
			borrowerInfoRepository.save(borrowerInformation.get());
		}
	}

	@Test
	public void updateLoanInfoTest() {
		Optional<LoanInformation> loanInformation = loanInfoRepository.findById(5L);
		if (loanInformation.isPresent()) {
			loanInformation.get().setLoanAmount(500000L);
			loanInformation.get().setLoanTenure(60);
			;
			loanInfoRepository.save(loanInformation.get());
		}
	}

	@Test
	public void filterByContrains() {
		List<LoanInformation> loanDetails = loanInfoRepository.filterByConstraints("B", 1000L, "532001200001");
		assertNotNull(loanDetails);
		assertEquals(6.7f, loanDetails.get(0).getLoanInterest());
	}

	@Test
	public void generateJsonFromPayload() throws JsonProcessingException {
		  ObjectMapper mapper = new ObjectMapper();
	      mapper.enable(SerializationFeature.INDENT_OUTPUT);
	      
	      LoanSearchRequestPayload loanSearchRequestPayload=new LoanSearchRequestPayload();
	      BorrowerInformation borrowerInfo=new BorrowerInformation();
	      loanSearchRequestPayload.setBorrower(borrowerInfo);
	      loanSearchRequestPayload.getBorrower().setFullName("A");
	      loanSearchRequestPayload.setLoanAmount(10L);
	      loanSearchRequestPayload.setLoanNumber("12321");
	      
	      
		/*
		 * DefaultPrettyPrinter p = new DefaultPrettyPrinter();
		 * DefaultPrettyPrinter.Indenter i = new DefaultIndenter("  ", "\n");
		 * p.indentArraysWith(i); p.indentObjectsWith(i);
		 */
	      
	   // Convert object to JSON string
	      String json = mapper.writerWithDefaultPrettyPrinter()
				.writeValueAsString(loanSearchRequestPayload);
		/*
		 * .replaceAll("\"", "") .replaceAll("\\r", "").replaceAll("\\n", "");
		 */
		 
	      System.out.println(json);
	   // Save JSON string to file
	      try {
			FileOutputStream fileOutputStream = new FileOutputStream("LoanSearch.json");
			mapper.writeValue(fileOutputStream, loanSearchRequestPayload);
		    fileOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
