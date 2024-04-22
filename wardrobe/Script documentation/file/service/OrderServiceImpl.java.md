## Package

`bg.conquerors.wardrobe.service.impl`

## Imports

The `OrderServiceImpl` class imports entities, DTOs, enums, repositories, and other components necessary for order management functionality within the application.

## Class

`public class OrderServiceImpl implements OrderService`

## Description

`OrderServiceImpl` provides the concrete implementation of the `OrderService` interface. It encapsulates business logic associated with order processes such as creating new orders, adding products to a cart, removing products from the cart, viewing the cart, and saving an order after purchase.

## Autowired Components

- `OrderRepository` for CRUD operations on `Order` entities.
- `OrderDetailRepository` for CRUD operations on `OrderDetail` entities.
- `ProductRepository` for accessing product details.
- `UserRepository` for accessing user details.
- `EntityManager` for managing entity persistence contexts.

## Methods

### `createNewOrder(User user)`

Creates a new order with the status `CART` for a given user.

### `addProductToCart(String productNumber, SizeEnum size, Integer quantity)`

Adds a product to the cart for the currently logged-in user.

### `removeProductFromCart(Long id)`

Removes a product from the cart using the order detail ID.

### `getCart()`

Retrieves the cart for the currently logged-in user, with detailed view of the cart items.

### `saveOrder(FinishOrderDTO finishOrderDTO)`

Saves an order after a purchase is completed, updates product inventories, and calculates the total price.

### `changeProductsInventoriesQuantity(Order orderToSave)`

Updates the quantity of products in inventory based on the quantities in the order being saved.

### `getTotalPrice(Order orderToSave)`

Calculates the total price for the order based on the price and quantity of each order detail.

### `getCart(User loggedUser)`

Retrieves the cart `Order` object for the given logged-in user.

### `getAllOrders()`

Returns a list of all orders within the system.

### `getLoggedUser()`

Fetches the currently authenticated user from the security context.

## Transactions

The `@Transactional` annotation is used to ensure that the method `addProductToCart` is executed within a transactional context, which is essential for maintaining consistent state within the database.

## Entity Management

`EntityManager` is used for merging the detached `Product` entity instance to the persistence context, which is required for further operations within the transactional boundary.

## Authentication

Authentication is handled using Spring Security to fetch the currently logged-in user's information from the security context.

## Exception Handling

The method `saveOrder` throws a generic `Exception` if the product inventory does not have enough quantity to fulfill the order. More specific exception handling may be added to provide clearer error messages and for cleaner error recovery flows.

## Notes

- The service uses the `SecurityContextHolder` to access authentication details, implying that the application is secured and requires users to authenticate.
- The `getCart` method may return `null`, indicating no cart is available; the calling code must handle this case.
- The class should be properly documented, including JavaDoc for each method detailing parameters, return types, exceptions, and usage scenarios to assist developers in understanding the class's responsibilities and behaviors.
- There is no direct interaction with the `Discount` entity or related logic in this service implementation, which may need to be incorporated if discounts are to be applied to orders.
- The methods `addProductToCart` and `saveOrder` could potentially affect multiple rows in the database and should handle any concurrency issues, ensuring the atomicity of transactions.
- The use of generic `Exception` should be replaced with more specific exceptions to better manage error handling and provide more informative error messages to the users or calling services.