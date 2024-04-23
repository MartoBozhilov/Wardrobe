## Package

`bg.conquerors.wardrobe.repository`

## Imports

- `bg.conquerors.wardrobe.model.entity.Discount`
- `org.springframework.data.jpa.repository.JpaRepository`
- `org.springframework.stereotype.Repository`
- `java.util.List`

## Interface Declaration

`public interface DiscountRepository extends JpaRepository<Discount, Long>`

## Annotation

`@Repository` Indicates that the interface is a Spring Data repository. Spring will provide the implementation automatically at runtime.

## Methods

### `List<Discount> findAll()`

- **Description**: Retrieves all discount entries from the database.
- **Returns**: A `List<Discount>` containing all the discount entities available.
- **Usage**: Typically used to fetch all discounts to display them to an admin panel or to apply business logic on the entire set of discounts.

### `Discount findAllById(Long id)`

- **Description**: Retrieves a discount entity based on the provided ID.
- **Parameters**:
    - `Long id`: The identifier of the discount entity to retrieve.
- **Returns**: A single `Discount` object if found, otherwise null.
- **Usage**: This method is utilized to fetch a specific discount when an identifier is known, which can be used to display details to the user or for further processing like updating or deleting a discount entry.

## Overview

The `DiscountRepository` interface is part of the data access layer within the `bg.conquerors.wardrobe` package. It provides abstracted methods to perform CRUD operations on the `Discount` entity. As an extension of `JpaRepository`, it inherits several methods capable of handling persistence operations such as saving, deleting, and finding `Discount` entities. Additional custom methods can be declared as needed to suit specific data access requirements related to discounts.