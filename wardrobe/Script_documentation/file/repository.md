## Overview

This section outlines the repository structure of the project. Each repository interface corresponds to a specific domain entity and defines the data access layer for that entity.

### Repositories

#### [`DiscountRepository`](DiscountRepository.java.md)

- Manages interactions with the discount data.

#### [`OrderDetailRepository`](OrderDetailRepository.java.md)

- Handles data operations for order line items.

#### [`OrderRepository`](OrderRepository.java.md)

- Responsible for operations related to order entities.

#### [`ProductRepository`](ProductRepository.java.md)

- Facilitates data access for product-related operations.

#### [`RoleRepository`](RoleRepository.java.md)

- Manages role data for access control purposes.

#### [`TagRepository`](TagRepository.java.md)

- Deals with operations surrounding product tags.

#### [`UserRepository`](UserRepository.java.md)

- Handles data persistence and retrieval for user entities.

## Details

Each repository interface extends a common base repository, providing CRUD operations and additional methods tailored to the needs of the specific domain entity. The repositories are designed to abstract the underlying data source and provide a clean separation of concerns between the data access layer and business logic layer of the application.
