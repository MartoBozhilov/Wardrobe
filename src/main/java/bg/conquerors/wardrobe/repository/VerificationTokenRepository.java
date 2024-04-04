package bg.conquerors.wardrobe.repository;

import bg.conquerors.wardrobe.service.registration.tokens.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

}
