package bg.conquerors.wardrobe.repository;

import bg.conquerors.wardrobe.model.entity.Discount;
import bg.conquerors.wardrobe.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    List<Discount> findAll();
    Discount findAllById(Long id);
}
