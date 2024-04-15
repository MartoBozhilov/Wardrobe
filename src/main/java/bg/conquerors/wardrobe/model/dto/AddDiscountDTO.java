package bg.conquerors.wardrobe.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddDiscountDTO {

    @NotBlank(message = "You need to specify a start date!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @NotBlank(message = "You need to specify an end date greater than a start date!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @NotBlank(message = "You need to specify an end date greater than a start date!")
    @Min(value = 0 , message = "The discount percentage can not be less than 0%")
    @Max(value = 100 , message = "The discount percentage can not be above 100%")
    private int discountPercentage;
}
