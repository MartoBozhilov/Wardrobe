package bg.conquerors.wardrobe.service.impl;

import bg.conquerors.wardrobe.model.dto.CartItemDTO;
import bg.conquerors.wardrobe.model.dto.CartViewDTO;
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
import java.util.Set;

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

        User loggedUser = getLoggedUser();

        Order cart = getCart(loggedUser);

        orderDetail.setOrder(cart);
        orderDetailRepository.save(orderDetail);
    }

    @Override
    public void removeProductFromCart(Long id) {

        orderDetailRepository.deleteById(id);
    }

    @Override
    public CartViewDTO getCart() {
        User loggedUser = getLoggedUser();

        Order cartOfUser = getCart(loggedUser);

        Set<OrderDetail> orderDetails = cartOfUser.getOrderInventories();

        CartViewDTO cartViewDTO = new CartViewDTO();
        cartViewDTO.setId(cartOfUser.getId());
        cartViewDTO.setCartItems(new HashSet<>());

        for (OrderDetail cartDetail : orderDetails) {
            CartItemDTO cartItemDTO = new CartItemDTO();

            cartItemDTO.setId(cartDetail.getId());
            cartItemDTO.setQuantity(cartDetail.getQuantity());

            Product currentProduct = cartDetail.getProduct();
            cartItemDTO.setPrice(currentProduct.getPrice());
            cartItemDTO.setProductName(currentProduct.getName());
            cartItemDTO.setProductImageUrl(currentProduct.getFirstImgUrl());

            cartViewDTO.getCartItems().add(cartItemDTO);
        }

        return cartViewDTO;
    }

    private static Order getCart(User loggedUser) {
        List<Order> userOrders = loggedUser.getOrders();
        return userOrders.stream()
                .filter(order -> order.getStatus().equals(OrderStatusEnum.CART))
                .findFirst()
                .orElse(null);
    }

    private User getLoggedUser() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String currentPrincipalUsername = authentication.getName();

        return userRepository.findByUsername(currentPrincipalUsername).get();
    }

}
