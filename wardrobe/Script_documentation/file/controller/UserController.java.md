### Documentation for `UserController` Class

**Package**: `bg.conquerors.wardrobe.controller`

**Imports**:

- `bg.conquerors.wardrobe.model.dto.AddProductDTO`
- `bg.conquerors.wardrobe.model.dto.UserRegistrationDTO`
- `bg.conquerors.wardrobe.model.entity.User`
- `bg.conquerors.wardrobe.repository.UserRepository`
- `bg.conquerors.wardrobe.service.UserService`
- `org.springframework.stereotype.Controller`
- `org.springframework.ui.Model`
- `org.springframework.validation.BindingResult`
- `org.springframework.web.bind.annotation.*`
- `javax.naming.Binding`
- `java.util.Optional`

**Class Annotation**:

- `@Controller`: Designates this class as a Spring MVC controller, managing web requests related to user functionalities.

**Constructor**:

- `UserController(UserService userService, UserRepository userRepository)`: Initializes the controller with necessary service and repository dependencies for user management.

### Methods Documentation

#### User Account Management

- `@GetMapping("/users/login")`
    
    - `String login()`: Displays the login page.
- `@GetMapping("/users/account")`
    
    - `String account(Model model)`: Retrieves and displays the current user's account details. It fetches the user details using `userService.findCurrentUser()` and adds it to the model for the "account" view.
- `@GetMapping("/users/account-update/{userId}")`
    
    - `String editUser(@PathVariable("userId") Long userId, Model model)`: Prepares the user information for editing by loading current user data into the "account-update" form. Utilizes `userService.findCurrentUser()` to fetch user details and includes the user's ID in the model.
- `@PostMapping("/users/account-update/{userId}")`
    
    - `String editUser(@PathVariable("userId") Long userId, UserRegistrationDTO userRegistrationDTO)`: Processes the submission of the user information edit form. It updates the user's information in the database via `userService.editUserInformation()` and redirects to the account view.

#### User Registration

- `@GetMapping("/users/register")`
    
    - `String register(Model model)`: Displays the registration form for new users. Initializes a `UserRegistrationDTO` object to capture user input.
- `@PostMapping("/users/register")`
    
    - `String register(UserRegistrationDTO userRegistrationDTO)`: Processes the registration of a new user. Calls `userService.register(userRegistrationDTO)` to add the user to the database and redirects to the login page upon successful registration.

### General Usage

- **Model Interaction**: In methods displaying views (`account`, `editUser`, `register`), the controller interacts with the `Model` object to pass necessary data for rendering the view templates.
    
- **Path Variables**: Uses `@PathVariable` to capture dynamic values from the URL (e.g., `userId` in `account-update` methods), which allows the controller to process user-specific requests.
    
- **Redirections**: After processing form submissions, the controller typically redirects to other views to reflect updates (e.g., redirecting to `/users/account` after updating user information).
    

### Enhancements and Considerations

- **Security Aspects**: While not detailed in the documentation, security measures such as authentication and authorization checks should be considered to protect user data.
    
- **Error Handling**: Enhanced error handling could be implemented to manage scenarios where user data is not found or when data validation fails during registration or update processes.