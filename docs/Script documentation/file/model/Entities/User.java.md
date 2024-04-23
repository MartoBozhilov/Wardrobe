### Imports

- **`jakarta.persistence.*`**
- **`jakarta.validation.constraints.Email`**
- **`jakarta.validation.constraints.NotEmpty`**
- **`jakarta.validation.constraints.NotNull`**
- **`lombok.Getter`**
- **`lombok.Setter`**
- **`java.util.ArrayList`**
- **`java.util.List`**

### Class Description

**`User`**

An entity class that represents a user within the system. It includes comprehensive user identification and authentication details, as well as relationships to roles and orders, enabling a rich user management and access control system.

### Annotations

- **`@Getter`**: Automatically generates getter methods for all fields, facilitating data access.
- **`@Setter`**: Automatically generates setter methods for all fields, allowing modifications to the user data.
- **`@Entity`**: Marks this class as a JPA entity, indicating it is mapped to a database table.
- **`@Table(name = "users")`**: Specifies the database table name associated with this entity. The table is named "users".

### Fields

1. **`email`** (Type: `String`)
    
    - **Description**: The email address of the user, used as part of the login credentials and communication.
    - **Annotations**:
        - **`@Email`**: Validates that the field contains a valid email address.
        - **`@NotEmpty`**
        - **`@Column(name = "email", nullable = false, unique = true)`**: Ensures the email is stored uniquely in the database, preventing duplicate accounts.
2. **`username`** (Type: `String`)
    
    - **Description**: The username chosen by the user for login and identification purposes.
    - **Annotations**:
        - **`@Column(name = "username", nullable = false, unique = true)`**
3. **`password`** (Type: `String`)
    
    - **Description**: The user's password for authentication.
    - **Annotations**:
        - **`@NotEmpty`**
        - **`@Column(name = "password", nullable = false)`**
4. **`firstName`** (Type: `String`)
    
    - **Description**: The first name of the user.
    - **Annotations**:
        - **`@NotEmpty`**
        - **`@Column(name = "first_name", nullable = false)`**
5. **`lastName`** (Type: `String`)
    
    - **Description**: The last name of the user.
    - **Annotations**:
        - **`@NotEmpty`**
        - **`@Column(name = "last_name", nullable = false)`**
6. **`phoneNumber`** (Type: `String`)
    
    - **Description**: The phone number of the user, useful for contact and possibly for recovery purposes.
    - **Annotations**:
        - **`@NotNull`**
        - **`@Column(name = "phone_number")`**
7. **`points`** (Type: `Integer`)
    
    - **Description**: Points accumulated by the user through various activities or purchases, applicable in loyalty programs.
    - **Annotations**:
        - **`@NotNull`**
        - **`@Column`**
8. **`roles`** (Type: `List<UserRole>`)
    
    - **Description**: A list of roles assigned to the user, defining access rights and permissions.
    - **Annotations**:
        - **`@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)`**
        - **`@JoinTable`**: Specifies the table for managing the many-to-many relationship between users and roles.
9. **`orders`** (Type: `List<Order>`)
    
    - **Description**: Orders placed by the user.
    - **Annotations**:
        - **`@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)`**: Indicates that the user entity owns the relationship and any persistence actions will cascade to related orders.

### Additional Methods

- **`addRole(UserRole role)`**: Adds a role to the user's list of roles, aiding in dynamic role management.

### Usage

The `User` entity is crucial for managing user data in applications requiring user authentication, order tracking, role-based access control, and customer relationship management. This class ensures that user-related data is efficiently handled and integrated within the system's security and operational frameworks.

---

This comprehensive setup supports robust user management and plays a pivotal role in systems where user interaction and personalized services are key components.