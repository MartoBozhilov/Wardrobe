package bg.conquerors.wardrobe.model.dto;

import bg.conquerors.wardrobe.model.enums.CategoryEnum;
import bg.conquerors.wardrobe.model.enums.GenderEnum;
import bg.conquerors.wardrobe.model.enums.StyleEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddUserDTO {

    @Valid

    @NotNull(message = "You need to specify a product number!")
    @NotBlank(message = "You need to specify a product number!")
    private String email;

    @NotNull(message = "You need to specify an product name!")
    @NotBlank(message = "You need to specify an product name!")
    private String username;

    @NotNull(message = "You need to specify a quantity for each size!")
    @NotBlank(message = "You need to specify a quantity for each size!")
    public String password;

    @NotNull(message = "You need to specify a description!")
    @NotBlank(message = "You need to specify a description!")
    private String firstName;

    @NotNull(message = "You need to specify first image url!")
    @NotBlank(message = "You need to specify first image url!")
    private String lastName;

    @NotNull(message = "You need to specify second image url!")
    @NotBlank(message = "You need to specify second image url!")
    private String phoneNumber;

    @NotNull(message = "You need to specify third image url!")
    @NotBlank(message = "You need to specify third image url!")
    private Integer points;

    @NotNull(message = "You need to specify a price!")
    @NotBlank(message = "You need to specify a price!")
    private boolean isAdmin;

}
