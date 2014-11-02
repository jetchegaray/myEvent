package com.je.enterprise.mievento.domain.entity.common.event;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import org.apache.commons.lang3.tuple.Pair;
import org.joda.time.DateTime;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.je.enterprise.mievento.api.dto.event.StatusType;

public class InvitationStatusTest {

	Pair<StatusType, Date> invitationStatus;
	
	@Test
	public void toJson_ok() throws IOException{
		
		this.invitationStatus  = Pair.of(StatusType.WAIT_TILL_DAY, DateTime.now().toDate());
		
		ObjectMapper mapper = new ObjectMapper();
		String value = mapper.writeValueAsString(invitationStatus);
		
		File file = new File("invitationStatus.json");
		file.createNewFile();
		PrintWriter writer = new PrintWriter(file);
		writer.println(value);
		writer.close();
		
	}
	

}
