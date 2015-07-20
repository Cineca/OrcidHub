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
package it.cineca.pst.huborcid.security;

import it.cineca.pst.huborcid.domain.Authority;
import it.cineca.pst.huborcid.domain.User;
import it.cineca.pst.huborcid.domain.UserOrcid;
import it.cineca.pst.huborcid.repository.UserOrcidRepository;
import it.cineca.pst.huborcid.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    @Inject
    private UserRepository userRepository;
    
    @Inject
    private UserOrcidRepository userOrcidRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
    	UserDetails userReturn = null;
        try{
        	userReturn = loadUserByUsernameJHipster(login);
        }catch(UsernameNotFoundException e){
        	userReturn = loadUserByUsernameOrcid(login);
        }
        return userReturn;
    }
    

    public UserDetails loadUserByUsernameJHipster(final String login) {
        log.debug("Authenticating JHipster {}", login);
        String lowercaseLogin = login.toLowerCase();
        User userFromDatabase = userRepository.findOneByLogin(lowercaseLogin);
        if (userFromDatabase == null) {
            throw new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database JHipster");
        } else if (!userFromDatabase.getActivated()) {
            throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated JHipster");
        }

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : userFromDatabase.getAuthorities()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
            grantedAuthorities.add(grantedAuthority);
        }
        return new org.springframework.security.core.userdetails.User(lowercaseLogin,
            userFromDatabase.getPassword(), grantedAuthorities);
    }	
    
    public UserDetails loadUserByUsernameOrcid(final String login) {
        log.debug("Authenticating Orcid {}", login);
        UserOrcid userFromDatabase = userOrcidRepository.findOneByUsername(login);
        if (userFromDatabase == null) {
            throw new UsernameNotFoundException("User " + login + " was not found in the database Orcid");
        } 

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_ORCID");
        grantedAuthorities.add(grantedAuthority);
        return new org.springframework.security.core.userdetails.User(login,
            userFromDatabase.getPasswordHash(), grantedAuthorities);
    }	
    
    
}
