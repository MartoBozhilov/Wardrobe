## Package

`bg.conquerors.wardrobe.repository`

## Imports

- `bg.conquerors.wardrobe.model.entity.Product`
- `bg.conquerors.wardrobe.model.enums.SizeEnum`
- `org.springframework.data.jpa.repository.JpaRepository`
- `org.springframework.stereotype.Repository`
- `java.util.List`

## Interface

`public interface ProductRepository extends JpaRepository<Product, Long>`

## Annotation

`@Repository`

- **Description**: Designates the interface as a Spring Data repository. Spring will automatically create an implementation and make it available in the application context for dependency injection.

## Methods

### `List<Product> findAll()`

- **Description**: Retrieves all `Product` entities from the database.
- **Returns**: A `List` of `Product` objects representing all the products in the database.
- **Usage**: Commonly used to display a list of all products, for inventory overviews, or when presenting product selections to the user.

### `Product findAllById(Long id)`

- **Description**: Fetches a `Product` entity based on the provided ID.
- **Parameters**:
    - `Long id`: The identifier of the `Product` entity to retrieve.
- **Returns**: A `Product` object if one with the given ID exists; otherwise, `null`.
- **Usage**: Typically used to obtain details of a specific product, such as when displaying product details or during the editing process.
- **Note**: The method name suggests that it should return a list but is intended to return a single `Product` entity. The method should ideally be named `findById(Long id)` to follow standard JPA naming conventions.

### `List<Product> findAllByProductNumber(String productNumber)`

- **Description**: Retrieves all `Product` entities with the specified product number.
- **Parameters**:
    - `String productNumber`: The product number to search for.
- **Returns**: A `List` of `Product` entities matching the given product number.
- **Usage**: Useful in cases where products might have variations (such as different sizes or colors) under the same product number.

### `Product findByProductNumberAndSize(String productNumber, SizeEnum size)`

- **Description**: Retrieves a single `Product` entity based on a combination of product number and size.
- **Parameters**:
    - `String productNumber`: The product number of the item.
    - `SizeEnum size`: The size of the product as defined in `SizeEnum`.
- **Returns**: A `Product` object matching the given criteria or `null` if not found.
- **Usage**: This method is particularly helpful when you need to find a specific variation of a product, such as a particular size.

### `Long countByTagId(Long tagId)`

- **Description**: Counts how many `Product` entities are associated with a specific tag ID.
- **Parameters**:
    - `Long tagId`: The identifier of the tag.
- **Returns**: The number of `Product` entities associated with the specified tag ID.
- **Usage**: Useful for statistics and insights, such as understanding the prevalence of certain tags in the product catalog or for inventory management.

## Overview

The `ProductRepository` interface is part of the data access layer for the `Product` entity within the `bg.conquerors.wardrobe` package. It extends the `JpaRepository`, thus inheriting methods for standard CRUD operations and allowing for the definition of additional queries that can be automatically implemented by Spring Data JPA. The `@Repository` annotation ensures the interface is detected by Spring's component scanning.

## Notes

- The method `findAllById` should be reviewed to align with standard JPA conventions, possibly renaming it to `findById`.
- This interface may require additional query methods as the application requirements evolve.
- When using custom methods, such as `findByProductNumberAndSize`, ensure the correct combination of parameters to satisfy the business logic and maintain data integrity.
- Consider adding documentation for each method within the interface to explain the use case, expected inputs, and outputs for future maintainers of the code.