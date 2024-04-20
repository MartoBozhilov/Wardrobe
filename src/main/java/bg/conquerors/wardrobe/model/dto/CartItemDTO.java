package bg.conquerors.wardrobe.model.dto;

import bg.conquerors.wardrobe.model.enums.SizeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {

    private Long id;

    private Integer quantity;

    private String productName;

    private String productImageUrl;

    private BigDecimal price;

    private SizeEnum size;

}
