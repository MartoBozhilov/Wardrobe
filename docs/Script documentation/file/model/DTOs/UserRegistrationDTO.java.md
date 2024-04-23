### Imports

- **`bg.conquerors.wardrobe.model.entity.User`** (not directly used in the DTO but typically related to the context where this DTO would be used)
- **`lombok.AllArgsConstructor`**
- **`lombok.Getter`**
- **`lombok.NoArgsConstructor`**
- **`lombok.Setter`**
- **`java.util.Optional`** (mentioned in import but not directly used in this DTO)

### Class Description

**`UserRegistrationDTO`**

A Data Transfer Object (DTO) used for capturing all necessary information needed to register a user. This includes personal identification and contact information.

### Annotations

- **`@Getter`**: Automatically generates getter methods for all fields, allowing other parts of the application to retrieve the values of these fields.
- **`@Setter`**: Automatically generates setter methods for all fields, enabling the values to be set or updated from other parts of the application.
- **`@NoArgsConstructor`**: Provides a no-argument constructor, which can be useful for frameworks that require empty constructors for instantiation.
- **`@AllArgsConstructor`**: Provides a constructor that includes all fields, facilitating easy instantiation with complete data.

### Fields

1. **`username`** (Type: `String`)
    
    - **Description**: The chosen username for the new user account, used for log-in and identification within the system.
2. **`password`** (Type: `String`)
    
    - **Description**: The password for the user account, which should be stored securely in the database after proper hashing.
3. **`firstName`** (Type: `String`)
    
    - **Description**: The user's first name, used for personalization and identification.
4. **`lastName`** (Type: `String`)
    
    - **Description**: The user's last name, also used for personalization and identification.
5. **`email`** (Type: `String`)
    
    - **Description**: The user's email address, used for communication and possibly as an alternative username.
6. **`phoneNumber`** (Type: `String`)
    
    - **Description**: The user's phone number, important for contact and security measures such as two-factor authentication.

### Usage

The `UserRegistrationDTO` is used during the registration process in web or mobile applications. It is the primary data structure passed between the client and server when a new user is creating an account. It ensures that all required fields are collected in one place, simplifying the handling of user data during the registration workflow.

---

This DTO is essential for ensuring that user registration processes are handled efficiently and securely, maintaining good practices by encapsulating user registration data in a single, manageable object.