package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.user.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/user")
public interface UsersController
{
    
	@RequestMapping(method=RequestMethod.POST, value=CreateAccount.REQUEST_NAME)
	public ResponseEntity<String> createAccount(@RequestBody Map<String, String> request) throws Exception;
	
	@RequestMapping(method=RequestMethod.POST, value=EditAccount.REQUEST_NAME)
	public ResponseEntity<String> editAccount(@RequestBody Map<String, String> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value=Login.REQUEST_NAME)
    public ResponseEntity<String> login(@RequestBody Map<String, String> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value=Promote.REQUEST_NAME)
    public ResponseEntity<String> promote(@RequestBody Map<String, String> request) throws Exception;

	@RequestMapping(method=RequestMethod.POST, value=DeleteAccount.REQUEST_NAME)
	public ResponseEntity<String> deleteAccount(@RequestBody Map<String, String> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value=ViewRoster.REQUEST_NAME)
    public ResponseEntity<String> viewRoster(@RequestBody Map<String, String> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value=ViewUser.REQUEST_NAME)
    public ResponseEntity<String> viewUser(@RequestBody Map<String, String> request) throws Exception;
}

	