package bg.conquerors.wardrobe.model.entity;

import bg.conquerors.wardrobe.model.enums.SizeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "size", nullable = false)
    @Enumerated(EnumType.STRING)
    private SizeEnum size;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    @OneToMany(mappedBy = "product")
    private List<ImgUrl> imageUrlList;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "min_price", nullable = false)
    private BigDecimal minPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "discount_id", referencedColumnName = "id")
    private Discount discount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    private Tag tag;

}
