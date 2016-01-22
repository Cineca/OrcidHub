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

/**
 * REST controller for managing RelPersonApplication.
 */
@Controller
@RequestMapping("/api")
public class ReportatFileResource {

    private final Logger log = LoggerFactory.getLogger(ReportatFileResource.class);

    @Inject
    private RelPersonApplicationRepository relPersonApplicationRepository;
    
    @Inject
    private ApplicationRepository applicationRepository;

    @Inject 
    private PersonBioRepository personBioRepository;
    
    
    /**
     * GET  /reportat -> get all the relPersonApplications.
     */
    @RequestMapping(value = "/reportat/downloadExcel",
            method = RequestMethod.GET)
    @Timed
    public void getExcel(HttpServletResponse response)
        throws URISyntaxException {
    	String currentLogin = SecurityUtils.getCurrentLogin();
    	Application application = applicationRepository.findOneByApplicationID(currentLogin);
    	Sort sort = new Sort(Sort.Direction.ASC, "person.localID");
    	List<RelPersonApplication> listAccessToken = relPersonApplicationRepository.findAllByLastIsTrueAndApplicationIs(application,sort);
    	
    	
    	HSSFWorkbook workbook = new HSSFWorkbook();
    	HSSFSheet sheet = workbook.createSheet("Report Access Token");
    	 
   	
    	Object[] headerExcel = new Object[] {"LOCAL ID", "ORCID", "ORCID ASSOCIATION DATE","ORCID ACCESS TOKEN","ORCID ACCESS TOKEN RELEASED DATE"};
    	Row rowHeader = sheet.createRow(0);
    	int cellnumHeader = 0;
	    //header
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
    	
    	
    	//data
    	
    	CellStyle cellStyle = workbook.createCellStyle();
    	CreationHelper createHelper = workbook.getCreationHelper();
    	cellStyle.setDataFormat(
    	    createHelper.createDataFormat().getFormat("m/d/yy"));
    	
    	int rownum = 1;
    	for(int i=0;i<listAccessToken.size();i++){
    		RelPersonApplication relPerson = listAccessToken.get(i);
    		Row rowData = sheet.createRow(rownum++);
    		int cellnumData = 0;
    		//localid
    		Cell cellLocalId = rowData.createCell(cellnumData++);
    		cellLocalId.setCellValue(relPerson.getPerson().getLocalID());
    		
    		//orcid
    		Cell cellOrcid = rowData.createCell(cellnumData++);
    		cellOrcid.setCellValue(relPerson.getPerson().getOrcid());
    		
    		//orcidCreated
    		Cell cellOrcidCreated = rowData.createCell(cellnumData++);
    		if(relPerson.getPerson().getOrcidReleaseDate()!=null){
    			cellOrcidCreated.setCellValue(relPerson.getPerson().getOrcidReleaseDate().toDate());
    			cellOrcidCreated.setCellStyle(cellStyle);
    		}
    		
    		//orcid access token
    		Cell callAccessToken = rowData.createCell(cellnumData++);
			if((relPerson.getDenied()==null)||(relPerson.getDenied()==false)){
				callAccessToken.setCellValue(relPerson.getOauthAccessToken());
			}else{
				callAccessToken.setCellValue((String)null);
			}
    		
    		//access token Created
    		Cell cellAccessTokenCreated = rowData.createCell(cellnumData++);
    		if(relPerson.getDateReleased()!=null){
    			if((relPerson.getDenied()==null)||(relPerson.getDenied()==false)){
	    			cellAccessTokenCreated.setCellValue(relPerson.getDateReleased().toDate());
	    			cellAccessTokenCreated.setCellStyle(cellStyle);
    			}else{
	    			//cellAccessTokenCreated.setCellValue((Date)null);
	    			cellAccessTokenCreated.setCellStyle(cellStyle);
    			}
    		}
    		
//    		//FIXME quando verrà gestita la revoca
//    		//denied
//    		Cell cellDenied = rowData.createCell(cellnumData++);
//    		if(relPerson.getDenied()!=null)
//    			cellDenied.setCellValue(new Boolean(null));
    	}
    	
    	//autosize
    	for(int i=0;i< headerExcel.length;i++){
    		sheet.autoSizeColumn(i);
    	}
    	
    	try {
    	    workbook.write(response.getOutputStream());
    	     
    	} catch (FileNotFoundException e) {
    	    e.printStackTrace();
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
    	
    }
   
}
