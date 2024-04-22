package bg.conquerors.wardrobe.service.impl;

import bg.conquerors.wardrobe.model.dto.*;
import bg.conquerors.wardrobe.model.entity.Discount;
import bg.conquerors.wardrobe.model.entity.Order;
import bg.conquerors.wardrobe.model.entity.OrderDetail;
import bg.conquerors.wardrobe.model.entity.Product;
import bg.conquerors.wardrobe.model.entity.Tag;
import bg.conquerors.wardrobe.model.entity.*;
import bg.conquerors.wardrobe.model.enums.OrderStatusEnum;
import bg.conquerors.wardrobe.model.enums.SizeEnum;
import bg.conquerors.wardrobe.repository.DiscountRepository;
import bg.conquerors.wardrobe.repository.OrderDetailRepository;
import bg.conquerors.wardrobe.repository.OrderRepository;
import bg.conquerors.wardrobe.repository.ProductRepository;
import bg.conquerors.wardrobe.repository.TagRepository;
import bg.conquerors.wardrobe.repository.UserRepository;
import bg.conquerors.wardrobe.model.enums.UserRoleEnum;
import bg.conquerors.wardrobe.service.AdminService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {

    private final ProductRepository productRepository;
    private final TagRepository tagRepository;

    private final DiscountRepository discountRepository;

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    private final UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    public AdminServiceImpl(ProductRepository productRepository,
                            TagRepository tagRepository,
                            DiscountRepository discountRepository,
                            OrderRepository orderRepository,
                            OrderDetailRepository orderDetailRepository,
                            UserRepository userRepository) {
        this.productRepository = productRepository;
        this.tagRepository = tagRepository;
        this.discountRepository = discountRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.userRepository = userRepository;
    }


    //region <Product CRUD>
    @Override
    public void addProduct(AddProductDTO addProductDTO) {

        Tag tag = getTag(addProductDTO);

        for (Product newProduct : getNewProduct(addProductDTO)) {
            newProduct.setTag(tag);
            productRepository.save(newProduct);
        }
    }

    @Override
    public void editProduct(String productNumber, AddProductDTO addProductDTO) {

        List<Product> products = setProduct(productRepository.findAllByProductNumber(productNumber), addProductDTO);

        for (Product product : products) {
            productRepository.save(product);
        }
    }

    @Override
    public AddProductDTO getProductByProductNumber(String productNumber) {

        List<Product> products = productRepository.findAllByProductNumber(productNumber);

        if (products == null)
            return null;

        return createProductDTO(products);
    }

    @Override
    public void deleteProduct(String productNumber) {
        List<Product> products = productRepository.findAllByProductNumber(productNumber);
        if (!products.isEmpty()) {
            for (Product product : products) {
                Tag tag = product.getTag();
                Long countOfTags = productRepository.countByTagId(tag.getId());
                System.out.println(tag);
                System.out.println(countOfTags);
                product.setTag(null);
                productRepository.save(product);
                productRepository.delete(product);
                if (tag != null && countOfTags <= 4) {
                    tagRepository.delete(tag);
                }
            }
        } else {
            throw new IllegalArgumentException("No products found with product number: " + productNumber);
        }
    }

    private List<Product> getNewProduct(AddProductDTO addProductDTO) {

        List<Product> createProducts = new ArrayList<>();

        for (var sizeValue : addProductDTO.getQuantities().keySet()) {

            SizeEnum size = SizeEnum.valueOf(sizeValue);

            Product createProduct = new Product();
            createProduct.setProductNumber(addProductDTO.getProductNumber());
            createProduct.setName(addProductDTO.getName());
            createProduct.setSize(size);
            createProduct.setDescription(addProductDTO.getDescription());
            createProduct.setQuantity(addProductDTO.getQuantities().get(sizeValue));
            createProduct.setPrice(addProductDTO.getPrice());
            createProduct.setMinPrice(addProductDTO.getMinPrice());

            createProduct.setFirstImgUrl(addProductDTO.getFirstImgUrl());
            createProduct.setSecondImgUrl(addProductDTO.getSecondImgUrl());
            createProduct.setThirdImgUrl(addProductDTO.getThirdImgUrl());

            createProducts.add(createProduct);
        }


        return createProducts;
    }

    private AddProductDTO createProductDTO(List<Product> products) {

        AddProductDTO addProductDTO = new AddProductDTO();

        Product product = products.get(0);

        addProductDTO.setProductNumber(product.getProductNumber());
        addProductDTO.setName(product.getName());
        addProductDTO.setDescription(product.getDescription());
        addProductDTO.setPrice(product.getPrice());
        addProductDTO.setMinPrice(product.getMinPrice());

        addProductDTO.setFirstImgUrl(product.getFirstImgUrl());
        addProductDTO.setSecondImgUrl(product.getSecondImgUrl());
        addProductDTO.setThirdImgUrl(product.getThirdImgUrl());

        addProductDTO.setCategory(product.getTag().getCategory());
        addProductDTO.setGender(product.getTag().getGender());
        addProductDTO.setStyle(product.getTag().getStyle());

        Map<String, Integer> quantity = new Hashtable<>();

        for (Product p : products) {
            quantity.put(p.getSize().toString(), p.getQuantity());
        }

        addProductDTO.setQuantities(quantity);

        return addProductDTO;
    }

    private List<Product> setProduct(List<Product> products, AddProductDTO addProductDTO) {

        for (var sizeValue : addProductDTO.getQuantities().keySet()) {

            SizeEnum size = SizeEnum.valueOf(sizeValue);

            Product updatedProduct = products.stream().filter(product1 -> product1.getSize().equals(size)).findFirst().get();

            int index = products.indexOf(updatedProduct);

            updatedProduct.setProductNumber(addProductDTO.getProductNumber());
            updatedProduct.setName(addProductDTO.getName());
            updatedProduct.setSize(size);
            updatedProduct.setDescription(addProductDTO.getDescription());
            updatedProduct.setQuantity(addProductDTO.getQuantities().get(sizeValue));
            updatedProduct.setPrice(addProductDTO.getPrice());
            updatedProduct.setMinPrice(addProductDTO.getMinPrice());

            updatedProduct.setFirstImgUrl(addProductDTO.getFirstImgUrl());
            updatedProduct.setSecondImgUrl(addProductDTO.getSecondImgUrl());
            updatedProduct.setThirdImgUrl(addProductDTO.getThirdImgUrl());

            updateTag(addProductDTO, updatedProduct.getTag());

            products.set(index, updatedProduct);
        }

        return products;
    }

    private Tag getTag(AddProductDTO addProductDTO) {

        Tag searchedTag = tagRepository
                .findByGenderAndCategoryAndStyle(
                        addProductDTO.getGender(),
                        addProductDTO.getCategory(),
                        addProductDTO.getStyle()
                );

        if (searchedTag != null) {
            return searchedTag;
        }

        Tag newTag = new Tag(
                addProductDTO.getGender(),
                addProductDTO.getCategory(),
                addProductDTO.getStyle()
        );

        tagRepository.save(newTag);

        return newTag;
    }

    private void updateTag(AddProductDTO addProductDTO, Tag tag) {


        boolean isChange = false;

        if (!tag.getCategory().equals(addProductDTO.getCategory())) {
            tag.setCategory(addProductDTO.getCategory());
            isChange = true;
        }

        if (!tag.getGender().equals(addProductDTO.getGender())) {
            tag.setGender(addProductDTO.getGender());
            isChange = true;
        }

        if (!tag.getStyle().equals(addProductDTO.getStyle())) {
            tag.setStyle(addProductDTO.getStyle());
            isChange = true;
        }
        if (isChange) tagRepository.save(tag);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    //endregion

    //region <Orders CRUD>

    @Override
    public void editOrder(Long id, AddOrderDTO addOrderDTO) {

        Order order = setOrder(orderRepository.findAllById(id), addOrderDTO);

        orderRepository.save(order);

        deleteNullOrderDetails();
    }

    private void deleteNullOrderDetails() {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        for (var orderDetail : orderDetails) {
            if (orderDetail.getOrder() == null) {
                orderDetailRepository.delete(orderDetail);
            }
        }
    }

    private Order setOrder(Order order, AddOrderDTO addOrderDTO) {

        order.setOrderDate(addOrderDTO.getOrderDate());
        order.setStatus(addOrderDTO.getStatus());
        order.setAddress(addOrderDTO.getAddress());

        order.setUser(userRepository.findById(addOrderDTO.getUserId()).get());

        List<OrderDetail> newOrderDetails = new ArrayList<>();
        BigDecimal totalPrice = new BigDecimal(0);
        for (var orderDetail : addOrderDTO.getOrderInventories()) {
            OrderDetail orderDetail1 = orderDetailRepository.findAllById(orderDetail.getId());
            orderDetail1.setQuantity(addOrderDTO.getOrderInventories().get(addOrderDTO.getOrderInventories().indexOf(orderDetail)).getQuantity());
            newOrderDetails.add(orderDetail1);
            totalPrice = updateTotalPrice(totalPrice, orderDetail1);
        }

        order.setOrderInventories(newOrderDetails);

        order.setTotalPrice(totalPrice);

        return order;
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findAllById(id);

        orderDetailRepository.deleteAll(order.getOrderInventories());

        orderRepository.delete(order);
    }

    @Override
    public void deleteOrderProduct(Long id) {
        OrderDetail orderDetail = orderDetailRepository.findAllById(id);
        Order order = orderDetail.getOrder();

        order.setTotalPrice(updateTotalPrice(order.getTotalPrice(), orderDetail, true));

        orderRepository.save(order);
        orderDetailRepository.delete(orderDetail);
    }

    @Override
    @Transactional
    public void addOrderProduct(Long orderId, Long productId, Integer quantity) {

        OrderDetail orderDetail = new OrderDetail();

        Order order = orderRepository.findAllById(orderId);
        Product product = productRepository.findAllById(productId);
        Product mergedProduct = entityManager.merge(product);

        orderDetail.setOrder(order);
        orderDetail.setProduct(mergedProduct);
        orderDetail.setQuantity(quantity);

        order.setTotalPrice(updateTotalPrice(order.getTotalPrice(), orderDetail));

        orderRepository.save(order);

        orderDetailRepository.save(orderDetail);
    }

    public BigDecimal updateTotalPrice(BigDecimal totalPrice, OrderDetail orderDetail)
    {
       return updateTotalPrice(totalPrice, orderDetail, false);
    }

    private BigDecimal updateTotalPrice(BigDecimal totalPrice, OrderDetail orderDetail, boolean isMinus) {
        if (!isMinus) {
            totalPrice = orderDetail.getProduct().getPrice().multiply(BigDecimal.valueOf(orderDetail.getQuantity())).add(totalPrice);
            return totalPrice;
        }else {
            totalPrice = totalPrice.subtract(orderDetail.getProduct().getPrice().multiply(BigDecimal.valueOf(orderDetail.getQuantity())));
            return totalPrice;
        }
    }

    @Override
    public AddOrderDTO getOrderById(Long id) {
        Order order = orderRepository.findAllById(id);

        if (order == null)
            return null;

        return createOrderDTO(order);
    }

    private AddOrderDTO createOrderDTO(Order order) {

        AddOrderDTO addOrderDTO = new AddOrderDTO();

        addOrderDTO.setOrderDate(order.getOrderDate());
        addOrderDTO.setStatus(order.getStatus());
        addOrderDTO.setAddress(order.getAddress());
        addOrderDTO.setTotalPrice(order.getTotalPrice());
        addOrderDTO.setUserId(order.getUser().getId());
        addOrderDTO.setOrderInventories(order.getOrderInventories());


        return addOrderDTO;
    }

    @Override
    public void changeStatus(Long id) {
        Order order = orderRepository.findAllById(id);

        if (order.getStatus() == OrderStatusEnum.ORDERED)
            order.setStatus(OrderStatusEnum.SHIPPED);
        else if (order.getStatus() == OrderStatusEnum.SHIPPED)
            order.setStatus(OrderStatusEnum.DELIVERED);

        orderRepository.save(order);
    }

    //endregion

    //region <Discount CRUD>
    @Override
    public void addDiscount(AddDiscountDTO addDiscountDTO) {

        Discount newDiscount = getNewDiscount(addDiscountDTO);

        discountRepository.save(newDiscount);
    }

    private Discount getNewDiscount(AddDiscountDTO addDiscountDTO) {
        Discount discount = new Discount();

        discount.setDiscountPercentage(addDiscountDTO.getDiscountPercentage());
        discount.setStartDate(addDiscountDTO.getStartDate());
        discount.setEndDate(addDiscountDTO.getEndDate());

        return discount;
    }

    @Override
    public void editDiscount(Long id, AddDiscountDTO addDiscountDTO) {
        Discount discount = setDiscount(discountRepository.findAllById(id), addDiscountDTO);

        discountRepository.save(discount);
    }

    private Discount setDiscount(Discount discount, AddDiscountDTO addDiscountDTO) {

        discount.setDiscountPercentage(addDiscountDTO.getDiscountPercentage());
        discount.setStartDate(addDiscountDTO.getStartDate());
        discount.setEndDate(addDiscountDTO.getEndDate());

        return discount;
    }

    @Override
    public AddDiscountDTO getDiscountById(Long id) {

        Discount discount = discountRepository.findAllById(id);

        if (discount == null)
            return null;

        return createDiscountDTO(discount);
    }

    private AddDiscountDTO createDiscountDTO(Discount discount) {
        AddDiscountDTO addDiscountDTO = new AddDiscountDTO();

        addDiscountDTO.setDiscountPercentage(discount.getDiscountPercentage());
        addDiscountDTO.setStartDate(discount.getStartDate());
        addDiscountDTO.setEndDate(discount.getEndDate());

        return addDiscountDTO;
    }

    @Override
    public void deleteDiscount(Long id) {
        discountRepository.delete(discountRepository.findAllById(id));
    }

    @Override
    public void setDiscountToProduct(SetDiscountToProductDTO setDiscountToProductDTO) {

        Discount discount = discountRepository.findAllById(setDiscountToProductDTO.getDiscountId());

        for (String productNumber : setDiscountToProductDTO.getProductsProductNumbers()) {
            List<Product> products = productRepository.findAllByProductNumber(productNumber);
            for (var product : products) {
                product.setDiscount(discount);
                productRepository.save(product);
            }
        }

    }
    //endregion

    //region <Users CRUD>

    @Override
    public void editUser(Long id, AddUserDTO addUserDTO) {

        User user = setUser(userRepository.findAllById(Collections.singleton(id)).get(0), addUserDTO);

        userRepository.save(user);
    }

    private User setUser(User user, AddUserDTO addUserDTO) {

        if (addUserDTO.isAdmin()) {
            if (!user.getRoles().stream().anyMatch((a) -> {
                return a.getRole().equals(UserRoleEnum.ADMIN);
            })) {
                List<UserRole> userRoles = user.getRoles();
                userRoles.add(new UserRole(UserRoleEnum.ADMIN));
                user.setRoles(userRoles);
            }
        } else {
            if (user.getRoles().stream().anyMatch((a) -> {
                return a.getRole().equals(UserRoleEnum.ADMIN);
            })) {
                List<UserRole> userRoles = user.getRoles();
                userRoles.remove(new UserRole(UserRoleEnum.ADMIN));
                user.setRoles(userRoles);
            }
        }

        user.setEmail(addUserDTO.getEmail());
        user.setPassword(addUserDTO.getPassword());
        user.setPoints(addUserDTO.getPoints());
        user.setUsername(addUserDTO.getUsername());
        user.setFirstName(addUserDTO.getFirstName());
        user.setLastName(addUserDTO.getLastName());
        user.setPhoneNumber(addUserDTO.getPhoneNumber());

        return user;
    }

    @Override
    public void deleteUser(Long id) {
        User user = (User) userRepository.findAllById(Collections.singleton(id));

        if (user == null)
            return;

        userRepository.delete(user);
    }

    @Override
    public AddUserDTO getUserById(Long id) {

        User user = userRepository.findAllById(Collections.singleton(id)).get(0);

        if (user == null)
            return null;

        return createUserDTO(user);
    }

    private AddUserDTO createUserDTO(User user) {

        AddUserDTO addUserDTO = new AddUserDTO();

        addUserDTO.setAdmin(user.getRoles().stream().anyMatch((a) -> {
            return a.getRole().equals(UserRoleEnum.ADMIN);
        }));
        addUserDTO.setEmail(user.getEmail());
        addUserDTO.setPassword(user.getPassword());
        addUserDTO.setPoints(user.getPoints());
        addUserDTO.setUsername(user.getUsername());
        addUserDTO.setFirstName(user.getFirstName());
        addUserDTO.setLastName(user.getLastName());
        addUserDTO.setPhoneNumber(user.getPhoneNumber());


        return addUserDTO;
    }

    //endregion


    //region <Statistic>
    @Override
    public StatisticDTO getStatistics(Date startDate, Date endDate) {
        List<Order> ordersInRange = orderRepository
                .findAllByOrderDateBetweenAndStatusNot(
                        startDate, endDate, OrderStatusEnum.CART
                );

        StatisticDTO statisticDTO = new StatisticDTO();
        statisticDTO.setStartDate(startDate);
        statisticDTO.setEndDate(endDate);

        for (Order currentOrder : ordersInRange) {
            statisticDTO.getOrders().add(mapOrderToAddOrderDTO(currentOrder));

            statisticDTO.setTotalIncome(
                    statisticDTO.getTotalIncome().add(currentOrder.getTotalPrice())
            );

            if (currentOrder.getStatus().equals(OrderStatusEnum.ORDERED)) {
                statisticDTO.setOrderedIncome(
                        statisticDTO.getOrderedIncome().add(currentOrder.getTotalPrice())
                );
            } else if (currentOrder.getStatus().equals(OrderStatusEnum.SHIPPED)) {
                statisticDTO.setShippedIncome(
                        statisticDTO.getShippedIncome().add(currentOrder.getTotalPrice())
                );
            } else if (currentOrder.getStatus().equals(OrderStatusEnum.DELIVERED)) {
                statisticDTO.setDeliveredIncome(
                        statisticDTO.getDeliveredIncome().add(currentOrder.getTotalPrice())
                );
            }
        }

        return statisticDTO;
    }

    private AddOrderDTO mapOrderToAddOrderDTO(Order order) {
        AddOrderDTO addOrderDTO = new AddOrderDTO();

        addOrderDTO.setOrderDate(order.getOrderDate());
        addOrderDTO.setOrderInventories(order.getOrderInventories());
        addOrderDTO.setStatus(order.getStatus());
        addOrderDTO.setTotalPrice(order.getTotalPrice());
        addOrderDTO.setAddress(order.getAddress());
        addOrderDTO.setUserId(order.getUser().getId());

        return addOrderDTO;
    }
//endregion

    @Override
    public List<ViewProductsDTO> findAllByDiscount() {
        return ProductServiceImpl.mapProductView(productRepository.findByDiscountIsNotNull());
    }



}
