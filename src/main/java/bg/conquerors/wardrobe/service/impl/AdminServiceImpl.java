package bg.conquerors.wardrobe.service.impl;

import bg.conquerors.wardrobe.model.dto.AddDiscountDTO;
import bg.conquerors.wardrobe.model.dto.AddOrderDTO;
import bg.conquerors.wardrobe.model.dto.AddProductDTO;
import bg.conquerors.wardrobe.model.entity.*;
import bg.conquerors.wardrobe.model.enums.OrderStatusEnum;
import bg.conquerors.wardrobe.model.enums.SizeEnum;
import bg.conquerors.wardrobe.repository.*;
import bg.conquerors.wardrobe.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {

    private final ProductRepository productRepository;
    private final TagRepository tagRepository;

    private final DiscountRepository discountRepository;

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    private final UserRepository userRepository;

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

    //endregion

    //region <Orders CRUD>
   /* @Override
    public void addOrder(AddOrderDTO addOrderDTO) {

    }

    private Order getNewOrder(AddOrderDTO addOrderDTO) {
        Order order = new Order();

        discount.setDiscountPercentage(addDiscountDTO.getDiscountPercentage());
        discount.setStartDate(addDiscountDTO.getStartDate());
        discount.setEndDate(addDiscountDTO.getEndDate());

        return order;
    }*/

    @Override
    public void editOrder(Long id, AddOrderDTO addOrderDTO) {

        System.out.println(addOrderDTO.getOrderInventories().size());
        //Order order = setOrder(orderRepository.findAllById(id), addOrderDTO);

        //orderRepository.save(order);
    }

    private Order setOrder(Order order, AddOrderDTO addOrderDTO) {

        order.setOrderDate(addOrderDTO.getOrderDate());
        order.setStatus(addOrderDTO.getStatus());
        order.setAddress(addOrderDTO.getAddress());
        order.setTotalPrice(addOrderDTO.getTotalPrice());
        order.setUser(userRepository.findById(addOrderDTO.getUserId()).get());
        order.setOrderInventories(addOrderDTO.getOrderInventories());

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

        orderDetailRepository.delete(orderDetail);
    }

    @Override
    public void addOrderProduct(Long orderId, Long productId, Integer quantity) {
        OrderDetail orderDetail = new OrderDetail();
        Order order = orderRepository.findAllById(orderId);
        Product product = productRepository.findAllById(productId);

        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetail.setQuantity(quantity);

        orderDetailRepository.save(orderDetail);
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
    //endregion
}
