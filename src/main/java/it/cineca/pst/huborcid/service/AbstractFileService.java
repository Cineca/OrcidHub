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
package it.cineca.pst.huborcid.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import it.cineca.pst.huborcid.domain.Application;
import it.cineca.pst.huborcid.domain.RelPersonApplication;
import it.cineca.pst.huborcid.domain.ResultUploadOrcidEntity;
import it.cineca.pst.huborcid.orcid.client.OrcidAccessToken;
import it.cineca.pst.huborcid.orcid.client.OrcidApiType;
import it.cineca.pst.huborcid.orcid.client.OrcidOAuthClient;
import it.cineca.pst.huborcid.repository.RelPersonApplicationRepository;
import it.cineca.pst.huborcid.repository.ResultUploadOrcidEntityRepository;

@Service
@Scope("prototype")
@Transactional
public abstract class AbstractFileService {

	private final Logger log = LoggerFactory.getLogger(AbstractFileService.class);
	
	@Inject
	private RelPersonApplicationRepository relPersonApplicationRepository;
	
	@Inject
	private ResultUploadOrcidEntityRepository resultUploadOrcidEntityRepository;
	    
	@Autowired
    private Environment env;
	
	private OrcidApiType orcidApiType;
	
	@PostConstruct
    public void settingEnv() {
		if(env.acceptsProfiles("prod")){
			orcidApiType = OrcidApiType.LIVE;
		}else{
			orcidApiType = OrcidApiType.SANDBOX;
		}
    }


//	@Async
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void uploadFileOrcid(MultipartFile file, Application application, String typeEntity) throws IOException{
		log.debug(String.format("Method uploadFileOrcid START, file.name=[%s]", file.getOriginalFilename()));
		
		Integer maxColumn = 0;
		ResultUploadOrcidEntity resultUploadOrcid = new ResultUploadOrcidEntity();
		try{
			resultUploadOrcid.setApplication(application);
			resultUploadOrcid.setFileNameUpload(file.getOriginalFilename());
			resultUploadOrcid.setStatus("PROGRESS");
			resultUploadOrcid.setEntityType(typeEntity);
			resultUploadOrcidEntityRepository.save(resultUploadOrcid);
			
	        OrcidAccessToken orcidAccessToken = new OrcidAccessToken();
			
			InputStream fileInputStream = new BufferedInputStream( file.getInputStream());
			HSSFWorkbook workbook = new HSSFWorkbook( fileInputStream );
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			boolean withErrors = false;
			Iterator<Row> rowIterator = sheet.iterator();
			OrcidOAuthClient clientOrcid = new OrcidOAuthClient(orcidApiType);
	        while (rowIterator.hasNext()) {
	        	Row row = rowIterator.next();
	        	if( row.getRowNum() == 0 ){
	        		maxColumn = row.getPhysicalNumberOfCells();
	        	} else {
	        		try {
	        			String valueCellLocalId = "";
	        			String valueCellOrcid = "";
	        			Cell cell = row.getCell(0);
	        			if( cell!=null ){
	        				valueCellLocalId = cell.getStringCellValue();
	        			}
	        			cell = row.getCell(1);
	        			if( cell!=null ){
	        				valueCellOrcid = cell.getStringCellValue();
	        			}
	
		        		List<RelPersonApplication> listPersApp = relPersonApplicationRepository.findAllByApplicationIsAndLastIsTrueAndOrcidIsOrLocalIdIs(application, valueCellOrcid, valueCellLocalId);
		        		if(listPersApp.size()==1){
		        			RelPersonApplication persApp = listPersApp.get(0);
		        			orcidAccessToken.setAccess_token(persApp.getOauthAccessToken());
		        			orcidAccessToken.setOrcid(persApp.getPerson().getOrcid());
		        			
		        			createAppendEntity(clientOrcid, orcidAccessToken, sheet, row);
		        			
		        			writeResultRow(row, maxColumn, "", true);
		        		}else if(listPersApp.size()==0){
		        			writeResultRow(row, maxColumn, "Utente non trovato", false);
		        			withErrors = true;
		        		}else if(listPersApp.size()>1){
		        			writeResultRow(row, maxColumn, "Errore di sistema: anagrafiche duplicate", false);
		        			withErrors = true;
		        		}	        		
	        		} catch (Exception e) {
	        			writeResultRow(row, maxColumn, e.getMessage(), false);
	        			withErrors = true;
					}
	        	}
	        }
	        
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        workbook.write(baos);
	        workbook.close();
	        byte[] fileResult = baos.toByteArray();
	        resultUploadOrcid.setStatus("COMPLETED");
	        resultUploadOrcid.setWithErrors(withErrors);
	        resultUploadOrcid.setFileResult(fileResult);
			resultUploadOrcidEntityRepository.save(resultUploadOrcid);
		} catch (Exception e) {
			log.debug(String.format("Method uploadFileOrcid, exception=[%s]", e.getMessage()));
			resultUploadOrcid.setStatus("ERROR");
			resultUploadOrcidEntityRepository.save(resultUploadOrcid);
		}
		
		log.debug("Method uploadFileOrcid END");
	}
	
	
	public abstract void createAppendEntity(OrcidOAuthClient orcidOAuthClient, OrcidAccessToken orcidAccessToken, HSSFSheet sheet, Row row) throws Exception;


	private void writeResultRow(Row row, Integer column, String message, boolean isRowOK){
		String cellValue = isRowOK ? "OK" : "ERROR"; 
		Cell cell = row.createCell(column);
		cell.setCellValue(cellValue);
		if( !isRowOK ){
			cell = row.createCell(column+1);
			cell.setCellValue(message);
		}
	}
	
}
