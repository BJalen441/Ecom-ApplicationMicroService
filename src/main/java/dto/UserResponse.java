package dto;

import com.app.ecom.user.UserRole;
import lombok.Data;

//What the client is allowed to see, nothing is pulled from here
//Is empty, mapToUserResponse fills the fields we choose from User
@Data
public class UserResponse {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private AddressDTO address;
    private UserRole role;
}
