### Package `bg.conquerors.wardrobe.service`

#### Interface `AdminService`

This interface defines methods for administering various aspects of the wardrobe system, including products, discounts, orders, and users.

#### Methods:

##### Product Management:

- `void addProduct(AddProductDTO addProductDTO)`
    
    - Adds a new product to the system based on the provided DTO.
- `void editProduct(String productNumber, AddProductDTO addProductDTO)`
    
    - Edits an existing product identified by its product number using the provided DTO.
- `void deleteProduct(String productNumber)`
    
    - Deletes a product from the system based on its product number.
- `AddProductDTO getProductByProductNumber(String id)`
    
    - Retrieves product information for a given product number.

##### Discount Management:

- `void addDiscount(AddDiscountDTO addDiscountDTO)`
    
    - Adds a new discount to the system based on the provided DTO.
- `void editDiscount(Long id, AddDiscountDTO addDiscountDTO)`
    
    - Edits an existing discount identified by its ID using the provided DTO.
- `void deleteDiscount(Long id)`
    
    - Deletes a discount from the system based on its ID.
- `AddDiscountDTO getDiscountById(Long id)`
    
    - Retrieves discount information for a given discount ID.

##### Order Management:

- `void editOrder(Long id, AddOrderDTO addOrderDTO)`
    
    - Edits an existing order identified by its ID using the provided DTO.
- `void deleteOrder(Long id)`
    
    - Deletes an order from the system based on its ID.
- `void deleteOrderProduct(Long id)`
    
    - Deletes a product from an order based on its ID.
- `void addOrderProduct(Long orderId, Long productId, Integer quantity)`
    
    - Adds a product to an order with the specified quantity.
- `AddOrderDTO getOrderById(Long id)`
    
    - Retrieves order information for a given order ID.
- `void changeStatus(Long id)`
    
    - Changes the status of an order identified by its ID.

##### User Management:

- `void editUser(Long id, AddUserDTO addUserDTO)`
    
    - Edits an existing user identified by its ID using the provided DTO.
- `void deleteUser(Long id)`
    
    - Deletes a user from the system based on its ID.
- `AddUserDTO getUserById(Long id)`
    
    - Retrieves user information for a given user ID.

#### Exceptions:

- `JsonProcessingException`: This exception can be thrown during JSON processing operations.

#### Note:

- Ensure proper handling of exceptions, especially `JsonProcessingException`, during JSON processing operations.