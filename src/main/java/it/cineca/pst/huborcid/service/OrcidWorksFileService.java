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

import javax.xml.bind.JAXBElement;

import org.apache.poi.hssf.usermodel.HSSFSheet;
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
import org.orcid.ns.orcid.ObjectFactory;
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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.cineca.pst.huborcid.orcid.client.OrcidAccessToken;
import it.cineca.pst.huborcid.orcid.client.OrcidOAuthClient;

@Service
@Scope("prototype")
@Transactional
public class OrcidWorksFileService extends AbstractFileService {

	@Override
	public void createAppendEntity(OrcidOAuthClient orcidOAuthClient, OrcidAccessToken orcidAccessToken, HSSFSheet sheet, Row row) throws Exception {
		OrcidWork orcidWork = createOrcidWork(sheet, row);     			
		orcidOAuthClient.appendWork(orcidAccessToken, orcidWork);
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
			if(dayStr.length()==1) {
				dayStr = "0".concat(dayStr);
	        }
			if(monthStr.length()==1) {
				monthStr = "0".concat(monthStr);
	        }
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
			    String orcidStr = arr.getJSONObject(i).getString("orcid");
			    String nameStr = arr.getJSONObject(i).getString("name");
			    String emailStr = arr.getJSONObject(i).getString("email");
			    String attSeqStr = arr.getJSONObject(i).getString("attributes-sequence");
			    String attSeqRole = arr.getJSONObject(i).getString("attributes-role");
			 
			    Contributor contributor = new Contributor();
			    ObjectFactory factory = new ObjectFactory();
			    OrcidId orcidId = new OrcidId();
			    JAXBElement<String> elementOrcidId = factory.createOrcidIdPath(orcidStr);
			    orcidId.getContent().add(elementOrcidId);
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
