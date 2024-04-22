package bg.conquerors.wardrobe.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SetDiscountToProductDTO {

    private Long discountId;

    private List<Long> productsProductNumbers;
}
