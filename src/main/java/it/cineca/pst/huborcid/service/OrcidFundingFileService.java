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
import org.orcid.ns.orcid.Amount;
import org.orcid.ns.orcid.ContributorEmail;
import org.orcid.ns.orcid.CreditName;
import org.orcid.ns.orcid.CurrencyCode;
import org.orcid.ns.orcid.Day;
import org.orcid.ns.orcid.DisambiguatedOrganization;
import org.orcid.ns.orcid.Funding;
import org.orcid.ns.orcid.FundingContributor;
import org.orcid.ns.orcid.FundingContributorAttributes;
import org.orcid.ns.orcid.FundingContributors;
import org.orcid.ns.orcid.FundingExternalIdentifier;
import org.orcid.ns.orcid.FundingExternalIdentifiers;
import org.orcid.ns.orcid.FundingTitle;
import org.orcid.ns.orcid.FuzzyDate;
import org.orcid.ns.orcid.Iso3166Country;
import org.orcid.ns.orcid.Month;
import org.orcid.ns.orcid.ObjectFactory;
import org.orcid.ns.orcid.OrcidId;
import org.orcid.ns.orcid.Organization;
import org.orcid.ns.orcid.OrganizationAddress;
import org.orcid.ns.orcid.Url;
import org.orcid.ns.orcid.Year;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.cineca.pst.huborcid.orcid.client.OrcidAccessToken;
import it.cineca.pst.huborcid.orcid.client.OrcidOAuthClient;

@Service
@Scope("prototype")
@Transactional
public class OrcidFundingFileService extends AbstractFileService {

	@Override
	public void createAppendEntity(OrcidOAuthClient orcidOAuthClient, OrcidAccessToken orcidAccessToken, HSSFSheet sheet, Row row) throws Exception {
		Funding funding = createFunding(sheet, row);     			
		orcidOAuthClient.appendFunding(orcidAccessToken, funding);
	}
	
	private Funding createFunding(HSSFSheet sheet, Row row) throws Exception {
		Funding funding = new Funding();
		int indexCol = 1;
		try{
			String valueCell = row.getCell(++indexCol).getStringCellValue();
			funding.setFundingType(valueCell);
			
			FundingTitle fundingTitle = new FundingTitle();
			valueCell = row.getCell(++indexCol).getStringCellValue();
			fundingTitle.setTitle(valueCell);
			funding.setFundingTitle(fundingTitle);
			
			valueCell = row.getCell(++indexCol).getStringCellValue();
			funding.setShortDescription(valueCell);
			
			valueCell = row.getCell(++indexCol).getStringCellValue();
			JSONObject json = new JSONObject(valueCell);
			String valueCellCurrencyCode = json.getString("currency-code");
			String valueCellAmount = json.getString("amount");
			CurrencyCode currencyCode = CurrencyCode.fromValue(valueCellCurrencyCode.toUpperCase());  
			Amount amount = new Amount();
			amount.setCurrencyCode(currencyCode);
			amount.setValue(valueCellAmount);
			funding.setAmount(amount);
			
			valueCell = row.getCell(++indexCol).getStringCellValue();
			Url url = new Url();
			url.setValue(valueCell);
			funding.setUrl(url);
			
			valueCell = row.getCell(++indexCol).getStringCellValue();
			FuzzyDate fuzzyDate = createDateFunding(valueCell);
			funding.setStartDate(fuzzyDate);
			
			valueCell = row.getCell(++indexCol).getStringCellValue();
			fuzzyDate = createDateFunding(valueCell);
			funding.setEndDate(fuzzyDate);
			
			valueCell = row.getCell(++indexCol).getStringCellValue();
			FundingExternalIdentifiers fundingExternalIdentifiers = createFundingExternalIdentifiers(valueCell);
			funding.setFundingExternalIdentifiers(fundingExternalIdentifiers);
			
			
			valueCell = row.getCell(++indexCol).getStringCellValue();
			FundingContributors fundingContributors = createFundingContributors(valueCell);
			funding.setFundingContributors(fundingContributors);
			
			valueCell = row.getCell(++indexCol).getStringCellValue();
			Organization organization = createOrganization(valueCell);
			funding.setOrganization(organization);
			
		}catch(Throwable t){
			Row rowHeader = sheet.getRow(0);
			Cell cellHeader = rowHeader.getCell(indexCol);
			throw new Exception(String.format("Errore colonna %s", cellHeader.getStringCellValue()));
		}
		return funding;	
		
	}
	
