package bg.conquerors.wardrobe.service.impl;

import bg.conquerors.wardrobe.model.entity.Order;
import bg.conquerors.wardrobe.model.entity.OrderDetail;
import bg.conquerors.wardrobe.model.entity.Product;
import bg.conquerors.wardrobe.model.entity.User;
import bg.conquerors.wardrobe.model.enums.OrderStatusEnum;
import bg.conquerors.wardrobe.model.enums.SizeEnum;
import bg.conquerors.wardrobe.repository.OrderDetailRepository;
import bg.conquerors.wardrobe.repository.OrderRepository;
import bg.conquerors.wardrobe.repository.ProductRepository;
import bg.conquerors.wardrobe.repository.UserRepository;
import bg.conquerors.wardrobe.service.OrderService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderDetailRepository orderDetailRepository,
                            ProductRepository productRepository,
                            UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createNewOrder(User user) {
        Order order = new Order();
        order.setStatus(OrderStatusEnum.CART);
        order.setOrderInventories(new HashSet<>());
        order.setUser(user);

        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void addProductToCart(String productNumber, SizeEnum size) {
        Product productToAdd = productRepository
                .findByProductNumberAndSize(productNumber, size);

        Product mergedProduct = entityManager.merge(productToAdd);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setQuantity(1);
        orderDetail.setProduct(mergedProduct);

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String currentPrincipalUsername = authentication.getName();

        User loggedUser = userRepository.findByUsername(currentPrincipalUsername).get();

        List<Order> userOrders = loggedUser.getOrders();
        Order cart = null;

        for (Order userCart : userOrders) {
            if (userCart.getStatus().equals(OrderStatusEnum.CART)) {
                cart = userCart;
            }
        }

        orderDetail.setOrder(cart);
        orderDetailRepository.save(orderDetail);
    }

}
