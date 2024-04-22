### Imports

- **`bg.conquerors.wardrobe.model.entity.OrderDetail`**
- **`bg.conquerors.wardrobe.model.enums.OrderStatusEnum`**
- **`jakarta.validation.constraints.Min`**
- **`jakarta.validation.constraints.NotBlank`**
- **`lombok.AllArgsConstructor`**
- **`lombok.Getter`**
- **`lombok.NoArgsConstructor`**
- **`lombok.Setter`**
- **`org.springframework.format.annotation.DateTimeFormat`**
- **`java.math.BigDecimal`**
- **`java.util.Date`**
- **`java.util.List`**

### Class Description

**`AddOrderDTO`**

A data transfer object (DTO) that encapsulates the details needed to create a new order within the system. It ensures that all necessary information is provided and correctly formatted before the order is processed.

### Annotations

- **`@Getter`**: Automatically generates getter methods for all fields.
- **`@Setter`**: Automatically generates setter methods for all fields.
- **`@NoArgsConstructor`**: Generates a no-argument constructor for the class.
- **`@AllArgsConstructor`**: Generates a constructor that takes one parameter for each field in the class.

### Fields

1. **`orderDate`** (Type: `Date`)
    
    - **Description**: The date on which the order is placed.
    - **Validation**:
        - **`@NotBlank`**: Ensures that the order date is not null; however, this should likely be `@NotNull` as `@NotBlank` is inappropriate for `Date` types.
        - **`@DateTimeFormat(pattern = "yyyy-MM-dd")`**: Specifies that the date should be formatted as year-month-day.
2. **`status`** (Type: `OrderStatusEnum`)
    
    - **Description**: The status of the order, e.g., pending, completed, cancelled.
    - **Validation**:
        - **`@NotBlank`**: Ensures that the status is specified. Note that this validation is incorrect since `OrderStatusEnum` is an enum and not a string or nullable field.
3. **`totalPrice`** (Type: `BigDecimal`)
    
    - **Description**: The total price of the order.
    - **Validation**:
        - **`@NotBlank`**: The annotation is misused here as `BigDecimal` is not a string or character sequence.
        - **`@Min(value = 0)`**: Ensures that the total price cannot be a negative value.
4. **`address`** (Type: `String`)
    
    - **Description**: The delivery address for the order.
    - **Validation**:
        - **`@NotBlank`**: Ensures the address is provided and is not just whitespace.
5. **`userId`** (Type: `Long`)
    
    - **Description**: The ID of the user placing the order.
    - **Validation**:
        - **`@NotBlank`**: Incorrectly applied, should be `@NotNull` or a similar non-null validation since `Long` is a numeric type.
6. **`orderInventories`** (Type: `List<OrderDetail>`)
    
    - **Description**: A list of order details specifying the items included in the order.
    - **Validation**:
        - **`@NotBlank`**: Misapplied as it should not be used for collections; should use `@NotEmpty` or `@NotNull`.

### Usage

`AddOrderDTO` is used to transfer order data from clients to the server efficiently while ensuring all necessary information is validated and formatted correctly. This class is typically involved in the handling of order processing in web applications.

---

**Note**: There are several misapplications of the `@NotBlank` annotation in this class. For non-string fields like `Date`, `BigDecimal`, `Long`, and collections, alternatives such as `@NotNull`, `@NotEmpty`, or other specific annotations should be used instead to validate these fields properly.