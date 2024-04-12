package bg.conquerors.wardrobe.service.impl;

import bg.conquerors.wardrobe.model.entity.Order;
import bg.conquerors.wardrobe.model.entity.User;
import bg.conquerors.wardrobe.model.enums.OrderStatusEnum;
import bg.conquerors.wardrobe.repository.OrderRepository;
import bg.conquerors.wardrobe.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void createNewOrder(User user) {
        Order order = new Order();
        order.setStatus(OrderStatusEnum.CART);
        order.setOrderInventories(new HashSet<>());
        order.setUser(user);

        orderRepository.save(order);
    }
}
