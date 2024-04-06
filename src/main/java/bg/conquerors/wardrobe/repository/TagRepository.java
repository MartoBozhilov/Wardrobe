package bg.conquerors.wardrobe.repository;

import bg.conquerors.wardrobe.model.entity.Tag;
import bg.conquerors.wardrobe.model.enums.CategoryEnum;
import bg.conquerors.wardrobe.model.enums.GenderEnum;
import bg.conquerors.wardrobe.model.enums.StyleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag findByGenderAndCategoryAndStyle(GenderEnum gender, CategoryEnum category, StyleEnum style);

}
