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

import it.cineca.pst.huborcid.web.rest.dto.ErrrorMessageDTO;
import it.cineca.pst.huborcid.web.rest.exception.ApplicationIdMissingException;
import it.cineca.pst.huborcid.web.rest.exception.ApplicationNotFoundException;
import it.cineca.pst.huborcid.web.rest.exception.ApplicationlIDDifferentException;
import it.cineca.pst.huborcid.web.rest.exception.CallbackMissingException;
import it.cineca.pst.huborcid.web.rest.exception.LocalIDDifferentException;
import it.cineca.pst.huborcid.web.rest.exception.LocalIdMissingException;
import it.cineca.pst.huborcid.web.rest.exception.OrcidDeniedForApplicationException;
import it.cineca.pst.huborcid.web.rest.exception.OrcidForUserMissingException;
import it.cineca.pst.huborcid.web.rest.exception.OrgIdIsMissingException;
import it.cineca.pst.huborcid.web.rest.exception.OrgIdIsOnlyForPublicAppException;
import it.cineca.pst.huborcid.web.rest.exception.RelPersonApplicationNotFoundException;
import it.cineca.pst.huborcid.web.rest.exception.TokenExpiredException;
import it.cineca.pst.huborcid.web.rest.exception.TokenNotFoundException;
import it.cineca.pst.huborcid.web.rest.util.ResultCode;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler
    ResponseEntity<ErrrorMessageDTO> handleException(ApplicationNotFoundException ex) {
		ErrrorMessageDTO errorMessage = new ErrrorMessageDTO(ResultCode.ERROR_APP_ID_NOT_FOUND,ex.getMessage());
        ResponseEntity<ErrrorMessageDTO> responseEntity = new ResponseEntity<ErrrorMessageDTO>(errorMessage, HttpStatus.NOT_FOUND);
        return responseEntity;
    }
	
	@ExceptionHandler
    ResponseEntity<ErrrorMessageDTO> handleException(LocalIDDifferentException ex) {
		ErrrorMessageDTO errorMessage = new ErrrorMessageDTO(ResultCode.ERROR_LOCAL_ID_DIFFERENT,ex.getMessage());
        ResponseEntity<ErrrorMessageDTO> responseEntity = new ResponseEntity<ErrrorMessageDTO>(errorMessage, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
	
	@ExceptionHandler
    ResponseEntity<ErrrorMessageDTO> handleException(LocalIdMissingException ex) {
		ErrrorMessageDTO errorMessage = new ErrrorMessageDTO(ResultCode.ERROR_LOCAL_ID_MISSING,ex.getMessage());
        ResponseEntity<ErrrorMessageDTO> responseEntity = new ResponseEntity<ErrrorMessageDTO>(errorMessage, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
	
	@ExceptionHandler
    ResponseEntity<ErrrorMessageDTO> handleException(ApplicationIdMissingException ex) {
		ErrrorMessageDTO errorMessage = new ErrrorMessageDTO(ResultCode.ERROR_APP_ID_MISSING,ex.getMessage());
        ResponseEntity<ErrrorMessageDTO> responseEntity = new ResponseEntity<ErrrorMessageDTO>(errorMessage, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
	
	@ExceptionHandler
    ResponseEntity<ErrrorMessageDTO> handleException(TokenNotFoundException ex) {
		ErrrorMessageDTO errorMessage = new ErrrorMessageDTO(ResultCode.ERROR_TOKEN_NOT_FOUND,ex.getMessage());
        ResponseEntity<ErrrorMessageDTO> responseEntity = new ResponseEntity<ErrrorMessageDTO>(errorMessage, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
	
	@ExceptionHandler
    ResponseEntity<ErrrorMessageDTO> handleException(TokenExpiredException ex) {
		ErrrorMessageDTO errorMessage = new ErrrorMessageDTO(ResultCode.ERROR_TOKEN_EXPIRED,ex.getMessage());
        ResponseEntity<ErrrorMessageDTO> responseEntity = new ResponseEntity<ErrrorMessageDTO>(errorMessage, HttpStatus.LOCKED);
        return responseEntity;
    }
	
	@ExceptionHandler
    ResponseEntity<ErrrorMessageDTO> handleException(OrcidForUserMissingException ex) {
		ErrrorMessageDTO errorMessage = new ErrrorMessageDTO(ResultCode.ERROR_ORCID_FOR_USER_MISSING,ex.getMessage());
        ResponseEntity<ErrrorMessageDTO> responseEntity = new ResponseEntity<ErrrorMessageDTO>(errorMessage, HttpStatus.NOT_FOUND);
        return responseEntity;
    }
	
	@ExceptionHandler
    ResponseEntity<ErrrorMessageDTO> handleException(OrcidDeniedForApplicationException ex) {
		ErrrorMessageDTO errorMessage = new ErrrorMessageDTO(ResultCode.ERROR_USER_DENIED,ex.getMessage());
        ResponseEntity<ErrrorMessageDTO> responseEntity = new ResponseEntity<ErrrorMessageDTO>(errorMessage, HttpStatus.NOT_FOUND);
        return responseEntity;
    }
	
	@ExceptionHandler
    ResponseEntity<ErrrorMessageDTO> handleException(RelPersonApplicationNotFoundException ex) {
		ErrrorMessageDTO errorMessage = new ErrrorMessageDTO(ResultCode.ERROR_REL_PERSON_APPLICATION_NOT_FOUND,ex.getMessage());
        ResponseEntity<ErrrorMessageDTO> responseEntity = new ResponseEntity<ErrrorMessageDTO>(errorMessage, HttpStatus.NOT_FOUND);
        return responseEntity;
    }
	
	@ExceptionHandler
    ResponseEntity<ErrrorMessageDTO> handleException(ApplicationlIDDifferentException ex) {
		ErrrorMessageDTO errorMessage = new ErrrorMessageDTO(ResultCode.ERROR_APP_ID_DIFFERENT_LOGIN,ex.getMessage());
        ResponseEntity<ErrrorMessageDTO> responseEntity = new ResponseEntity<ErrrorMessageDTO>(errorMessage, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
	
	@ExceptionHandler
    ResponseEntity<ErrrorMessageDTO> handleException(OrgIdIsOnlyForPublicAppException ex) {
		ErrrorMessageDTO errorMessage = new ErrrorMessageDTO(ResultCode.ERROR_ORG_ID_IS_ONLY_FOR_PUBLIC_APP,ex.getMessage());
        ResponseEntity<ErrrorMessageDTO> responseEntity = new ResponseEntity<ErrrorMessageDTO>(errorMessage, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
	
	@ExceptionHandler
    ResponseEntity<ErrrorMessageDTO> handleException(OrgIdIsMissingException ex) {
		ErrrorMessageDTO errorMessage = new ErrrorMessageDTO(ResultCode.ERROR_ORG_ID_MISSING,ex.getMessage());
        ResponseEntity<ErrrorMessageDTO> responseEntity = new ResponseEntity<ErrrorMessageDTO>(errorMessage, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
	
	@ExceptionHandler
    ResponseEntity<ErrrorMessageDTO> handleException(Exception ex) {
		ErrrorMessageDTO errorMessage = new ErrrorMessageDTO(ResultCode.ERROR_GENERIC_FAIL,ex.getMessage());
        ResponseEntity<ErrrorMessageDTO> responseEntity = new ResponseEntity<ErrrorMessageDTO>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }
	
	@ExceptionHandler
    ResponseEntity<ErrrorMessageDTO> handleException(CallbackMissingException ex) {
		ErrrorMessageDTO errorMessage = new ErrrorMessageDTO(ResultCode.ERROR_CALLBACK_MISSING,ex.getMessage());
        ResponseEntity<ErrrorMessageDTO> responseEntity = new ResponseEntity<ErrrorMessageDTO>(errorMessage, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
}
