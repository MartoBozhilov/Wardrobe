package bg.conquerors.wardrobe.model.dto;

import bg.conquerors.wardrobe.model.enums.SizeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ViewProductDTO {

    private String productNumber;

    private String name;

    private String description;

    private String firstImgUrl;

    private String secondImgUrl;

    private String thirdImgUrl;

    private BigDecimal price;

    private SizeEnum size;

}
