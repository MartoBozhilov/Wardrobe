### Imports

- **`jakarta.persistence.CascadeType`**
- **`jakarta.persistence.Column`**
- **`jakarta.persistence.Entity`**
- **`jakarta.persistence.OneToMany`**
- **`jakarta.persistence.Table`**
- **`lombok.Getter`**
- **`lombok.Setter`**
- **`java.util.Date`**
- **`java.util.List`**

### Class Description

**`Discount`**

An entity that defines a discount applied to products. It includes relationships to the products that the discount affects, as well as attributes specifying the validity period and percentage of the discount.

### Annotations

- **`@Getter`**: Automatically generates getter methods for all fields, allowing easy access to the values of properties.
- **`@Setter`**: Automatically generates setter methods for all fields, enabling the modification of property values.
- **`@Table`**: Specifies that this class is mapped to a table in the database, with the table name defaulting to the class name unless otherwise specified.
- **`@Entity`**: Marks this class as a JPA entity, meaning it is mapped to a database table.

### Fields

1. **`products`** (Type: `List<Product>`)
    
    - **Description**: A list of products that the discount is applied to.
    - **Annotations**:
        - **`@OneToMany(mappedBy = "discount", cascade = CascadeType.ALL)`**: Specifies a one-to-many relationship with the `Product` entity. The `cascade = CascadeType.ALL` indicates that persistence actions (like save, delete) on the `Discount` entity should cascade to the related products.
2. **`startDate`** (Type: `Date`)
    
    - **Description**: The start date of the discount period.
    - **Annotations**:
        - **`@Column(name = "start_date")`**: Maps this field to a column in the database named "start_date".
3. **`endDate`** (Type: `Date`)
    
    - **Description**: The end date of the discount period.
    - **Annotations**:
        - **`@Column(name = "end_date")`**: Maps this field to a column in the database named "end_date".
4. **`discountPercentage`** (Type: `int`)
    
    - **Description**: The percentage rate of the discount applied.
    - **Annotations**:
        - **`@Column(name = "discount_percentage")`**: Maps this field to a column in the database named "discount_percentage".

### Usage

The `Discount` entity is used in applications where products may have discounts that vary over time or across different items. By linking directly with product entities, this setup allows the system to efficiently manage discount applications and ensure they are reflected across the relevant products within the sales platform.

---

This entity structure facilitates efficient data management for discount-related operations within an e-commerce or retail management system, ensuring that all relevant product discounts are appropriately tracked and applied.