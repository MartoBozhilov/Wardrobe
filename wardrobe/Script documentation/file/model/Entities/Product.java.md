### Imports

- **`bg.conquerors.wardrobe.model.enums.SizeEnum`**
- **`jakarta.persistence.*`**
- **`jakarta.validation.constraints.NotEmpty`**
- **`jakarta.validation.constraints.NotNull`**
- **`lombok.Getter`**
- **`lombok.Setter`**
- **`java.math.BigDecimal`**

### Class Description

**`Product`**

An entity class that represents a product in the system's catalog. It includes comprehensive details such as product number, name, size, description, images, inventory quantity, pricing, and associations with discounts and tags for categorization and promotional purposes.

### Annotations

- **`@Getter`**: Automatically generates getter methods for all fields, facilitating data access.
- **`@Setter`**: Automatically generates setter methods for all fields, enabling data modification.
- **`@Entity`**: Marks this class as a JPA entity, indicating it is mapped to a database table.
- **`@Table(name = "products")`**: Specifies the database table name associated with this entity. The table is named "products".

### Fields

1. **`productNumber`** (Type: `String`)
    
    - **Description**: A unique identifier for the product.
    - **Annotations**:
        - **`@NotNull`**
        - **`@Column(name = "product_number")`**
2. **`name`** (Type: `String`)
    
    - **Description**: The name of the product.
    - **Annotations**:
        - **`@NotNull`**
        - **`@Column(name = "name")`**
3. **`size`** (Type: `SizeEnum`)
    
    - **Description**: The size of the product, using an enumerated type.
    - **Annotations**:
        - **`@Column(name = "size", nullable = false)`**
        - **`@Enumerated(EnumType.STRING)`**
4. **`description`** (Type: `String`)
    
    - **Description**: A textual description of the product.
    - **Annotations**:
        - **`@Column(columnDefinition = "TEXT")`**
5. **`firstImgUrl`, `secondImgUrl`, `thirdImgUrl`** (Type: `String`)
    
    - **Description**: URLs to images of the product, providing visual details.
    - **Annotations**:
        - **`@NotEmpty`**
        - **`@NotNull`**
        - **`@Column`** for each respective image URL field.
6. **`quantity`** (Type: `Integer`)
    
    - **Description**: The available quantity of the product.
    - **Annotations**:
        - **`@Column(nullable = false)`**
7. **`price`** (Type: `BigDecimal`)
    
    - **Description**: The retail price of the product.
    - **Annotations**:
        - **`@Column(nullable = false)`**
8. **`minPrice`** (Type: `BigDecimal`)
    
    - **Description**: The minimum price allowed for the product, possibly used in promotions or discounts.
    - **Annotations**:
        - **`@Column(name = "min_price", nullable = false)`**
9. **`discount`** (Type: `Discount`)
    
    - **Description**: A potential discount applied to the product.
    - **Annotations**:
        - **`@ManyToOne(fetch = FetchType.EAGER)`**
        - **`@JoinColumn(name = "discount_id", referencedColumnName = "id")`**
10. **`tag`** (Type: `Tag`)
    
    - **Description**: An optional tag associated with the product for categorization or marketing.
    - **Annotations**:
        - **`@ManyToOne(fetch = FetchType.EAGER)`**
        - **`@JoinColumn(name = "tag_id", referencedColumnName = "id")`**

### Usage

The `Product` entity is used to manage the catalog of products within an e-commerce or retail system. It supports operations such as product listing, inventory management, pricing adjustments, and promotional activities. This entity ensures that detailed product information is consistently managed and available for e-commerce transactions and analysis.

---

This entity design allows for efficient tracking and management of product data, crucial for operational effectiveness in retail and e-commerce settings.