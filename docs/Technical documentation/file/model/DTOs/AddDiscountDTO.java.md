### Imports

- **`jakarta.validation.constraints.Max`**
- **`jakarta.validation.constraints.Min`**
- **`jakarta.validation.constraints.NotBlank`**
- **`lombok.*`**
- **`org.springframework.format.annotation.DateTimeFormat`**
- **`java.util.Date`**

### Class Description

**`AddDiscountDTO`**

A data transfer object (DTO) that encapsulates the information required to add a discount. This class is designed with full getter and setter access, default and parameterized constructors, and its fields are enforced with validation rules.

### Annotations

- `@Getter`: Automatically generates getters for all fields.
- `@Setter`: Automatically generates setters for all fields.
- `@Data`: Bundles the features of `@ToString`, `@EqualsAndHashCode`, `@Getter`/`@Setter` and `@RequiredArgsConstructor` together: in this context, used primarily for convenience during development.
- `@NoArgsConstructor`: Generates a no-argument constructor.
- `@AllArgsConstructor`: Generates a constructor that takes a parameter for each field in the class.

### Fields

1. **`startDate`** (Type: `Date`)
    - **Description**: The starting date of the discount period.
    - **Validation**:
        - `@NotBlank`: Ensures the field is not null and trimmed length is greater than zero with the message "You need to specify a start date!".
        - `@DateTimeFormat(pattern = "yyyy-MM-dd")`: Specifies the format for the date expected to be "year-month-day".
2. **`endDate`** (Type: `Date`)
    - **Description**: The ending date of the discount period.
    - **Validation**:
        - `@NotBlank`: Ensures the field is not null and the trimmed length is greater than zero, with the message "You need to specify an end date greater than a start date!".
        - `@DateTimeFormat(pattern = "yyyy-MM-dd")`: Specifies the format for the date expected to be "year-month-day".
3. **`discountPercentage`** (Type: `int`)
    - **Description**: The percentage of the discount applied.
    - **Validation**:
        - `@NotBlank`: Ensures the field is not null, typically more applicable to String types; likely an error in context unless logic for numeric types is intended.
        - `@Min(value = 0)`: Ensures the discount percentage cannot be less than 0%, with a corresponding message.
        - `@Max(value = 100)`: Ensures the discount percentage cannot exceed 100%, with a corresponding message.

### Usage

This class is typically used to transfer discount data from client-side to server-side in web applications, ensuring that all necessary validations are in place before the data is processed or stored in the database.

---

Note: The `@NotBlank` annotations on the `startDate` and `endDate` fields may have been misused, as this annotation is usually applied to String fields. Consider using `@NotNull` if the intention is to prevent null dates. For `discountPercentage`, the `@NotBlank` should be replaced with appropriate validations suitable for integer fields, like `@NotNull`.