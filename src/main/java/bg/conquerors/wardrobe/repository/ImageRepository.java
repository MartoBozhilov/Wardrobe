package bg.conquerors.wardrobe.repository;

import bg.conquerors.wardrobe.model.entity.ImgUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImgUrl, Long> {

}
