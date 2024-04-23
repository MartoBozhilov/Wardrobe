package bg.conquerors.wardrobe.model.dto;

import bg.conquerors.wardrobe.model.entity.OrderDetail;
import bg.conquerors.wardrobe.model.enums.OrderStatusEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ViewUserOrdersDTO {

    @NotBlank(message = "You need to specify an user id!")
    private Long orderId;

    @NotBlank(message = "You need to specify an order date!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    @NotBlank(message = "You need to specify an order status!")
    private OrderStatusEnum status;

    @NotBlank(message = "You need to specify a total price!")
    @Min(value = 0, message = "The total price can not be negative integer!")
    private BigDecimal totalPrice;

    @NotBlank(message = "You need to specify an order address!")
    private String address;

}
