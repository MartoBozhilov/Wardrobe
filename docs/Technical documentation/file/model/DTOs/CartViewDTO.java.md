### Imports

- **`lombok.AllArgsConstructor`**
- **`lombok.Getter`**
- **`lombok.NoArgsConstructor`**
- **`lombok.Setter`**
- **`java.util.List`**

### Class Description

**`CartViewDTO`**

A Data Transfer Object (DTO) that provides a comprehensive view of a shopping cart, including its unique identifier and the list of cart items it contains. This class is essential for operations that involve displaying or managing the contents of a shopping cart.

### Annotations

- **`@Getter`**: Automatically generates getter methods for all fields.
- **`@Setter`**: Automatically generates setter methods for all fields.
- **`@NoArgsConstructor`**: Creates a no-argument constructor for the class, enabling instantiation without needing to pass initial data.
- **`@AllArgsConstructor`**: Generates a constructor that takes a parameter for each field, allowing for complete initialization during object creation.

### Fields

1. **`id`** (Type: `Long`)
    
    - **Description**: The unique identifier for the cart. This ID is typically used to reference the cart in database operations and session tracking.
2. **`cartItems`** (Type: `List<CartItemDTO>`)
    
    - **Description**: A list of `CartItemDTO` objects, each representing an individual item in the cart. This list includes all the items a user has added to their cart, along with their respective quantities, prices, and other details.

### Usage

The `CartViewDTO` is used primarily in the presentation layer of web applications, where it facilitates the display and management of shopping cart data. By encapsulating cart details in this DTO, the system ensures that cart operations are handled efficiently, with all relevant cart information easily accessible for operations like viewing, updating, or checking out.

---

This DTO is pivotal in e-commerce platforms for managing user interactions with their shopping cart, allowing users to see what they have added to the cart and make adjustments as needed before proceeding to checkout. The use of this DTO helps maintain a clean separation between the business logic and the presentation layer, enhancing code manageability and scalability.