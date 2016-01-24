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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONArray;
import org.json.JSONObject;
import org.orcid.ns.orcid.Citation;
import org.orcid.ns.orcid.CitationType;
import org.orcid.ns.orcid.Contributor;
import org.orcid.ns.orcid.ContributorAttributes;
import org.orcid.ns.orcid.ContributorEmail;
import org.orcid.ns.orcid.Country;
import org.orcid.ns.orcid.CreditName;
import org.orcid.ns.orcid.Day;
import org.orcid.ns.orcid.JournalTitle;
import org.orcid.ns.orcid.LanguageCode;
import org.orcid.ns.orcid.Month;
import org.orcid.ns.orcid.OrcidId;
import org.orcid.ns.orcid.OrcidWork;
import org.orcid.ns.orcid.PublicationDate;
import org.orcid.ns.orcid.Subtitle;
import org.orcid.ns.orcid.TranslatedTitle;
import org.orcid.ns.orcid.Url;
import org.orcid.ns.orcid.WorkContributors;
import org.orcid.ns.orcid.WorkExternalIdentifier;
import org.orcid.ns.orcid.WorkExternalIdentifiers;
import org.orcid.ns.orcid.WorkTitle;
import org.orcid.ns.orcid.Year;
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
import it.cineca.pst.huborcid.domain.ResultOrcidWork;
import it.cineca.pst.huborcid.orcid.client.OrcidAccessToken;
import it.cineca.pst.huborcid.orcid.client.OrcidApiType;
import it.cineca.pst.huborcid.orcid.client.OrcidOAuthClient;
import it.cineca.pst.huborcid.repository.RelPersonApplicationRepository;
import it.cineca.pst.huborcid.repository.ResultOrcidWorkRepository;

@Service
@Scope("prototype")
@Transactional
public class FileService {

	private final Logger log = LoggerFactory.getLogger(FileService.class);
	
	@Inject
	private RelPersonApplicationRepository relPersonApplicationRepository;
	
	@Inject
	private ResultOrcidWorkRepository resultOrcidWorkRepository;
	    
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


	//@Async
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void importExcelWorks(MultipartFile file, Application application) throws IOException{
		log.debug(String.format("Method importExcelWorks START, file.name=[%s]", file.getOriginalFilename()));
		
		ResultOrcidWork resultOrcidWork = new ResultOrcidWork();
		try{
			resultOrcidWork.setApplication(application);
			resultOrcidWork.setFileNameUpload(file.getOriginalFilename());
			resultOrcidWork.setStatus("PROGRESS");
			resultOrcidWorkRepository.save(resultOrcidWork);
			
	        OrcidAccessToken orcidAccessToken = new OrcidAccessToken();
			OrcidWork orcidWork = new OrcidWork();
			
			InputStream fileInputStream = new BufferedInputStream( file.getInputStream());
			HSSFWorkbook workbook = new HSSFWorkbook( fileInputStream );
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			boolean withErrors = false;
			Iterator<Row> rowIterator = sheet.iterator();
			OrcidOAuthClient clientOrcid = new OrcidOAuthClient(orcidApiType);
	        while (rowIterator.hasNext()) {
	        	Row row = rowIterator.next();
	        	if(row.getRowNum() != 0){ 
	        		try {
		        		String valueCellLocalId = row.getCell(0).getStringCellValue();
		        		String valueCellOrcid = row.getCell(1).getStringCellValue();
	
		        		List<RelPersonApplication> listPersApp = relPersonApplicationRepository.findAllByApplicationIsAndLastIsTrueAndOrcidIsOrLocalIdIs(application, valueCellOrcid, valueCellLocalId);
		        		if(listPersApp.size()==1){
		        			RelPersonApplication persApp = listPersApp.get(0);
		        			orcidAccessToken.setAccess_token(persApp.getOauthAccessToken());
		        			orcidAccessToken.setOrcid(persApp.getPerson().getOrcid());
		        			
		        			orcidWork = createOrcidWork(sheet, row);     			
		        			clientOrcid.appendWork(orcidAccessToken, orcidWork);
		        			
		        			writeResultRow(row, "", true);
		        		}else{
		        			writeResultRow(row, "Utente non trovato", false);
		        			withErrors = true;
		        		}	        		
	        		} catch (Exception e) {
	        			writeResultRow(row, e.getMessage(), false);
	        			withErrors = true;
					}
	        	}
	        }
	        
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        workbook.write(baos);
	        workbook.close();
	        byte[] fileResult = baos.toByteArray();
			resultOrcidWork.setStatus("COMPLETED");
			resultOrcidWork.setWithErrors(withErrors);
			resultOrcidWork.setFileResult(fileResult);
			resultOrcidWorkRepository.save(resultOrcidWork);
		} catch (Exception e) {
			log.debug(String.format("Method importExcelWorks, exception=[%s]", e.getMessage()));
			resultOrcidWork.setStatus("ERROR");
			resultOrcidWorkRepository.save(resultOrcidWork);
		}
		
		log.debug("Method importExcelWorks END");
	}
	
	
	private void writeResultRow(Row row, String message, boolean isRowOK){
		String cellValue = isRowOK ? "OK" : "ERROR"; 
		Cell cell = row.createCell(row.getPhysicalNumberOfCells());
		cell.setCellValue(cellValue);
		if( !isRowOK ){
			cell = row.createCell(row.getPhysicalNumberOfCells());
			cell.setCellValue(message);
		}
	}
	
