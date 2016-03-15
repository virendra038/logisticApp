package logistic.controller;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import logistic.entity.Client;
import logistic.repository.ClientRepository;
import logistic.repository.UserRepository;

@RestController
@RequestMapping("/clients")
public class ClientRestController {
	
	
	private final ClientRepository clientRepository;
	private final UserRepository userRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	Collection<Client> readAccount() {
		return this.clientRepository.findAll();
	}
	
	@RequestMapping(value = "/{clientId}", method = RequestMethod.GET)
	Client readClient(@PathVariable("clientId") Long clientId) {
		//this.validateUser(clientId);
		return this.clientRepository.findOne(clientId);
	}
	
	@RequestMapping(method = RequestMethod.POST) 
	 public Client createClient(@RequestBody Client client) { 
	  return this.clientRepository.save( 
	    new Client( 
	     
	      client.getClientname(), 
	      client.getPassword()
	      )); 
	 } 
	
	
	@RequestMapping(value = "/{clientId}", method = RequestMethod.DELETE)
	ResponseEntity<Client> deleteClient(@PathVariable("clientId") Long clientId) {
		//this.validateUser(clientId);
	    
		clientRepository.delete(clientId);
		 return new ResponseEntity<Client>(HttpStatus.NO_CONTENT);
	}
	
	
	@Autowired
	ClientRestController(	ClientRepository clientRepository, UserRepository userRepository) 
	{
		this.clientRepository = clientRepository;
		this.userRepository = userRepository;
	}

}