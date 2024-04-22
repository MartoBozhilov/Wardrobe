### Package `bg.conquerors.wardrobe.service`

#### Interface `ProductService`

This interface defines methods for retrieving information about products within the wardrobe system.

#### Methods:

- `List<ViewProductsDTO> getViewOfProducts()`
    
    - Retrieves a list of DTOs representing the view of all products available in the system.
- `ViewProductDTO getProductByProductNumber(String productNumber)`
    
    - Retrieves detailed information about a product based on its product number.

#### Note:

- These methods are intended to provide clients with access to product information for browsing and viewing purposes.