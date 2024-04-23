## Overview

This section outlines the repository structure of the project. Each repository interface corresponds to a specific domain entity and defines the data access layer for that entity.

### Repositories

#### [`DiscountRepository`](repository/DiscountRepository.java.md)

- Manages interactions with the discount data.

#### [`OrderDetailRepository`](repository/OrderDetailRepository.java.md)

- Handles data operations for order line items.

#### [`OrderRepository`](repository/OrderRepository.java.md)

- Responsible for operations related to order entities.

#### [`ProductRepository`](repository/ProductRepository.java.md)

- Facilitates data access for product-related operations.

#### [`RoleRepository`](repository/RoleRepository.java.md)

- Manages role data for access control purposes.

#### [`TagRepository`](repository/TagRepository.java.md)

- Deals with operations surrounding product tags.

#### [`UserRepository`](repository/UserRepository.java.md)

- Handles data persistence and retrieval for user entities.

## Details

Each repository interface extends a common base repository, providing CRUD operations and additional methods tailored to the needs of the specific domain entity. The repositories are designed to abstract the underlying data source and provide a clean separation of concerns between the data access layer and business logic layer of the application.
