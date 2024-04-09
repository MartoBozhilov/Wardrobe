package bg.conquerors.wardrobe.service.impl;

import bg.conquerors.wardrobe.model.dto.ViewProductsDTO;
import bg.conquerors.wardrobe.model.entity.Product;
import bg.conquerors.wardrobe.repository.ProductRepository;
import bg.conquerors.wardrobe.service.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ViewProductsDTO> getViewOfProducts() {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String UserName = authentication.getName();



        return mapProductView(productRepository.findAll());
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

}
