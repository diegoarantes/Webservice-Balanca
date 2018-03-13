package com.incopostes.balanca.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.incopostes.balanca.model.Peso;
import com.incopostes.balanca.util.BalancaUtil;

@RestController
public class PesoResource {
	@Autowired
	BalancaUtil service;

	@RequestMapping(value = "/peso", method = RequestMethod.GET)
	public ResponseEntity<Peso> retornaPeso() {
		return new ResponseEntity<>(service.retornaPeso(), HttpStatus.OK);
	}
}