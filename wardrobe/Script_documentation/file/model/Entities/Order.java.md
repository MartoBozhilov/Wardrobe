### Imports

- **`bg.conquerors.wardrobe.model.enums.OrderStatusEnum`**
- **`jakarta.persistence.*`**
- **`lombok.Getter`**
- **`lombok.NoArgsConstructor`**
- **`lombok.Setter`**
- **`java.math.BigDecimal`**
- **`java.util.Date`**
- **`java.util.List`**

### Class Description

**`Order`**

An entity class that represents a customer's order. It encapsulates all the necessary details about an order, including order date, status, total price, delivery address, the customer associated with the order, and the details of items included in the order.

### Annotations

- **`@Getter`**: Automatically generates getter methods for all fields, providing read access to property values.
- **`@Setter`**: Automatically generates setter methods for all fields, allowing property values to be modified.
- **`@Entity`**: Designates this class as a JPA entity, meaning it's mapped to a database table.
- **`@Table(name = "orders")`**: Specifies the database table with which this entity is associated. The table is named "orders".
- **`@NoArgsConstructor`**: Provides a no-argument constructor for the class, required by JPA for creating instances of the entity.

### Fields

1. **`orderDate`** (Type: `Date`)
    
    - **Description**: The date when the order was placed.
    - **Annotations**:
        - **`@Column(name = "order_date")`**: Maps this field to the "order_date" column in the "orders" table.
2. **`status`** (Type: `OrderStatusEnum`)
    
    - **Description**: The current status of the order (e.g., pending, shipped, delivered).
    - **Annotations**:
        - **`@Column(name = "status")`**
        - **`@Enumerated(EnumType.STRING)`**: Specifies that the enum value should be stored as a STRING in the database.
3. **`totalPrice`** (Type: `BigDecimal`)
    
    - **Description**: The total price of all items in the order.
    - **Annotations**:
        - **`@Column(name = "total_price")`**: Maps this field to the "total_price" column in the "orders" table.
4. **`address`** (Type: `String`)
    
    - **Description**: The delivery address for the order.
    - **Annotations**:
        - **`@Column(name = "address")`**: Maps this field to the "address" column in the "orders" table.
5. **`user`** (Type: `User`)
    
    - **Description**: The user who placed the order.
    - **Annotations**:
        - **`@ManyToOne(fetch = FetchType.EAGER)`**: Specifies a many-to-one relationship with the `User` entity. Fetch type is EAGER, meaning it loads the user details immediately with the order.
        - **`@JoinColumn(name = "user_id", referencedColumnName = "id")`**: Defines the foreign key column for this relationship.
6. **`orderInventories`** (Type: `List<OrderDetail>`)
    
    - **Description**: The list of item details included in the order.
    - **Annotations**:
        - **`@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)`**: Specifies a one-to-many relationship with the `OrderDetail` entities. All persistence actions on `Order` will cascade to `OrderDetail`.

### Constructor

- **`Order(long id)`**: A constructor to initialize the order with an ID. This is useful for creating an instance with a specific identifier, primarily for testing or when the ID is already known.

### Usage

The `Order` entity is used to manage all aspects of an order's lifecycle in an e-commerce system, from creation to delivery. It is instrumental for tracking order progress, managing financial records, and linking customer data with order specifics.

---

This class ensures a comprehensive management of orders within a database, supporting operations that require data on order status, customer details, and transaction amounts, among others.