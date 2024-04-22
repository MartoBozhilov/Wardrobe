### Imports

- **`jakarta.validation.Valid`**
- **`jakarta.validation.constraints.Min`**
- **`jakarta.validation.constraints.NotBlank`**
- **`jakarta.validation.constraints.NotNull`**
- **`lombok.AllArgsConstructor`**
- **`lombok.Getter`**
- **`lombok.NoArgsConstructor`**
- **`lombok.Setter`**

### Class Description

**`AddUserDTO`**

A data transfer object (DTO) that encapsulates user information required for registration or user data update processes. This class is equipped with various validation annotations to ensure all user data fields are properly checked before processing.

### Annotations

- **`@Getter`**: Automatically generates getter methods for all fields.
- **`@Setter`**: Automatically generates setter methods for all fields.
- **`@NoArgsConstructor`**: Generates a no-argument constructor for the class.
- **`@AllArgsConstructor`**: Generates a constructor that takes a parameter for each field.

### Fields

1. **`email`** (Type: `String`)
    
    - **Description**: The email address of the user.
    - **Validation**:
        - **`@NotNull`**, **`@NotBlank`**: Ensures the email is not null and not just whitespace.
2. **`username`** (Type: `String`)
    
    - **Description**: The chosen username of the user.
    - **Validation**:
        - **`@NotNull`**, **`@NotBlank`**: Ensures the username is not null and not just whitespace.
3. **`password`** (Type: `String`)
    
    - **Description**: The password for the user account.
    - **Validation**:
        - **`@NotNull`**, **`@NotBlank`**: Ensures the password is not null and not just whitespace.
4. **`firstName`** (Type: `String`)
    
    - **Description**: The first name of the user.
    - **Validation**:
        - **`@NotNull`**, **`@NotBlank`**: Ensures the first name is not null and not just whitespace.
5. **`lastName`** (Type: `String`)
    
    - **Description**: The last name of the user.
    - **Validation**:
        - **`@NotNull`**, **`@NotBlank`**: Ensures the last name is not null and not just whitespace.
6. **`phoneNumber`** (Type: `String`)
    
    - **Description**: The phone number of the user.
    - **Validation**:
        - **`@NotNull`**, **`@NotBlank`**: Ensures the phone number is not null and not just whitespace.
7. **`points`** (Type: `Integer`)
    
    - **Description**: The reward points accumulated by the user.
    - **Validation**:
        - **`@NotNull`**, **`@NotBlank`** (misapplied, should be `@Min` if there's a minimum value condition or simply `@NotNull`): Ensures the points value is specified.
8. **`isAdmin`** (Type: `boolean`)
    
    - **Description**: A flag indicating whether the user has administrative privileges.
    - **Validation**:
        - **`@NotNull`**, **`@NotBlank`** (misapplied, inappropriate for a boolean type; should be `@NotNull` if nullability were possible for primitive types): Ensures the isAdmin status is specified.

### Usage

The `AddUserDTO` is typically used in web applications to register new users or update existing user information. It ensures that all required user information is validated before being processed or stored, enhancing data integrity and security within the application.

---

**Note**: There are several instances where the `@NotBlank` annotation is misused for non-String fields and for fields where `@NotNull` would suffice. Proper application of validation constraints will help avoid logical errors and enforce the correct data validations more effectively.