package br.com.app.store.Store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.app.store.Store.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT t FROM User t WHERE t.username = ?1 AND t.password = ?2")
	List<User> findUserByUsernameAndPassword(String username, String password);

	@Query("SELECT t FROM User t WHERE t.username = ?1")
	List<User> findUserByUsername(String username);
}
