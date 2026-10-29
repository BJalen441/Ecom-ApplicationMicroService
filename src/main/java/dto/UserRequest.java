package dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

//Client will send a request to here, UserRequest then calls
// applyRequest(User user, UserRequest request) in which the
//  user then has its fields set by the fields provided by us
//   in the UserRequest class. User then saves that request
//    into the database

//The request such as findById() uses the now created new User(.java)
// and(want to say calls mapToUserReponse, but mapToUserResponse
//  uses User instead); goes UserResponse mapTo..(User) {}

@Data
public class UserRequest {//What the client is allowed to send, has no id or role to set
    @NotBlank(message = "First name is required")
    private String firstName;

    // Optional
    private String middleName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    // Optional
    private AddressDTO address;
}