### Package `bg.conquerors.wardrobe.service`

#### Interface `OrderService`

This interface provides methods for managing orders within the wardrobe system.

#### Methods:

- `void createNewOrder(User user)`
    
    - Creates a new order for the specified user.
- `void addProductToCart(String productNumber, SizeEnum size, Integer quantity)`
    
    - Adds a product to the user's shopping cart with the specified size and quantity.
- `void removeProductFromCart(Long id)`
    
    - Removes a product from the user's shopping cart based on its ID.
- `CartViewDTO getCart()`
    
    - Retrieves the current contents of the user's shopping cart.
- `void saveOrder(FinishOrderDTO finishOrderDTO) throws Exception`
    
    - Saves the order with the provided details. Throws an exception if there's an issue during the process.
- `List<Order> getAllOrders()`
    
    - Retrieves a list of all orders in the system.

#### Exceptions:

- `Exception`: This exception can be thrown during the order saving process. Ensure proper handling to manage any potential errors.

#### Note:

- Ensure proper handling of exceptions to maintain system stability and reliability.