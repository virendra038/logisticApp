package logistic.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import logistic.entity.Client;
import logistic.entity.Task;
import logistic.entity.User;
import logistic.repository.TaskRepository;
import logistic.repository.ClientRepository;


@RestController
@RequestMapping("/task")
public class TaskRestController {

	private final TaskRepository taskRepository;
	private final ClientRepository clientRepository;
	
	// get request
	@RequestMapping(method = RequestMethod.GET)
	Collection<Task> readTask() {
		return this.taskRepository.findAll();
	}
	
	@RequestMapping(value="/{taskId}",method = RequestMethod.GET)
	Task readOneTask(@PathVariable Long taskId) {
		
		return this.taskRepository.findBytaskId(taskId);
	}
	
	
	
	
	
	@RequestMapping(value="/{clientId}",method = RequestMethod.POST)
	ResponseEntity<?> add(@PathVariable Long clientId, @RequestBody Task task) {
		this.validateClient(clientId);
		return  this.clientRepository
				.findByClientId(clientId)
				.map(owner -> {
					Task result = taskRepository.save(new Task(owner, task.pickup, task.duration,task.drop,task.exId));

					HttpHeaders httpHeaders = new HttpHeaders();
					httpHeaders.setLocation(ServletUriComponentsBuilder
							.fromCurrentRequest().path("/{id}")
							.buildAndExpand(result.getTaskId()).toUri());
					return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
				}).get();

	}
	
	
	@RequestMapping(value="/assign/{clientId}/{taskId}",method = RequestMethod.PATCH)
	ResponseEntity<Client> add(@PathVariable Long clientId,@PathVariable Long taskId) {
		this.validateClient(clientId);
		 Client k = clientRepository.findOne(clientId);
		 Task cp = taskRepository.findOne(taskId);
		 cp.setOwner(k);
		 taskRepository.save(cp);
		 System.out.println(cp.getOwner());
		 return new ResponseEntity<Client>(HttpStatus.OK);
		 
	}
	
	
	@Autowired
	TaskRestController(TaskRepository taskRepository,ClientRepository clientRepository) 
	{
		this.taskRepository = taskRepository;
		this.clientRepository = clientRepository;
	}
	
	private void validateClient(Long clientId)
	{
		this.clientRepository.findByClientId(clientId).orElseThrow(
				() -> new ClientNotFoundException(clientId));
	}
}


