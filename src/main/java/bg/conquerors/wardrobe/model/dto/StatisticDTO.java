package bg.conquerors.wardrobe.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDTO {

    private Date startDate;

    private Date endDate;

    private BigDecimal totalIncome;

    private BigDecimal orderedIncome;

    private BigDecimal shippedIncome;

    private BigDecimal deliveredIncome;

    private List<AddOrderDTO> orders;

}
