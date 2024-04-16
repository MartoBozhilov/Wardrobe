package bg.conquerors.wardrobe.repository;

import bg.conquerors.wardrobe.model.entity.Product;
import bg.conquerors.wardrobe.model.enums.SizeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();

    Product findAllById(Long id);

    List<Product> findAllByProductNumber(String ProductNumber);

    Product findByProductNumberAndSize(String productNumber, SizeEnum size);

}
