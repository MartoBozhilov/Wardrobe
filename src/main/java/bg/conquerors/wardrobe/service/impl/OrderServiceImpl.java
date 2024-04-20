package bg.conquerors.wardrobe.service.impl;

import bg.conquerors.wardrobe.model.dto.CartItemDTO;
import bg.conquerors.wardrobe.model.dto.CartViewDTO;
import bg.conquerors.wardrobe.model.dto.FinishOrderDTO;
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

import java.math.BigDecimal;
import java.util.Date;
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
    public void addProductToCart(String productNumber, SizeEnum size, Integer quantity) {
        Product productToAdd = productRepository
                .findByProductNumberAndSize(productNumber, size);

        Product mergedProduct = entityManager.merge(productToAdd);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setQuantity(quantity);
        orderDetail.setProduct(mergedProduct);

        User loggedUser = getLoggedUser();

        Order cart = getCart(loggedUser);

        orderDetail.setOrder(cart);

        OrderDetail detailFromDB = orderDetailRepository
                .findAllByOrderIdAndProductId(
                        orderDetail.getOrder().getId(),
                        productToAdd.getId()
                );

        if (detailFromDB == null) {
            orderDetailRepository.save(orderDetail);
        } else {
            detailFromDB.setQuantity(detailFromDB.getQuantity() + quantity);
        }
    }

    @Override
    public void removeProductFromCart(Long id) {

        OrderDetail itemToDelete = orderDetailRepository.findAllById(id);
        itemToDelete.setProduct(null);
        orderDetailRepository.saveAndFlush(itemToDelete);

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
            cartItemDTO.setSize(currentProduct.getSize());
            cartItemDTO.setProductName(currentProduct.getName());
            cartItemDTO.setProductImageUrl(currentProduct.getFirstImgUrl());

            cartViewDTO.getCartItems().add(cartItemDTO);
        }

        return cartViewDTO;
    }

    @Override
    public void saveOrder(FinishOrderDTO finishOrderDTO) throws Exception {

        Order orderToSave = orderRepository.findAllById(finishOrderDTO.getId());
        orderToSave.setAddress(finishOrderDTO.getAddress());

        Date date = new Date();
        orderToSave.setOrderDate(date);
        orderToSave.setStatus(OrderStatusEnum.ORDERED);

        BigDecimal totalPrice = getTotalPrice(orderToSave);
        changeProductsInventoriesQuantity(orderToSave);

        orderToSave.setTotalPrice(totalPrice);
        orderRepository.save(orderToSave);

        createNewOrder(getLoggedUser());
    }



    private void changeProductsInventoriesQuantity(Order orderToSave) throws Exception {
        Set<OrderDetail> orderDetailSet = orderToSave.getOrderInventories();

        for (OrderDetail orderDetail : orderDetailSet) {
            Product productToAlter = orderDetail.getProduct();

            if (productToAlter.getQuantity() - orderDetail.getQuantity() >= 0) {

                productToAlter.setQuantity(
                        productToAlter.getQuantity() - orderDetail.getQuantity()
                );
            } else {
                throw new Exception("Not enough quantity of product with id=" + productToAlter.getId());
            }

            productRepository.saveAndFlush(productToAlter);
        }

    }

    private static BigDecimal getTotalPrice(Order orderToSave) {
        BigDecimal totalPrice = new BigDecimal("0");

        for (OrderDetail orderDetail : orderToSave.getOrderInventories()) {
            BigDecimal orderDetailTotalSum =
                    orderDetail.getProduct().getPrice()
                            .multiply(BigDecimal.valueOf(orderDetail.getQuantity()));

            totalPrice = totalPrice.add(orderDetailTotalSum);
        }
        return totalPrice;
    }

    private static Order getCart(User loggedUser) {
        List<Order> userOrders = loggedUser.getOrders();
        return userOrders.stream()
                .filter(order -> order.getStatus().equals(OrderStatusEnum.CART))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();  // Using Spring Data JPA repository method findAll()
    }

    private User getLoggedUser() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String currentPrincipalUsername = authentication.getName();

        return userRepository.findByUsername(currentPrincipalUsername).get();
    }

}
