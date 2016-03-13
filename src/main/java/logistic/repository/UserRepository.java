package logistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import logistic.entity.User;

import java.util.Collection;

public interface UserRepository extends JpaRepository<User, Long> {
    Collection<User> findByClientClientId(Long clientId);


	
}
