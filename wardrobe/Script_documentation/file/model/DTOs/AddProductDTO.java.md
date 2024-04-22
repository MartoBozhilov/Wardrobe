### Imports

- **`bg.conquerors.wardrobe.model.enums.CategoryEnum`**
- **`bg.conquerors.wardrobe.model.enums.GenderEnum`**
- **`bg.conquerors.wardrobe.model.enums.StyleEnum`**
- **`jakarta.validation.Valid`**
- **`jakarta.validation.constraints.Min`**
- **`jakarta.validation.constraints.NotBlank`**
- **`jakarta.validation.constraints.NotNull`**
- **`lombok.AllArgsConstructor`**
- **`lombok.Getter`**
- **`lombok.NoArgsConstructor`**
- **`lombok.Setter`**
- **`java.math.BigDecimal`**
- **`java.util.Map`**

### Class Description

**`AddProductDTO`**

A data transfer object that encapsulates all the necessary information to add a product to the inventory system. It ensures data validation using Jakarta Validation annotations for fields like product details and pricing.

### Annotations

- **`@Getter`**: Generates getter methods for all fields.
- **`@Setter`**: Generates setter methods for all fields.
- **`@NoArgsConstructor`**: Creates a no-argument constructor.
- **`@AllArgsConstructor`**: Creates a constructor that takes a parameter for each field.

### Fields

1. **`productNumber`** (Type: `String`)
    
    - **Description**: A unique identifier for the product.
    - **Validation**:
        - **`@NotNull`**, **`@NotBlank`**: Ensures the field is not null and not empty.
2. **`name`** (Type: `String`)
    
    - **Description**: The name of the product.
    - **Validation**:
        - **`@NotNull`**, **`@NotBlank`**: Ensures the field is not null and not empty.
3. **`quantities`** (Type: `Map<String, Integer>`)
    
    - **Description**: A mapping of sizes to their respective quantities.
    - **Validation**:
        - **`@NotNull`**, **`@NotBlank`** (misapplied, should be `@NotEmpty`): Ensures the map is not null and not empty.
4. **`description`** (Type: `String`)
    
    - **Description**: A detailed description of the product.
    - **Validation**:
        - **`@NotNull`**, **`@NotBlank`**: Ensures the field is not null and not empty.
5. **`firstImgUrl`**, **`secondImgUrl`**, **`thirdImgUrl`** (Type: `String`)
    
    - **Description**: URLs for product images.
    - **Validation** (applied to each URL):
        - **`@NotNull`**, **`@NotBlank`**: Ensures each field is not null and not empty.
6. **`price`**, **`minPrice`** (Type: `BigDecimal`)
    
    - **Description**: The standard price and minimum allowed price for the product.
    - **Validation**:
        - **`@NotNull`**, **`@NotBlank`** (misapplied for `BigDecimal`), **`@Min(value = 0)`**: Ensures the prices are not negative and not null.
7. **`gender`** (Type: `GenderEnum`)
    
    - **Description**: The gender classification for the product.
    - **Validation**:
        - **`@NotNull`**, **`@NotBlank`**: Ensures the field is not null and not empty.
8. **`category`** (Type: `CategoryEnum`)
    
    - **Description**: The category of the product.
    - **Validation**:
        - **`@NotNull`**, **`@NotBlank`**: Ensures the field is not null and not empty.
9. **`style`** (Type: `StyleEnum`)
    
    - **Description**: The style classification of the product.
    - **Validation**:
        - **`@NotNull`**, **`@NotBlank`**: Ensures the field is not null and not empty.

### Usage

`AddProductDTO` is utilized to facilitate the addition of new products through user interfaces or APIs, ensuring that all necessary product details are validated before the product is added to the database or inventory system.

---

**Note**: The misuse of `@NotBlank` annotations on non-String fields such as `BigDecimal` and `Map` should be corrected to more appropriate validations like `@NotNull` and `@NotEmpty`. This ensures that the validations are logically correct and effective.