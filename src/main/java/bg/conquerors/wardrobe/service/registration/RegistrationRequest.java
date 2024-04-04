package bg.conquerors.wardrobe.service.registration;

import bg.conquerors.wardrobe.model.entity.Order;
import bg.conquerors.wardrobe.model.entity.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public record RegistrationRequest(
         String email,
         String username,
         String password,
         String firstName,
         String lastName,
         String phoneNumber,
         Integer points
) {

}
