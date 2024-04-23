### Imports

- **`bg.conquerors.wardrobe.model.enums.SizeEnum`**
- **`lombok.AllArgsConstructor`**
- **`lombok.Getter`**
- **`lombok.NoArgsConstructor`**
- **`lombok.Setter`**
- **`java.math.BigDecimal`**

### Class Description

**`ViewProductDTO`**

A Data Transfer Object (DTO) used for presenting detailed information about a product. It encapsulates all relevant product details required by clients to make purchasing decisions or for informational displays.

### Annotations

- **`@Getter`**: Automatically generates getter methods for all fields, enabling easy access to the values of the properties.
- **`@Setter`**: Automatically generates setter methods for all fields, allowing for modifications to the properties if necessary.
- **`@NoArgsConstructor`**: Generates a constructor without any arguments, facilitating instantiation without predefined values.
- **`@AllArgsConstructor`**: Generates a constructor that includes all class fields, allowing for full initialization of an object with all properties set.

### Fields

1. **`productNumber`** (Type: `String`)
    
    - **Description**: A unique identifier or SKU for the product, used in inventory tracking and management.
2. **`name`** (Type: `String`)
    
    - **Description**: The product's name, which is displayed to users and used in product listings.
3. **`description`** (Type: `String`)
    
    - **Description**: A detailed description of the product, providing potential buyers with information about features, benefits, and uses.
4. **`firstImgUrl`**, **`secondImgUrl`**, **`thirdImgUrl`** (Type: `String`)
    
    - **Description**: URLs to images of the product. These images are essential for online retail, helping customers visualize the product. Each URL points to a different view or aspect of the product to provide a comprehensive visual description.
5. **`price`** (Type: `BigDecimal`)
    
    - **Description**: The selling price of the product. `BigDecimal` is used for precision in financial calculations, ensuring accurate pricing and billing.
6. **`size`** (Type: `SizeEnum`)
    
    - **Description**: The size of the product, based on a predefined set of size enumerations (e.g., Small, Medium, Large). This field is crucial for apparel and other size-varied items, allowing customers to select a suitable size.

### Usage

The `ViewProductDTO` is primarily used in the presentation layer of applications, particularly in systems that require displaying detailed product information to users. It facilitates data transfer between backend services and frontend interfaces, ensuring that all necessary product details are available for effective display and decision-making by customers.

---

This DTO effectively supports the operational needs of e-commerce platforms by providing a standardized way to handle and present product data, which is crucial for customer engagement and sales processes.