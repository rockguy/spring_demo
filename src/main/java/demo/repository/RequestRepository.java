package demo.repository;

import demo.model.Request;
import demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
