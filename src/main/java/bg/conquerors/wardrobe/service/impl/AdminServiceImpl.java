package bg.conquerors.wardrobe.service.impl;

import bg.conquerors.wardrobe.model.dto.AddDiscountDTO;
import bg.conquerors.wardrobe.model.dto.AddProductDTO;
import bg.conquerors.wardrobe.model.entity.Discount;
import bg.conquerors.wardrobe.model.entity.Product;
import bg.conquerors.wardrobe.model.entity.Tag;
import bg.conquerors.wardrobe.repository.DiscountRepository;
import bg.conquerors.wardrobe.repository.ProductRepository;
import bg.conquerors.wardrobe.repository.TagRepository;
import bg.conquerors.wardrobe.service.AdminService;
import org.springframework.stereotype.Service;

import java.time.temporal.TemporalAdjuster;

@Service
public class AdminServiceImpl implements AdminService {

    private final ProductRepository productRepository;
    private final TagRepository tagRepository;

    private final DiscountRepository discountRepository;

    public AdminServiceImpl(ProductRepository productRepository,
                            TagRepository tagRepository,
                            DiscountRepository discountRepository) {
        this.productRepository = productRepository;
        this.tagRepository = tagRepository;
        this.discountRepository = discountRepository;
    }


    /*
    *
    *
    *                           Product
    *
    *
    * */

    @Override
    public void addProduct(AddProductDTO addProductDTO) {
        Tag tag = getTag(addProductDTO);

        Product newProduct = getNewProduct(addProductDTO);
        newProduct.setTag(tag);

        productRepository.save(newProduct);
    }

    @Override
    public void editProduct(Long id,AddProductDTO addProductDTO) {
        Product product = setProduct(productRepository.findAllById(id),addProductDTO);

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
        var product = productRepository.findAllById(id);
        Tag tag = product.getTag();
        productRepository.delete(product);
        tagRepository.delete(tag);
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

        addProductDTO.setCategory(product.getTag().getCategory());
        addProductDTO.setGender(product.getTag().getGender());
        addProductDTO.setStyle(product.getTag().getStyle());

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

        updateTag(addProductDTO,product.getTag());

        return product;
    }

    private Tag getTag(AddProductDTO addProductDTO) {

        /*Tag searchedTag = tagRepository
                .findByGenderAndCategoryAndStyle(
                        addProductDTO.getGender(),
                        addProductDTO.getCategory(),
                        addProductDTO.getStyle()
                );

        if (searchedTag != null) {
            return searchedTag;
        }*/

        Tag newTag = new Tag(
                addProductDTO.getGender(),
                addProductDTO.getCategory(),
                addProductDTO.getStyle()
        );

        tagRepository.save(newTag);

        return newTag;
    }

    private void updateTag(AddProductDTO addProductDTO, Tag tag){


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
           if(isChange) tagRepository.save(tag);
    }

    /*
     *
     *
     *                           Discount
     *
     *
     * */

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
        Discount discount = setDiscount(discountRepository.findAllById(id),addDiscountDTO);

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

}
