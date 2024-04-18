package bg.conquerors.wardrobe.model.dto;

import bg.conquerors.wardrobe.model.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ViewProductsDTO {

    private Long id;

    private String productNumber;

    private String name;

    private String imgUrl;

    private BigDecimal price;

    private Tag tag;

}
