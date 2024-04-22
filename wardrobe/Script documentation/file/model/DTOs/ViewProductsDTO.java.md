### Imports

- **`bg.conquerors.wardrobe.model.entity.Tag`**
- **`lombok.AllArgsConstructor`**
- **`lombok.Getter`**
- **`lombok.NoArgsConstructor`**
- **`lombok.Setter`**
- **`java.math.BigDecimal`**

### Class Description

**`ViewProductsDTO`**

A Data Transfer Object (DTO) designed to encapsulate essential information about products for easy viewing in lists or galleries. It includes product identifiers, names, images, pricing information, and associated tags, providing a concise overview suitable for customer browsing.

### Annotations

- **`@Getter`**: Automatically generates getter methods for all fields, facilitating access to property values.
- **`@Setter`**: Automatically generates setter methods for all fields, allowing properties to be updated as needed.
- **`@NoArgsConstructor`**: Provides a no-argument constructor, which is particularly useful for frameworks and libraries that require empty constructors for instantiation purposes.
- **`@AllArgsConstructor`**: Generates a constructor that takes an argument for each field in the class, enabling comprehensive initialization at the time of object creation.

### Fields

1. **`id`** (Type: `Long`)
    
    - **Description**: The unique identifier of the product, often used for referencing and managing products within the system.
2. **`productNumber`** (Type: `String`)
    
    - **Description**: A unique product number or SKU, which aids in the identification and cataloging of products.
3. **`name`** (Type: `String`)
    
    - **Description**: The name of the product, used prominently in product listings and during searches.
4. **`imgUrl`** (Type: `String`)
    
    - **Description**: The URL for the product's main image. This image is critical for online sales where visual presentation influences purchasing decisions.
5. **`price`** (Type: `BigDecimal`)
    
    - **Description**: The retail price of the product. Using `BigDecimal` ensures precise handling of financial data, crucial for pricing accuracy.
6. **`tag`** (Type: `Tag`)
    
    - **Description**: An associated tag that provides additional metadata about the product, such as category, promotions, or special labels. This can aid in the filtering and sorting of products in various views.

### Usage

The `ViewProductsDTO` is typically used in e-commerce platforms to display products in a concise format that includes the most relevant information for customer browsing. It can be used in various interfaces, such as product lists, search results, or promotional galleries, facilitating quick and effective product evaluation by customers.

---

This DTO structure is essential for maintaining efficient data transfer and presentation in retail and e-commerce applications, ensuring that key product information is easily accessible and effectively communicated to users.