package com.edupay.authentication.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edupay.authentication.data.model.EduPayUser;
import com.edupay.authentication.data.repository.EduPayUserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class EduPayAuthenticationController {
	
	@Autowired
	private EduPayUserServiceImpl eduPayUserServiceImpl;
	@RequestMapping(value = "/authenticateUser", method = RequestMethod.POST)
    public ResponseEntity < String > authenticateUser(@RequestBody Map<String,Object> inputMap) {
		ObjectMapper mapper = new ObjectMapper();
		EduPayUser user = mapper.convertValue(inputMap, EduPayUser.class);
		eduPayUserServiceImpl.getUserByUsername(user.getUsername());
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
}
