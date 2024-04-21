package bg.conquerors.wardrobe.repository;

import bg.conquerors.wardrobe.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();

    Order findAllById(Long id);
}
