## Package

`bg.conquerors.wardrobe.service.impl`

## Imports

The class imports data transfer objects (DTOs), entity models, repository interfaces, security and encryption components, and other services necessary for user management functionalities.

## Class

`public class UserServiceImpl implements UserService`

## Description

`UserServiceImpl` provides the implementation of the `UserService` interface, focusing on user-related operations such as registration, finding current user details, and editing user information.

## Dependencies

- `UserRepository`: For accessing user data.
- `RoleRepository`: For accessing role data related to users.
- `PasswordEncoder`: For encoding passwords in a secure manner.
- `OrderService`: For creating a new order upon user registration.

## Constructor

`UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, OrderService orderService)`

- Initializes the repositories, password encoder, and order service for use within the class.

## Methods

### `void register(UserRegistrationDTO userRegistrationDTO)`

Registers a new user with the application.

#### Implementation Details:

- Checks if a user already exists with the given username.
- If not, maps the `UserRegistrationDTO` to a `User` entity and saves it.
- Creates a new order for the user as part of the registration process.

### `private User map(UserRegistrationDTO userRegistrationDTO)`

Maps data from `UserRegistrationDTO` to `User` entity.

#### Implementation Details:

- Sets user properties from the DTO.
- Encodes the password using `PasswordEncoder`.
- Assigns the default role to the user by fetching it from the `RoleRepository`.
- Initializes the user with zero points and an empty list of orders.

### `User findCurrentUser()`

Finds and returns the currently authenticated user.

#### Implementation Details:

- Retrieves the username from the security context.
- Fetches the user from the repository or throws `UsernameNotFoundException` if the user is not found.

### `void editUserInformation(Long id, UserRegistrationDTO userRegistrationDTO)`

Updates user information for an existing user.

#### Implementation Details:

- Retrieves an existing user by ID.
- Updates user details based on the provided `UserRegistrationDTO`.
- Conditionally updates the password if it is provided in the DTO.
- Saves the updated user entity.

## Service Annotation

`@Service` marks the class as a Spring-managed service component, indicating that it should be automatically detected and managed by Spring's dependency injection facilities.

## Security Context

Uses `SecurityContextHolder` to access the authentication details of the currently logged-in user, integrating seamlessly with Spring Security.

## Exception Handling

- `findCurrentUser` and `editUserInformation` methods use exception throwing for error handling, specifically throwing `UsernameNotFoundException` when a user cannot be found either by username or ID.

## Transaction Handling

While the class does not explicitly handle transactions, it is implied that the service methods may participate in transactions managed by Spring's transaction management facilities, particularly methods that modify data.

## Considerations

- The registration method does not handle the case where the username is already in use apart from returning without action, which might not be informative for the client. Consider improving feedback mechanisms.
- The password change check in `editUserInformation` should ensure robust security practices, such as validating the new password against security policies before encoding and saving.

This documentation provides a detailed overview of the `UserServiceImpl` class, highlighting its functionality, internal methods, and interactions with other components within the system. It is designed to ensure maintainability and ease of understanding for future developers or maintainers of the code.