/**
 * This file is part of huborcid.
 *
 * huborcid is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * huborcid is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with huborcid.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.cineca.pst.huborcid.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.cineca.pst.huborcid.domain.Application;
import it.cineca.pst.huborcid.domain.PersonBio;
import it.cineca.pst.huborcid.domain.RelPersonApplication;
import it.cineca.pst.huborcid.domain.util.CustomDateTimeDeserializer;
import it.cineca.pst.huborcid.domain.util.CustomDateTimeSerializer;
import it.cineca.pst.huborcid.repository.ApplicationRepository;
import it.cineca.pst.huborcid.repository.PersonBioRepository;
import it.cineca.pst.huborcid.repository.RelPersonApplicationRepository;
import it.cineca.pst.huborcid.security.SecurityUtils;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/api")
public class ReportOrcidBioFileResource {

    @Inject 
    private PersonBioRepository personBioRepository;
    
    @Inject 
    private ApplicationRepository applicationRepository;

    @RequestMapping(value = "/reportOrcidBio/downloadExcel",
            method = RequestMethod.GET)
    @Timed
    public void getExcelOrcidBio(HttpServletResponse response) throws URISyntaxException {
    	String currentLogin = SecurityUtils.getCurrentLogin();
    	Application application = applicationRepository.findOneByApplicationID(currentLogin);
    	
    	Sort sort = new Sort(Sort.Direction.ASC, Arrays.asList("person.firstName","person.lastName"));
    	List<PersonBio> listPersonBio = personBioRepository.findAllPersonByApplicationIsAndLastIsTrue(application, sort);
    	
    	HSSFWorkbook workbook = new HSSFWorkbook();
    	HSSFSheet sheet = workbook.createSheet("Report");
   	
    	Object[] headerExcel = new Object[] {"FIRST NAME", "LAST NAME", "LOCAL ID", "ORCID", "BIOGRAPHY","RESEARCHER URLS","EXTERNAL IDENTIFIERS"};
    	Row rowHeader = sheet.createRow(0);
    	int cellnumHeader = 0;
    	for (Object obj : headerExcel) {
	        Cell cell = rowHeader.createCell(cellnumHeader++);
	        if(obj instanceof Date) 
	            cell.setCellValue((Date)obj);
	        else if(obj instanceof Boolean)
	            cell.setCellValue((Boolean)obj);
	        else if(obj instanceof String)
	            cell.setCellValue((String)obj);
	        else if(obj instanceof Double)
	            cell.setCellValue((Double)obj);
	    }
    	
    	int rownum = 1;
    	for(int i=0;i<listPersonBio.size();i++){
    		PersonBio personBio = listPersonBio.get(i);
    		Row rowData = sheet.createRow(rownum++);
    		int cellnumData = 0;

    		Cell cell = rowData.createCell(cellnumData++);
    		cell.setCellValue(personBio.getPerson().getFirstName());
    		
    		cell = rowData.createCell(cellnumData++);
    		cell.setCellValue(personBio.getPerson().getLastName());
    		
    		cell = rowData.createCell(cellnumData++);
    		cell.setCellValue(personBio.getPerson().getLocalID());

    		cell = rowData.createCell(cellnumData++);
    		cell.setCellValue(personBio.getPerson().getOrcid());
    		
    		cell = rowData.createCell(cellnumData++);
    		cell.setCellValue(personBio.getBiography());
    		
    		cell = rowData.createCell(cellnumData++);
    		cell.setCellValue(personBio.getResearcher_urls());
    		
    		cell = rowData.createCell(cellnumData++);
    		cell.setCellValue(personBio.getExternal_identifiers());
    	}
    	
    	for(int i=0;i< headerExcel.length;i++){
    		sheet.autoSizeColumn(i);
    	}
    	
    	try {
    	    workbook.write(response.getOutputStream());
    	    workbook.close();
    	} catch (FileNotFoundException e) {
    	    e.printStackTrace();
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
    }
    
   
}
