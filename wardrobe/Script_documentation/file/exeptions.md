### Directory Structure Documentation

**Directory Name**: `exceptions`

**Purpose**: The `exceptions` directory is designated for housing exception handler classes in the application. These handlers are responsible for intercepting specific exceptions thrown within the application and providing appropriate HTTP responses.

**Contents**:

1. **File**: [`DiscountExceptionHandler.java`](DiscountExeptionHandler.md)
    
    - **Description**: This file contains the `DiscountExceptionHandler` class, which is tasked with handling exceptions specifically related to discount operations within the application. It likely includes methods annotated with `@ExceptionHandler` to process instances of exceptions like `DiscountNotFoundException` or `DiscountValidationException`.
    - **Responsibilities**:
        - Capture and handle any exceptions thrown during discount-related processes.
        - Provide HTTP responses with appropriate status codes and error messages for discount-related issues.
2. **File**: [`ProductExceptionHandler.java`](ProductExeptionHandler.md)
    
    - **Description**: This file comprises the `ProductExceptionHandler` class, focusing on handling exceptions that arise during product management operations. Similarly, it probably defines methods with `@ExceptionHandler` annotations aimed at dealing with `ProductNotFoundException`, `ProductValidationException`, etc.
    - **Responsibilities**:
        - Intercept and manage exceptions thrown by product-related