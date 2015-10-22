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

exports.config = {
  framework: 'jasmine2',
  seleniumAddress: 'http://localhost:4444/wd/hub',
  specs: ['spec.js'],
  multiCapabilities: [{
//    browserName: 'firefox'
//  }, {
    browserName: 'chrome'
//  },{
//	browserName: 'phantomjs',
//	'phantomjs.binary.path': require('phantomjs').path,
//    'phantomjs.ghostdriver.cli.args': ['--loglevel=DEBUG']
  }],
  rootElement: '[ng-app]',
  
  onPrepare: function() {
	
	  
	    browser.driver.get('http://ubudget-unibo-dev2.sviluppo.u-gov.it');
	    //browser.pause();
	    //console.log(browser.driver.getPageSource());

	    
	    browser.driver.findElement(by.xpath("//input[@name='j_username']")).sendKeys('ADMIN_TESTUSER');
	    browser.driver.findElement(by.xpath("//input[@name='j_password']")).sendKeys('test');
	    browser.driver.findElement(by.xpath("//input[@value='Login']")).click();

	    // Login takes some time, so wait until it's done.
	    // For the test app's login, we know it's done when it redirects to
	    // index.html.
	    //browser.driver.sleep(10000);
	    browser.waitForAngular();
//	    
//	    browser.driver.wait(function() {
//	      return browser.driver.getCurrentUrl().then(function(url) {
//	        return /ubudgetWebApp/.test(url);
//	      });
//	    });
	  },
}