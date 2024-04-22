### Documentation for `OrderController` Class

**Package**: `bg.conquerors.wardrobe.controller`

**Imports**:

- `bg.conquerors.wardrobe.model.dto.FinishOrderDTO`
- `bg.conquerors.wardrobe.model.enums.SizeEnum`
- `bg.conquerors.wardrobe.service.OrderService`
- Standard Spring MVC and Java libraries

**Class Annotation**:

- `@Controller`: Marks this class as a Spring MVC controller responsible for web requests relating to order management.

**Fields**:

- `OrderService orderService`: Service layer dependency that handles all order-related business logic.

**Constructor**:

- `OrderController(OrderService orderService)`: Initializes the controller with necessary service dependency.

### Methods Documentation

#### Cart Management

- `@GetMapping("/add-to-cart/{productNumber}/{size}/{quantity}")`
    
    - `String addProductToCart(Model model, String productNumber, SizeEnum size, Integer quantity)`: Adds a product to the shopping cart. Utilizes path variables for product details and redirects to the product detail page.
- `@PostMapping("/cart/remove-from-cart/{id}")`
    
    - `String removeProductFromCart(Long id)`: Removes a product from the shopping cart and redirects to the shopping cart view.
- `@GetMapping("/shopping-cart")`
    
    - `String shoppingCart(Model model)`: Displays the shopping cart with a list of items added. The method calculates the total price of the cart items and adds it to the model.

#### Checkout and Order Completion

- `@PostMapping("/cart/proceed-to-checkout")`
    - `String saveOrder(FinishOrderDTO finishOrderDTO, Long cartItemId) throws Exception`: Processes the checkout and saves the order. It sets the ID from the request parameter to the DTO and calls the service to save the order. Redirects to the index page after successful order placement.
- `@GetMapping("/checkout")`
    - `String checkout()`: Returns the checkout view where users can finalize their orders.

### Usage and Flow

- **Path Variables and Request Parameters**: Utilizes path variables (`{productNumber}`, `{size}`, `{quantity}`, `{id}`) and request parameters (e.g., `id` in the checkout POST method) to handle data specific to each request.
- **Error Handling**: The `saveOrder` method can throw exceptions, indicating that error handling is a consideration, though specifics are not detailed here.
- **Redirections**: Most methods redirect to another view upon completion of their task, which helps in maintaining a clean flow and user experience across the cart and checkout processes.
- **Model Enhancements**: For views that require data display, such as the shopping cart, relevant data is added to the model, ensuring that views are dynamic and responsive to changes in the cart.