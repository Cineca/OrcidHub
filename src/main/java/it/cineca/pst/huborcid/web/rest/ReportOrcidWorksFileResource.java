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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.codahale.metrics.annotation.Timed;

import it.cineca.pst.huborcid.domain.Application;
import it.cineca.pst.huborcid.domain.ResultOrcidWork;
import it.cineca.pst.huborcid.repository.ApplicationRepository;
import it.cineca.pst.huborcid.repository.ResultOrcidWorkRepository;
import it.cineca.pst.huborcid.security.SecurityUtils;
import it.cineca.pst.huborcid.service.FileService;
import it.cineca.pst.huborcid.web.rest.dto.UploadResponseDTO;
import it.cineca.pst.huborcid.web.rest.util.ResultCode;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api")
public class ReportOrcidWorksFileResource {
	
    @Inject
    private FileService fileService;
    
    @Inject
	private ApplicationRepository applicationRepository;
    
    @Inject
    private ResultOrcidWorkRepository resultOrcidWorkRepository;

	@RequestMapping(value="/reportOrcidWorks/fileUpload", method=RequestMethod.POST)
    public @ResponseBody UploadResponseDTO handleFileUpload(@RequestParam("file") MultipartFile multipartFile){
        if (!multipartFile.isEmpty()) {
            try {
            	String currentLogin =SecurityUtils.getCurrentLogin();
        		Application application = applicationRepository.findOneByApplicationID(currentLogin);

        	        
        		fileService.importExcelWorks(multipartFile, application);

                return new UploadResponseDTO(ResultCode.SUCCESS, "huborcidApp.reportOrcidWorks.uploadFileStatus.UPLOAD_SUCCESS");
            } catch (Exception e) {
                return new UploadResponseDTO(ResultCode.ERROR_FILE_UPLOAD, "huborcidApp.reportOrcidWorks.uploadFileStatus.UPLOAD_FAILURE");
            }
        } else {
        	 return new UploadResponseDTO(ResultCode.ERROR_FILE_UPLOAD, "huborcidApp.reportOrcidWorks.uploadFileStatus.UPLOAD_FAILURE_EMPTY");
        }
    }
	

    @RequestMapping(value = "/reportOrcidWorks/downloadExcelResult/{id}",
            method = RequestMethod.GET)
    @Timed
    public void getExcelResultOrcidWork(@PathVariable Long id, HttpServletResponse response) {
    	try {
    		ResultOrcidWork resultOrcidWork = resultOrcidWorkRepository.findOne(id);
		    byte[] fileBytesResult = resultOrcidWork.getFileResult();
		    response.setContentType("application/data");
		    ServletOutputStream outs = response.getOutputStream();
		    outs.write(fileBytesResult);
		    outs.flush();
		    outs.close();
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
    }
   
}
