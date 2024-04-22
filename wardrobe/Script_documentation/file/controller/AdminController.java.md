### `AdminController` Class

**Package**: `bg.conquerors.wardrobe.controller`

**Imports**: Various, including models, DTOs, services, and standard Java and Spring libraries.

**Annotations**:

- `@Controller`: Specifies that this class serves as a Spring MVC controller.
- `@RequestMapping("/admin")`: Maps all HTTP requests that start with `/admin` to the methods in this controller.

**Constructor**:

- The constructor accepts `AdminService`, `ProductService`, and `OrderService` as parameters, facilitating operations related to administration, product handling, and order processing.

### Methods Documentation

#### General Admin Methods

- `admin(Model model)`: Displays the admin dashboard, typically populated with product views.

#### Product Management (CRUD)

- `addProduct(Model model)`: Prepares a model for a new product form.
- `addProduct(AddProductDTO addProductDTO, BindingResult result)`: Processes the submission of a new product form.
- `editProduct(String productNumber, Model model)`: Prepares a model to edit an existing product identified by `productNumber`.
- `editProduct(String productNumber, AddProductDTO addProductDTO)`: Processes the editing of an existing product.
- `deleteProductGet(String productNumber)`: Initiates the deletion of a product.
- `deleteProductPost(String productNumber)`: Confirms and processes the deletion of a product.
- `productGrid(Model model)`: Displays a grid of all products.

#### Order Management (CRUD)

- `editOrder(Long id, Model model)`: Prepares a model to edit an existing order identified by `id`.
- `editOrder(Long id, AddOrderDTO addOrderDTO)`: Processes the editing of an existing order.
- `deleteOrderGet(Long id)`: Initiates the deletion of an order.
- `deleteOrderPost(Long id)`: Confirms and processes the deletion of an order.
- `ordersGet(Model model)`: Displays a list of all orders.
- `ordersPost(Long orderId)`: Processes changes to an order's status.

#### Discount Management (CRUD)

- `addDiscount(Model model)`: Prepares a model for a new discount form.
- `addDiscount(AddDiscountDTO addDiscountDTO)`: Processes the submission of a new discount form.
- `editDiscount(Long id, Model model)`: Prepares a model to edit an existing discount identified by `id`.
- `editDiscount(Long id, AddDiscountDTO addDiscountDTO)`: Processes the editing of an existing discount.
- `deleteDiscountGet(Long id)`: Initiates the deletion of a discount.
- `deleteDiscountPost(Long id)`: Confirms and processes the deletion of a discount.

#### User Management (CRUD)

- `editUser(Long id, Model model)`: Prepares a model to edit an existing user identified by `id`.
- `editUser(Long id, AddUserDTO addUserDTO)`: Processes the editing of an existing user.
- `deleteUserGet(Long id)`: Initiates the deletion of a user.
- `deleteUserPost(Long id)`: Confirms and processes the deletion of a user.
- `usersGet(Model model)`: Displays a list of all users.

**Logging**: Utilizes `Logger` for logging various levels of information and errors.

### Usage

This class is utilized by the Spring MVC framework to handle administrative functionalities via the web interface of an application. It interacts heavily with service classes to manage data persistence and business logic.