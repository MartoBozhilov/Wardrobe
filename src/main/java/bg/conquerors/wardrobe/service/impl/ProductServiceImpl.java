package bg.conquerors.wardrobe.service.impl;

import bg.conquerors.wardrobe.model.dto.ViewProductDTO;
import bg.conquerors.wardrobe.model.dto.ViewProductsDTO;
import bg.conquerors.wardrobe.model.entity.Discount;
import bg.conquerors.wardrobe.model.entity.Product;
import bg.conquerors.wardrobe.model.enums.SizeEnum;
import bg.conquerors.wardrobe.repository.DiscountRepository;
import bg.conquerors.wardrobe.repository.ProductRepository;
import bg.conquerors.wardrobe.service.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final DiscountRepository discountRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              DiscountRepository discountRepository) {
        this.productRepository = productRepository;
        this.discountRepository = discountRepository;
    }
    @Override
    public List<ViewProductsDTO> getViewOfProducts() {


        return mapProductView(productRepository.findAll());
    }

    @Override
    public ViewProductDTO getProductByProductNumber(String productNumber) {

        List<Product> products = productRepository.findAllByProductNumber(productNumber);
        ViewProductDTO viewProductDTO = new ViewProductDTO();
        viewProductDTO.setProductNumber(productNumber);
        viewProductDTO.setName(products.get(0).getName());
        viewProductDTO.setDescription(products.get(0).getDescription());
        viewProductDTO.setFirstImgUrl(products.get(0).getFirstImgUrl());
        viewProductDTO.setSecondImgUrl(products.get(0).getSecondImgUrl());

        if (!products.get(0).getThirdImgUrl().equals("")) {
            viewProductDTO.setThirdImgUrl(products.get(0).getThirdImgUrl());
        }

        viewProductDTO.setPrice(calculateProduct(products));
        viewProductDTO.setSizeQuantityMap(new HashMap<>());
        viewProductDTO.getSizeQuantityMap().put(SizeEnum.S, products.get(0).getQuantity());
        viewProductDTO.getSizeQuantityMap().put(SizeEnum.M, products.get(1).getQuantity());
        viewProductDTO.getSizeQuantityMap().put(SizeEnum.L, products.get(2).getQuantity());
        viewProductDTO.getSizeQuantityMap().put(SizeEnum.XL, products.get(3).getQuantity());

        return viewProductDTO;
    }

    private List<ViewProductsDTO> mapProductView(List<Product> products) {
        List<ViewProductsDTO> returnProducts = new ArrayList<>();
        List<String> productNumbers = new ArrayList<>();

        for (var product : products) {
            ViewProductsDTO productDTO = new ViewProductsDTO();

            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setPrice(product.getPrice());
            productDTO.setImgUrl(product.getFirstImgUrl());

            if(!productNumbers.contains(product.getProductNumber())) {
                productNumbers.add(product.getProductNumber());
                returnProducts.add(productDTO);
            }
        }

        return returnProducts;
    }
    private BigDecimal calculateProduct(List<Product> products) {
        Discount productDiscount = discountRepository.findById(products.get(0).getId()).orElse(null);

        if (productDiscount != null) {
            BigDecimal discountPercent = BigDecimal
                    .valueOf(Double.parseDouble(productDiscount.getDiscountPercentage())
                    );

            BigDecimal discountPrice = (discountPercent.multiply(products.get(0).getPrice())).divide(BigDecimal.valueOf(100));

            if ((discountPrice.compareTo(products.get(0).getPrice())) > 0) {
                return discountPrice;
            }
        }

        return products.get(0).getPrice();
    }
}
