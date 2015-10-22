/*
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
// spec.js
describe('OrcidHub test protractor', function() {
	var scenari = element.all(by.repeater('scenario in scenari track by $index'));
  //var createOrcid = element(by.id('connect-orcid-button'));
  //var listApp = element.all(by.repeater('value2 in appsdata.listApp'));
	//var userField = element(by.name('j_username'));
	//var pwdField = element(by.name('j_password'));
	//var submit = $('input[value=Login]');
	//var username = 'ADMIN_TESTUSER';
	//var pwd = 'test';

  beforeEach(function() {
    browser.get('http://ubudget-unibo-dev2.sviluppo.u-gov.it/ubudgetWebApp/#/scenari');
  });

  it('should have 3 app', function() {
	  var scenario = scenari.first().element(by.className('btn-null'));
	  scenario.click();
	  
	  browser.driver.sleep(5000);
	  var taga = element(by.xpath("//a[contains(text(),\"Seleziona un'Unità Analitica\")]"));
	  taga.click();
	  browser.driver.sleep(5000);
	  // userField.sendKeys('ADMIN_TESTUSER');
	 // pwdField.sendKeys('test');
	 // submit.click();
	  
	  
	 //expect(listApp.count()).toEqual(3); 
    //createOrcid.click();
  });
});