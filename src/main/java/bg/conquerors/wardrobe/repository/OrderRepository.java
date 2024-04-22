package bg.conquerors.wardrobe.repository;

import bg.conquerors.wardrobe.model.entity.Order;
import bg.conquerors.wardrobe.model.enums.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();

    Order findAllById(Long id);

    List<Order> findAllByOrderDateBetweenAndStatusNot(Date startDate, Date endDate, OrderStatusEnum status);

}
