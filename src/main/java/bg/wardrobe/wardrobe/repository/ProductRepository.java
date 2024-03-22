package bg.wardrobe.wardrobe.repository;

import bg.wardrobe.wardrobe.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
