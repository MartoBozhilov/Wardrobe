package bg.conquerors.wardrobe.model.dto;

import bg.conquerors.wardrobe.model.enums.CategoryEnum;
import bg.conquerors.wardrobe.model.enums.GenderEnum;
import bg.conquerors.wardrobe.model.enums.SizeEnum;
import bg.conquerors.wardrobe.model.enums.StyleEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Dictionary;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddProductDTO {

    @Valid

    @NotNull(message = "You need to specify a product number!")
    @NotBlank(message = "You need to specify a product number!")
    private String productNumber;

    @NotNull(message = "You need to specify an product name!")
    @NotBlank(message = "You need to specify an product name!")
    private String name;

    @NotNull(message = "You need to specify a quantity for each size!")
    @NotBlank(message = "You need to specify a quantity for each size!")
    public Map<String, Integer> quantities;

    @NotNull(message = "You need to specify a description!")
    @NotBlank(message = "You need to specify a description!")
    private String description;

    @NotNull(message = "You need to specify first image url!")
    @NotBlank(message = "You need to specify first image url!")
    private String firstImgUrl;

    @NotNull(message = "You need to specify second image url!")
    @NotBlank(message = "You need to specify second image url!")
    private String secondImgUrl;

    @NotNull(message = "You need to specify third image url!")
    @NotBlank(message = "You need to specify third image url!")
    private String thirdImgUrl;

    @NotNull(message = "You need to specify a price!")
    @NotBlank(message = "You need to specify a price!")
    @Min(value = 0,message = "The price can not be negative number!")
    private BigDecimal price;

    // @Max(value = price, message = "The min price can not be more then price!")
    @NotNull(message = "You need to specify a min Price!")
    @NotBlank(message = "You need to specify a min Price!")
    @Min(value = 0, message = "The min price can not be negative number!")
    private BigDecimal minPrice;

    @NotNull(message = "You need to specify an gender!")
    @NotBlank(message = "You need to specify an gender!")
    private GenderEnum gender;

    @NotNull(message = "You need to specify an category!")
    @NotBlank(message = "You need to specify an category!")
    private CategoryEnum category;

    @NotNull(message = "You need to specify a style!")
    @NotBlank(message = "You need to specify a style!")
    private StyleEnum style;

}