	private OrcidWork createOrcidWork(HSSFSheet sheet, Row row) throws Exception {
		OrcidWork orcidWork = new OrcidWork();
		int indexCol = 1;
		try{
			WorkTitle workTitle = new WorkTitle();
			String valueCellTitle = row.getCell(++indexCol).getStringCellValue();
			workTitle.setTitle(valueCellTitle);
			
			String valueCellSubtitle = row.getCell(++indexCol).getStringCellValue();
			Subtitle subtitle = new Subtitle();
			subtitle.setContent(valueCellSubtitle);
			workTitle.setSubtitle(subtitle);
			
			String valueCellTranslatedTitle = row.getCell(++indexCol).getStringCellValue();
			if(valueCellTranslatedTitle!=null && !valueCellTranslatedTitle.isEmpty()){
				JSONObject json = new JSONObject(valueCellTranslatedTitle);
				String translatedTitleStr = json.getString("title");
				String languageCodeStr = json.getString("lang");
				LanguageCode languageCode = LanguageCode.fromValue(languageCodeStr.toLowerCase());  
				TranslatedTitle translatedTitle = new TranslatedTitle(); 
				translatedTitle.setLanguageCode(languageCode);
				translatedTitle.setValue(translatedTitleStr);
				workTitle.setTranslatedTitle(translatedTitle);
			}
			orcidWork.setWorkTitle(workTitle);
			
			String valueCellJournalTitle = row.getCell(++indexCol).getStringCellValue();
			if( valueCellJournalTitle!=null && !valueCellJournalTitle.isEmpty() ){
	    		JournalTitle journalTitle = new JournalTitle();
	    		journalTitle.setContent(valueCellJournalTitle);
	    		orcidWork.setJournalTitle(journalTitle);
			}
			
			String valueCellShortDes = row.getCell(++indexCol).getStringCellValue();
			orcidWork.setShortDescription(valueCellShortDes);
			
		
			String valueCellCitType = row.getCell(++indexCol).getStringCellValue();
			if( valueCellCitType!=null && !valueCellCitType.isEmpty() ){
				Citation citation = new Citation();
				CitationType citationType = CitationType.fromValue(valueCellCitType.toLowerCase());
				citation.setWorkCitationType(citationType);
				
				String valueCellCit = row.getCell(++indexCol).getStringCellValue();
				if( valueCellCit!=null && !valueCellCit.isEmpty() ){
					citation.setCitation(valueCellCit);
	    			orcidWork.setWorkCitation(citation);
				}
			}
			
			String valueCellPubbDate = row.getCell(++indexCol).getStringCellValue();
			PublicationDate publicationDate = createPublicationDate(valueCellPubbDate);
			orcidWork.setPublicationDate(publicationDate);
			
			String valueCellWorkType = row.getCell(++indexCol).getStringCellValue();   
			if( valueCellWorkType!=null && !valueCellWorkType.isEmpty() ){
				orcidWork.setWorkType(valueCellWorkType);
			}
			
			String valueCellExId = row.getCell(++indexCol).getStringCellValue();
			WorkExternalIdentifiers workExternalIdentifiers = createWorkExternalIdentifiers(valueCellExId);
			orcidWork.setWorkExternalIdentifiers(workExternalIdentifiers);
			
			String valueCellUrl = row.getCell(++indexCol).getStringCellValue();
			if( valueCellUrl!=null && !valueCellUrl.isEmpty() ){
				Url url = new Url();
				url.setValue(valueCellUrl);
				orcidWork.setUrl(url);
			}
			
			String valueCellContributors = row.getCell(++indexCol).getStringCellValue();
			WorkContributors workContributors = createWorkContributors(valueCellContributors);
			orcidWork.setWorkContributors(workContributors);
			
			String valueCellLang = row.getCell(++indexCol).getStringCellValue();
			if( valueCellLang!=null && !valueCellLang.isEmpty() ){
				LanguageCode languageCode = LanguageCode.fromValue(valueCellLang.toLowerCase());  
				orcidWork.setLanguageCode(languageCode);
			}
			
			String valueCellCountry = row.getCell(++indexCol).getStringCellValue();
			if( valueCellCountry!=null && !valueCellCountry.isEmpty() ){
				Country country = new Country();
				country.setValue(valueCellCountry);
				orcidWork.setCountry(country);
			}
		}catch(Throwable t){
			Row rowHeader = sheet.getRow(0);
			Cell cellHeader = rowHeader.getCell(indexCol);
			throw new Exception(String.format("Errore colonna %s", cellHeader.getStringCellValue()));
		}
		return orcidWork;
	}
	
	
	private PublicationDate createPublicationDate(String valueCell) throws Exception {
		PublicationDate publicationDate = new PublicationDate();
		if( valueCell!=null && !valueCell.isEmpty() ){
			JSONObject json = new JSONObject(valueCell);
			String dayStr = json.getString("giorno");
			String monthStr = json.getString("mese");
			String yearStr = json.getString("anno");
			Day day = new Day();
			day.setValue(dayStr);
			Month month = new Month();
			month.setValue(monthStr);
			Year year = new Year();
			year.setValue(yearStr);
			publicationDate.setDay(day);
			publicationDate.setMonth(month);
			publicationDate.setYear(year);
		}
		return publicationDate;
	}
	
