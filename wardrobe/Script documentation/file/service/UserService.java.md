### Package `bg.conquerors.wardrobe.service`

#### Interface `UserService`

This interface provides methods for managing user-related operations within the wardrobe system.

#### Methods:

- `void register(UserRegistrationDTO userRegistrationDTO)`
    
    - Registers a new user based on the provided registration details.
- `User findCurrentUser()`
    
    - Retrieves the currently logged-in user.
- `void editUserInformation(Long id, UserRegistrationDTO userRegistrationDTO)`
    
    - Edits the information of an existing user identified by their ID using the provided registration details.

#### Note:

- These methods facilitate user registration, retrieval of the current user, and editing user information.