### Documentation for `ProductController` Class

**Package**: `bg.conquerors.wardrobe.controller`

**Imports**:

- `bg.conquerors.wardrobe.model.enums.SizeEnum`
- `bg.conquerors.wardrobe.service.ProductService`
- Standard Spring MVC libraries (`org.springframework.stereotype.Controller`, `org.springframework.ui.Model`, `org.springframework.web.bind.annotation.GetMapping`, `org.springframework.web.bind.annotation.PathVariable`)

**Class Annotation**:

- `@Controller`: Marks this class as a Spring MVC controller, indicating its role in handling HTTP requests and returning responses related to product management.

**Fields**:

- `ProductService productService`: Service layer dependency that manages all product-related operations.

**Constructor**:

- `ProductController(ProductService productService)`: Initializes the controller with the `ProductService` to facilitate product management functionalities.

### Methods Documentation

#### Product Viewing

- `@GetMapping("/shop")`
    
    - `String getViewProducts(Model model)`: Retrieves and displays a list of products. It calls `productService.getViewOfProducts()` to fetch product data and adds it to the model for rendering in the "shop" view template.
- `@GetMapping("/shop/product-detail/{productNumber}")`
    
    - `String viewProduct(Model model, @PathVariable String productNumber)`: Displays detailed information about a specific product identified by `productNumber`. The method fetches product details using `productService.getProductByProductNumber(productNumber)` and adds this data along with available sizes (retrieved using `SizeEnum.values()`) to the model for display in the "product-detail" view.

### General Usage

- **Model Usage**: Both methods utilize the `Model` interface to pass data to the views. The model acts as a map where the keys are the names used in the view templates to access the data, and the values are the data objects themselves.
    
- **Path Variables**: The method `viewProduct` uses a path variable (`{productNumber}`) to capture the product number from the URL, demonstrating how dynamic data can be handled in controller methods.
    
- **Service Interaction**: The controller interacts with the `ProductService` to fetch data necessary for the views. This separation of concerns ensures the controller does not directly handle data logic but instead focuses on application flow and user interaction.
    

### Views and Navigation

- **"shop" View**: This view displays a list of all products available in the store, allowing users to browse through the offerings.
    
- **"product-detail" View**: Provides detailed information about a specific product, including size options, which helps users make informed decisions on their purchases.
    

### Enhancements and Considerations

- **Scalability**: The controller can be expanded with additional methods to handle more functionalities like searching, filtering, and sorting products.
    
- **Error Handling**: Future enhancements could include error handling to manage cases where products are not found or when the product service is unavailable.