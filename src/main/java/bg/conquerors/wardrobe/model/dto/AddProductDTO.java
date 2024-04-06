package bg.conquerors.wardrobe.model.dto;

import bg.conquerors.wardrobe.model.enums.CategoryEnum;
import bg.conquerors.wardrobe.model.enums.GenderEnum;
import bg.conquerors.wardrobe.model.enums.SizeEnum;
import bg.conquerors.wardrobe.model.enums.StyleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddProductDTO {

    private String productNumber;

    private String name;

    private SizeEnum size;

    private String description;

    private String imageUrl1;

    private String imageUrl2;

    private String imageUrl3;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal minPrice;

    private GenderEnum gender;

    private CategoryEnum category;

    private StyleEnum style;

}
