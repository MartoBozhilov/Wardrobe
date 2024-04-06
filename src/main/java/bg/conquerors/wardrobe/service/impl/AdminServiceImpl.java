package bg.conquerors.wardrobe.service.impl;

import bg.conquerors.wardrobe.model.dto.AddProductDTO;
import bg.conquerors.wardrobe.model.entity.Product;
import bg.conquerors.wardrobe.model.entity.Tag;
import bg.conquerors.wardrobe.repository.ProductRepository;
import bg.conquerors.wardrobe.repository.TagRepository;
import bg.conquerors.wardrobe.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final ProductRepository productRepository;
    private final TagRepository tagRepository;

    public AdminServiceImpl(ProductRepository productRepository,
                            TagRepository tagRepository) {
        this.productRepository = productRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public void addProduct(AddProductDTO addProductDTO) {
        Tag tag = getTag(addProductDTO);

        Product newProduct = getNewProduct(addProductDTO);
        newProduct.setTag(tag);

        productRepository.save(newProduct);
    }

    private Product getNewProduct(AddProductDTO addProductDTO) {
        Product createProduct = new Product();

        createProduct.setProductNumber(addProductDTO.getProductNumber());
        createProduct.setName(addProductDTO.getName());
        createProduct.setSize(addProductDTO.getSize());
        createProduct.setDescription(addProductDTO.getDescription());
        createProduct.setQuantity(addProductDTO.getQuantity());
        createProduct.setPrice(addProductDTO.getPrice());
        createProduct.setMinPrice(addProductDTO.getMinPrice());

        createProduct.setFirstImgUrl(addProductDTO.getImageUrl1());
        createProduct.setSecondImgUrl(addProductDTO.getImageUrl2());
        createProduct.setThirdImgUrl(addProductDTO.getImageUrl3());

        return createProduct;
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

}
