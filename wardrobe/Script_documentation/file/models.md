### Model Package Documentation

**Package Name**: `model`

**Overview**: The `model` package forms the core of the application's domain model, encompassing data structures for data transfer objects (DTOs), business entities, and enumerated types (enums). Each sub-package serves a distinct purpose in the context of the application.

#### Sub-package: `dto`

**Purpose**: Holds Data Transfer Objects that facilitate the transfer of data between different layers of the application, such as from the client to the server during a web request, or between services within the server.

**Contents**:

- [`AddDiscountDTO`](AddDiscountDTO.java.md): Represents the data needed to add a new discount.
- [`AddOrderDTO`](AddOrderDTO.java.md): Contains information required to place a new order.
- [`AddProductDTO`](AddProductDTO.java.md): Encapsulates data necessary for adding a product.
- [`AddUserDTO`](AddUserDTO.java.md): Carries data for user creation.
- [`CartItemDTO`](CartItemDTO.java.md): Defines the data structure for an item in the shopping cart.
- [`CartViewDTO`](CartViewDTO.java.md): Represents the view model of the shopping cart for the user interface.
- [`FinishOrderDTO`](FinishOrderDTO.java.md): Holds the data required to finalize an order.
- [`UserRegistrationDTO`](UserRegistrationDTO.java.md): Contains information for registering a new user.
- [`ViewProductDTO`](ViewProductDTO.java.md): Represents the detailed view of a product.
- [`ViewProductsDTO`](ViewProductsDTO.java.md): Contains data for the view of multiple products.

#### Sub-package: `entity`

**Purpose**: Contains entity classes that typically correspond to database tables, representing the persistence layer objects within the application.

**Contents**:

- [`BaseEntity`](BaseEntity.java.md): Likely an abstract class providing common fields like ID.
- [`Discount`](Discount.java.md): Entity for discount details.
- [`Order`](Order.java.md): Entity representing a customer's order.
- [`OrderDetail`](OrderDetail.java.md): Provides details of individual items within an order.
- [`Product`](Product.java.md): Entity for product details.
- [`Tag`](Tag.java.md): Represents a tag that can be associated with a product.
- [`User`](User.java.md): Entity representing a user in the system.
- [`UserRole`](UserRole.java.md): Defines the different roles a user can have.

#### Sub-package: `enums`

**Purpose**: Defines a set of constants used throughout the application to represent fixed sets of known values, such as categories or roles.

**Contents**:

- [`CategoryEnum`](CategoryEnum.java.md): Enumerates product categories.
- [`GenderEnum`](GenderEnum.java.md): Enumerates gender options for products or users.
- [`OrderStatusEnum`](OrderStatusEnum.java.md): Enumerates the different statuses an order can have.
- [`SizeEnum`](SizeEnum.java.md): Enumerates available sizes for products.
- [`StyleEnum`](StyleEnum.java.md): Enumerates styles associated with products.
- [`UserRoleEnum`](UserRoleEnum.java.md): Enumerates roles that a user can hold.
