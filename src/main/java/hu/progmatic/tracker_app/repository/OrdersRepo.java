package hu.progmatic.tracker_app.repository;

import hu.progmatic.tracker_app.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Integer> {
}
