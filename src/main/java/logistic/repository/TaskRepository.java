package logistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import logistic.entity.Task;

import java.util.Collection;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Collection<Task> findByOwnerClientId(Long clientId);

	Task findBytaskId(Long taskId);


	
}






