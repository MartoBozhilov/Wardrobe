### Imports

- **`bg.conquerors.wardrobe.model.enums.SizeEnum`**
- **`lombok.AllArgsConstructor`**
- **`lombok.Getter`**
- **`lombok.NoArgsConstructor`**
- **`lombok.Setter`**
- **`java.math.BigDecimal`**

### Class Description

**`CartItemDTO`**

A Data Transfer Object (DTO) representing an item in a user's shopping cart. It includes details such as the item's unique identifier, quantity, name, image URL, price, and size.

### Annotations

- **`@Getter`**: Automatically generates getter methods for all fields.
- **`@Setter`**: Automatically generates setter methods for all fields.
- **`@NoArgsConstructor`**: Generates a no-argument constructor for the class, facilitating easier instantiation without parameters.
- **`@AllArgsConstructor`**: Generates a constructor that takes a parameter for each field in the class, allowing for full initialization upon creation.

### Fields

1. **`id`** (Type: `Long`)
    
    - **Description**: The unique identifier for the cart item. It is typically used to track items within the cart for operations like update or delete.
2. **`quantity`** (Type: `Integer`)
    
    - **Description**: The quantity of the product in the cart. This number represents how many units of the product the user wishes to purchase.
3. **`productName`** (Type: `String`)
    
    - **Description**: The name of the product. This is used for display purposes in the shopping cart.
4. **`productImageUrl`** (Type: `String`)
    
    - **Description**: The URL of the product's image. This helps users visually confirm the products they intend to purchase.
5. **`price`** (Type: `BigDecimal`)
    
    - **Description**: The price of a single unit of the product. `BigDecimal` is used to ensure precision in financial calculations.
6. **`size`** (Type: `SizeEnum`)
    
    - **Description**: The size of the product, based on predefined enumeration values (like Small, Medium, Large). This ensures that the product size is validated against a set of allowed options.

### Usage

The `CartItemDTO` is used primarily in e-commerce platforms to manage the products that users have added to their shopping carts. It facilitates operations such as viewing cart contents, updating quantities, and calculating total prices. The use of DTOs also helps in separating the presentation layer from the business logic, ensuring cleaner code and easier maintenance.

---

This DTO simplifies the handling and manipulation of cart item data within an application, ensuring that all necessary item information is encapsulated in a single object.