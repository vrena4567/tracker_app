package hu.progmatic.tracker_app.repository;

import hu.progmatic.tracker_app.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {
}
