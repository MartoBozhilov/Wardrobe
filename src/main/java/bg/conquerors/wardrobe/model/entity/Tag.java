package bg.conquerors.wardrobe.model.entity;

import bg.conquerors.wardrobe.model.enums.CategoryEnum;
import bg.conquerors.wardrobe.model.enums.GenderEnum;
import bg.conquerors.wardrobe.model.enums.StyleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tags")
@AllArgsConstructor
@NoArgsConstructor
public class Tag extends BaseEntity {

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @Column(name = "style", nullable = false)
    @Enumerated(EnumType.STRING)
    private StyleEnum style;

}
