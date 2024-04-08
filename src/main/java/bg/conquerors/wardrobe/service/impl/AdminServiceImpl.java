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

    @Override
    public void editProduct(Long id,AddProductDTO addProductDTO) {
        Tag tag = getTag(addProductDTO);

        Product product = setProduct(productRepository.findAllById(id),addProductDTO);
        product.setTag(tag);

        productRepository.save(product);
    }

    @Override
    public AddProductDTO getProductById(Long id) {


        Product product = productRepository.findAllById(id);

        if (product == null)
            return null;

        return createProductDTO(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.delete(
                productRepository.getReferenceById(id));
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

    private AddProductDTO createProductDTO(Product product){

        AddProductDTO addProductDTO = new AddProductDTO();

        addProductDTO.setProductNumber(product.getProductNumber());
        addProductDTO.setName(product.getName());
        addProductDTO.setSize(product.getSize());
        addProductDTO.setDescription(product.getDescription());
        addProductDTO.setQuantity(product.getQuantity());
        addProductDTO.setPrice(product.getPrice());
        addProductDTO.setMinPrice(product.getMinPrice());

        addProductDTO.setImageUrl1(product.getFirstImgUrl());
        addProductDTO.setImageUrl2(product.getSecondImgUrl());
        addProductDTO.setImageUrl3(product.getThirdImgUrl());

        return addProductDTO;
    }

    private Product setProduct(Product product,AddProductDTO addProductDTO) {

        product.setProductNumber(addProductDTO.getProductNumber());
        product.setName(addProductDTO.getName());
        product.setSize(addProductDTO.getSize());
        product.setDescription(addProductDTO.getDescription());
        product.setQuantity(addProductDTO.getQuantity());
        product.setPrice(addProductDTO.getPrice());
        product.setMinPrice(addProductDTO.getMinPrice());

        product.setFirstImgUrl(addProductDTO.getImageUrl1());
        product.setSecondImgUrl(addProductDTO.getImageUrl2());
        product.setThirdImgUrl(addProductDTO.getImageUrl3());

        return product;
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
