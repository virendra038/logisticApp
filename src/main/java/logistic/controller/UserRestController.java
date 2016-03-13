package logistic.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import logistic.entity.User;
import logistic.repository.ClientRepository;
import logistic.repository.UserRepository;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



@RestController
@RequestMapping("clients/{clientId}/users")
public class UserRestController {
	
	private final UserRepository userRepository;
	private final ClientRepository clientRepository;
	
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> add(@PathVariable Long clientId, @RequestBody User input) {
		this.validateClient(clientId);
		return  this.clientRepository
				.findByClientId(clientId)
				.map(client -> {
					User result = userRepository.save(new User(client, input.username,input.firstname,input.lastname,input.contact,input.location));

					HttpHeaders httpHeaders = new HttpHeaders();
					httpHeaders.setLocation(ServletUriComponentsBuilder
							.fromCurrentRequest().path("/{id}")
							.buildAndExpand(result.getUserId()).toUri());
					return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
				}).get();

	}

	
	
	@RequestMapping(method = RequestMethod.GET)
	Collection<User> readusers(@PathVariable Long clientId) {
		this.validateClient(clientId);
		return this.userRepository.findByClientClientId(clientId);
	}
	
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	User readUser(@PathVariable Long clientId, @PathVariable Long userId) {
		this.validateClient(clientId);
		return this.userRepository.findOne(userId);
	}
	//delete single user
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable Long clientId, @PathVariable Long userId) {
		this.validateClient(clientId);
		userRepository.delete(userId);
		
		 return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
	
//	//delete_all_users
	@RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers(@PathVariable("clientId") Long clientId) {
        //System.out.println("Deleting All Users");
		this.validateClient(clientId);
        userRepository.deleteAll();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
	
	
	private void validateClient(Long clientId)
	{
		this.clientRepository.findByClientId(clientId).orElseThrow(
				() -> new ClientNotFoundException(clientId));
	}
	
	
	
	
	@Autowired
	UserRestController(UserRepository userRepository,
			ClientRepository clientRepository) 
	{
		this.userRepository = userRepository;
		this.clientRepository = clientRepository;
	}

}


@ResponseStatus(HttpStatus.NOT_FOUND)
class ClientNotFoundException extends RuntimeException {

	public ClientNotFoundException(Long clientId) {
		super("could not find user '" + clientId + "'.");
	}
}