## Package

`bg.conquerors.wardrobe.service.impl`

## Imports

The class imports necessary components for user management and Spring Security.

## Class

`public class WardrobeUserDetailsService implements UserDetailsService`

## Description

`WardrobeUserDetailsService` provides an implementation of the `UserDetailsService` interface from Spring Security. This service is crucial for integrating the application's user management with Spring Security by loading user details based on usernames.

## Dependency

- `UserRepository`: For accessing user data from the database.

## Constructor

`WardrobeUserDetailsService(UserRepository userRepository)`

- Initializes the `userRepository` for use within the class to perform database operations related to user data retrieval.

## Methods

### `UserDetails loadUserByUsername(String username) throws UsernameNotFoundException`

Loads user details needed by Spring Security during the authentication process.

#### Implementation Details:

- Retrieves a user entity by username using `userRepository.findByUsername`.
- Maps the `User` entity to `UserDetails` using the static method `map`.
- Throws `UsernameNotFoundException` if no user is found with the provided username.

### `private static UserDetails map(bg.conquerors.wardrobe.model.entity.User user)`

Converts a `User` entity into a `UserDetails` object that Spring Security can use for authentication and authorization.

#### Implementation Details:

- Builds a `UserDetails` object with username, password, and authorities.
- Maps each user role to a `GrantedAuthority` using the `map` method for user roles.

### `private static GrantedAuthority map(UserRole userRole)`

Converts a `UserRole` entity into a `GrantedAuthority` object.

#### Implementation Details:

- Converts the role name from the `UserRole` entity to a format suitable for Spring Security (prefixed with `ROLE_`).

## Security Integration

This service is part of the security configuration of the application. It is essential for authenticating users based on the stored data and assigning the correct authorities for access control.

## Error Handling

Uses the standard pattern for Spring Security user details loading by throwing `UsernameNotFoundException` if a user cannot be found, ensuring that the authentication process handles it appropriately.

## Usage

This service should be configured as part of Spring Security's authentication manager to provide user details during the login process. It ensures that users can log in using their stored credentials and that their authorities are correctly set up for role-based access control.

## Considerations

- The method `loadUserByUsername` needs to efficiently handle the retrieval and conversion of user data, as it is a critical part of the security process.
- Error handling should be robust to provide clear messages for authentication failures.
- It may be beneficial to add logging for successful and unsuccessful authentication attempts for auditing and debugging purposes.

This documentation provides a comprehensive overview of the `WardrobeUserDetailsService` class, detailing its responsibilities, interactions with other components, and its role in the system's security architecture.