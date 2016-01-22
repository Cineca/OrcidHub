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

import it.cineca.pst.huborcid.config.Constants;
import it.cineca.pst.huborcid.service.FileService;
import it.cineca.pst.huborcid.service.OrcidService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class OrcidController {
	
    @Inject
    private Environment env;
    
    public OrcidController() {
		super();
	}
    
    
	@RequestMapping(value = {"/orcid"}, method = RequestMethod.GET)
	public String orcid(
			HttpServletRequest request) {
		
        if (env.acceptsProfiles(Constants.SPRING_PROFILE_PRODUCTION)) {
        	return "forward:/dist/index2.html";
        }
	
		return "forward:/index2.html";
	}
	



}