	private WorkExternalIdentifiers createWorkExternalIdentifiers(String valueCell) throws Exception {
		WorkExternalIdentifiers workExternalIdentifiers = new WorkExternalIdentifiers();
		if( valueCell!=null && !valueCell.isEmpty() ){
			JSONObject json = new JSONObject(valueCell);
		    JSONArray arr = json.getJSONArray("identifiers");
			for (int i = 0; i < arr.length(); i++){
			    String type = arr.getJSONObject(i).getString("type");
			    String id = arr.getJSONObject(i).getString("id");
    			WorkExternalIdentifier workExternalIdentifier = new WorkExternalIdentifier();
    			workExternalIdentifier.setWorkExternalIdentifierId(id);
    			workExternalIdentifier.setWorkExternalIdentifierType(type);
    			workExternalIdentifiers.getWorkExternalIdentifier().add(workExternalIdentifier);
			}
			
		}
		return workExternalIdentifiers;
	}
	
	private WorkContributors createWorkContributors(String valueCell) throws Exception {
		WorkContributors workContributors = new WorkContributors();
		if( valueCell!=null && !valueCell.isEmpty() ){
			JSONObject json = new JSONObject(valueCell);
		    JSONArray arr = json.getJSONArray("contributors");
			for (int i = 0; i < arr.length(); i++){
			    String orcidIdStr = arr.getJSONObject(i).getString("orcid");
			    String nameStr = arr.getJSONObject(i).getString("name");
			    String emailStr = arr.getJSONObject(i).getString("email");
			    String attSeqStr = arr.getJSONObject(i).getString("attributes-sequence");
			    String attSeqRole = arr.getJSONObject(i).getString("attributes-role");
			 
			    Contributor contributor = new Contributor();
			    OrcidId orcidId = new OrcidId();
			    //TODO
//			    orcidId.getContent().add(orcidIdStr);
			    contributor.setContributorOrcid(orcidId);
			    CreditName creditName = new CreditName();
			    creditName.setValue(nameStr);
			    contributor.setCreditName(creditName);
			    ContributorEmail contributorEmail = new ContributorEmail();
			    contributorEmail.setValue(emailStr);
			    contributor.setContributorEmail(contributorEmail);
			    ContributorAttributes contributorAttributes = new ContributorAttributes();
			    contributorAttributes.setContributorRole(attSeqRole);
			    contributorAttributes.setContributorSequence(attSeqStr);
			    contributor.setContributorAttributes(contributorAttributes);
			    
			    workContributors.getContributor().add(contributor);
			}
		}
		return workContributors;
	}
}