	private FuzzyDate createDateFunding(String valueCell) throws Exception {
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
		FuzzyDate fuzzyDate = new FuzzyDate();
		fuzzyDate.setDay(day);
		fuzzyDate.setMonth(month);
		fuzzyDate.setYear(year);
		return fuzzyDate;
	}
	
	private FundingExternalIdentifiers createFundingExternalIdentifiers(String valueCell) throws Exception {
		FundingExternalIdentifiers fundingExternalIdentifiers = new FundingExternalIdentifiers();
		if( valueCell!=null && !valueCell.isEmpty() ){
			JSONObject json = new JSONObject(valueCell);
		    JSONArray arr = json.getJSONArray("identifiers");
			for (int i = 0; i < arr.length(); i++){
			    String type = arr.getJSONObject(i).getString("type");
			    String value = arr.getJSONObject(i).getString("value");
			    String url = arr.getJSONObject(i).getString("url");
    			FundingExternalIdentifier fundingExternalIdentifier = new FundingExternalIdentifier();
    			fundingExternalIdentifier.setFundingExternalIdentifierType(type);
    			fundingExternalIdentifier.setFundingExternalIdentifierValue(value);
    			fundingExternalIdentifier.setFundingExternalIdentifierUrl(url);
    			fundingExternalIdentifiers.getFundingExternalIdentifier().add(fundingExternalIdentifier);
			}
		}
		return fundingExternalIdentifiers;
	}
	
	private FundingContributors createFundingContributors(String valueCell) throws Exception {
		FundingContributors fundingContributors = new FundingContributors();
		if( valueCell!=null && !valueCell.isEmpty() ){
			JSONObject json = new JSONObject(valueCell);
		    JSONArray arr = json.getJSONArray("contributors");
			for (int i = 0; i < arr.length(); i++){
				String orcidStr = arr.getJSONObject(i).getString("orcid");
			    String nameStr = arr.getJSONObject(i).getString("name");
			    String emailStr = arr.getJSONObject(i).getString("email");
			    String attSeqRole = arr.getJSONObject(i).getString("attributes-role");
			 
			    FundingContributor fundingContributor = new FundingContributor();
			    
			    ObjectFactory factory = new ObjectFactory();
			    OrcidId orcidId = new OrcidId();
			    JAXBElement<String> elementOrcidId = factory.createOrcidIdPath(orcidStr);
			    orcidId.getContent().add(elementOrcidId);
			    fundingContributor.setContributorOrcid(orcidId);
			    CreditName creditName = new CreditName();
			    creditName.setValue(nameStr);
			    fundingContributor.setCreditName(creditName);
			    ContributorEmail contributorEmail = new ContributorEmail();
			    contributorEmail.setValue(emailStr);
			    fundingContributor.setContributorEmail(contributorEmail);
			    FundingContributorAttributes fundingContributorAttributes = new FundingContributorAttributes();
			    fundingContributorAttributes.setFundingContributorRole(attSeqRole);
			    fundingContributor.setFundingContributorAttributes(fundingContributorAttributes);
			    fundingContributors.getFundingContributor().add(fundingContributor);
			}
		}
		return fundingContributors;
	}
	
	private Organization createOrganization(String valueCell) throws Exception {
		JSONObject json = new JSONObject(valueCell);
		String name = json.getString("name");
		String addressCity = json.getString("address-city");
		String addressRegion = json.getString("address-region");
		String addressCountry = json.getString("address-country");
		String disOrgId = json.getString("disambiguated-organization-identifier");
		String disSource = json.getString("disambiguation-source");

		Iso3166Country country = Iso3166Country.valueOf(addressCountry.toUpperCase());
		DisambiguatedOrganization disambiguatedOrganization = new DisambiguatedOrganization();
		disambiguatedOrganization.setDisambiguatedOrganizationIdentifier(disOrgId);
		disambiguatedOrganization.setDisambiguationSource(disSource);
		OrganizationAddress organizationAddress = new OrganizationAddress();
		organizationAddress.setCity(addressCity);
		organizationAddress.setRegion(addressRegion);
		organizationAddress.setCountry(country);
		Organization organization = new Organization();
		organization.setName(name);
		organization.setAddress(organizationAddress);
		organization.setDisambiguatedOrganization(disambiguatedOrganization);
		return organization;
	}


	
}
