### Imports

- **`bg.conquerors.wardrobe.model.enums.CategoryEnum`**
- **`bg.conquerors.wardrobe.model.enums.GenderEnum`**
- **`bg.conquerors.wardrobe.model.enums.StyleEnum`**
- **`jakarta.persistence.*`**
- **`lombok.AllArgsConstructor`**
- **`lombok.Getter`**
- **`lombok.NoArgsConstructor`**
- **`lombok.Setter`**

### Class Description

**`Tag`**

An entity class that encapsulates the tagging system used to categorize products by gender, category, and style. These tags facilitate the filtering and searching of products on an e-commerce platform, enhancing user experience by enabling more granular product discovery.

### Annotations

- **`@Getter`**: Automatically generates getter methods for all fields, allowing read access to property values.
- **`@Setter`**: Automatically generates setter methods for all fields, permitting modification of property values.
- **`@Entity`**: Marks this class as a JPA entity, meaning it is mapped to a database table.
- **`@Table(name = "tags")`**: Specifies the database table name associated with this entity. The table is named "tags".
- **`@AllArgsConstructor`**: Generates a constructor that includes an argument for each field, enabling full object initialization.
- **`@NoArgsConstructor`**: Provides a no-argument constructor, enhancing compatibility with JPA which requires a no-arg constructor for entities.

### Fields

1. **`gender`** (Type: `GenderEnum`)
    
    - **Description**: Specifies the gender category associated with the tag, such as Male, Female, or Unisex.
    - **Annotations**:
        - **`@Column(name = "gender", nullable = false)`**
        - **`@Enumerated(EnumType.STRING)`**: Enum values are stored as strings in the database.
2. **`category`** (Type: `CategoryEnum`)
    
    - **Description**: Defines the product category, such as Clothing, Accessories, or Footwear.
    - **Annotations**:
        - **`@Column(name = "category", nullable = false)`**
        - **`@Enumerated(EnumType.STRING)`**: Ensures that the category is stored as a string in the database.
3. **`style`** (Type: `StyleEnum`)
    
    - **Description**: Represents the style or theme of the product, such as Casual, Formal, or Sport.
    - **Annotations**:
        - **`@Column(name = "style", nullable = false)`**
        - **`@Enumerated(EnumType.STRING)`**: Stores the style as a string value in the database.

### Usage

The `Tag` entity is used in product management systems to assign descriptive attributes to items, allowing for efficient organization, searching, and filtering of products based on these characteristics. This tagging system is integral to enhancing the shopping experience by helping customers find products that meet specific criteria related to gender, category, or style.

---

This entity supports dynamic product categorization within e-commerce platforms, providing a flexible and effective way to manage product assortments and improve the accessibility of varied inventory through targeted filters.