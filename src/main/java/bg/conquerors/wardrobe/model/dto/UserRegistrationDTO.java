package bg.conquerors.wardrobe.model.dto;

import bg.conquerors.wardrobe.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDTO {

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

}
