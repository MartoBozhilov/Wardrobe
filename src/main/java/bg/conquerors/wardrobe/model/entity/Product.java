package bg.conquerors.wardrobe.model.entity;

import bg.conquerors.wardrobe.model.enums.CategoryEnum;
import bg.conquerors.wardrobe.model.enums.SizeEnum;
import bg.conquerors.wardrobe.model.enums.StyleEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @NotNull
    @Column(name = "product_number")
    private String productNumber;

    @Column(name = "size", nullable = false)
    @Enumerated(EnumType.STRING)
    private SizeEnum size;

    @Column(nullable = false)
    private String color;

    @NotNull
    @OneToMany(mappedBy = "product")
    private List<ImgUrl> imageUrlList;

    @Column(nullable = false)
    private String brand;

    @NotNull
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String material;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StyleEnum Style;

    @Column(columnDefinition = "TEXT")
    private String description;

}