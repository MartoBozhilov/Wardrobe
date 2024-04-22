### Documentation for `DiscountExceptionHandler` Class

**Package**: `bg.conquerors.wardrobe.exceptions`

**Imports**:

- `org.springframework.http.HttpStatus`
- `org.springframework.http.ResponseEntity`
- `org.springframework.validation.FieldError`
- `org.springframework.web.bind.MethodArgumentNotValidException`
- `org.springframework.web.bind.annotation.ControllerAdvice`
- `org.springframework.web.bind.annotation.ExceptionHandler`
- `org.springframework.web.bind.annotation.ResponseStatus`
- `java.util.HashMap`
- `java.util.Map`

**Class Annotation**:

- `@ControllerAdvice`: Designates this class as a global exception handler within the Spring MVC framework. This means it can catch exceptions across all controllers, providing a centralized point for handling common exceptions.

### Method Documentation

#### handleMethodArgumentNotValidException

- **Annotation**: `@ResponseStatus(HttpStatus.BAD_REQUEST)`
    
    - This annotation sets the HTTP response status to `BAD_REQUEST` (400) whenever the handled exception is thrown.
- **Handler**: `@ExceptionHandler(MethodArgumentNotValidException.class)`
    
    - Indicates that this method handles `MethodArgumentNotValidException`, which is typically thrown when validation on an argument annotated with `@Valid` fails.
- **Method Signature**:
    
    - `public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException)`
    - Takes a `MethodArgumentNotValidException` as its parameter.
- **Functionality**:
    
    - **Error Mapping**: The method processes the `BindingResult` from the exception to extract field errors. It then maps each field error to its default error message, compiling a `Map<String, String>` where the key is the field name and the value is the error message.
    - **Error Response**: Returns a `ResponseEntity` containing the errors map and the HTTP status code `BAD_REQUEST`. This provides detailed feedback to the client about what went wrong with their input.

### General Usage

- **Global Error Handling**: As a `@ControllerAdvice` class, `DiscountExceptionHandler` applies to all controllers, making it effective for handling form validation errors that occur across different parts of the application related to discount management.
    
- **Client-Side Interaction**: The detailed error messages help clients understand exactly which fields caused the validation failure and how to correct them, enhancing user experience and facilitating effective error correction on client forms.
    

### Enhancements and Considerations

- **Expandability**: Additional exception handlers can be added to this class to manage other types of common exceptions encountered in the application.
- **User Feedback**: While providing detailed error information is helpful for debugging and user correction, it should be done carefully to avoid leaking sensitive data or implementation details.
- **Performance Considerations**: Handling exceptions in this detailed manner might slightly impact performance, but it is essential for maintaining robust, user-friendly error reporting.