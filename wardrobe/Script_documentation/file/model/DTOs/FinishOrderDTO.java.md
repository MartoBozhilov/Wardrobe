### Imports

- **`lombok.AllArgsConstructor`**
- **`lombok.Getter`**
- **`lombok.NoArgsConstructor`**
- **`lombok.Setter`**

### Class Description

**`FinishOrderDTO`**

A Data Transfer Object (DTO) used specifically for completing an order process. It captures the essential elements required to finalize an order, such as the order identifier and the delivery address.

### Annotations

- **`@Getter`**: Automatically generates getter methods for all fields, allowing data to be retrieved.
- **`@Setter`**: Automatically generates setter methods for all fields, allowing data to be set or updated.
- **`@NoArgsConstructor`**: Generates a no-argument constructor, facilitating instantiation of the class without initial data.
- **`@AllArgsConstructor`**: Creates a constructor that includes all fields, enabling complete initialization at the time of object creation.

### Fields

1. **`id`** (Type: `Long`)
    
    - **Description**: The unique identifier of the order. This ID is crucial for tracking and referencing the order within the system during the finalization process.
2. **`address`** (Type: `String`)
    
    - **Description**: The delivery address where the order should be sent. This field is essential for ensuring that the order reaches the correct destination.

### Usage

The `FinishOrderDTO` is typically used in the final stage of the order process in e-commerce platforms. It enables the efficient handling of necessary actions to complete an order, such as verifying order details, processing payments, and arranging for delivery. The DTO serves as a final checkpoint that confirms where the order should be delivered and under which order ID, thus ensuring that the order management process is both streamlined and accurate.

---

This DTO structure supports maintaining a clean and organized architecture within the application, ensuring that data related to the order completion process is managed effectively. This helps reduce errors and improves the overall efficiency of the order fulfillment process.