package com.app.ecom.user;

import dto.AddressDTO;
import dto.UserRequest;
import dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //Marks as string management component
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserResponse> fetchAllUser() {
        return userRepository.findAll().stream() //using .findAll instead of just returning an ArrayList(It's a Database function)
                .map(this::mapToUserResponse)
                .toList(); //Database function
    }

    public UserResponse addUser(UserRequest request) {
//Why are we still using User, and perhaps not UserResponse
        User user = new User();
        applyRequest(user, request);
        return mapToUserResponse(userRepository.save(user)); //Use .save instead of .add
    }

    private User findUserOrThrow(Long id) {
//So were still using User(I need to know what is being pulled from User
// and what's being pulled from UserResponse)
//This previously was its own function that didn't exist with
// UserResponse fetchUser but now mapToUserResponse now calls this function
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public UserResponse fetchUser(Long id) {
        return mapToUserResponse(findUserOrThrow(id));
    }

//    public User updateUser(Long id, User updatedUser) {
//        User existingUser = userRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException(id));
//
//        existingUser.setFirstName(updatedUser.getFirstName());
//        existingUser.setLastName(updatedUser.getLastName());
//
//        return userRepository.save(existingUser);
//    }

    public UserResponse updateUser(Long id, UserRequest request) {
        User user = findUserOrThrow(id);
        applyRequest(user, request);
        return mapToUserResponse(userRepository.save(user));
    }

    private void applyRequest(User user, UserRequest request) {
        user.setFirstName(request.getFirstName());
        user.setMiddleName(request.getMiddleName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());

        if (request.getAddress() != null) {
            Address address = user.getAddress() != null ? user.getAddress() : new Address();
            address.setStreet(request.getAddress().getStreet());
            address.setCity(request.getAddress().getCity());
            address.setState(request.getAddress().getState());
            address.setCountry(request.getAddress().getCountry());
            address.setZipCode(request.getAddress().getZipCode());
            user.setAddress(address);
        }
    }

//Converts the User(database object) into UserResponse
//Then gets turning into JSON for the client
//Copies the fields I want to expose or leave behind
    private UserResponse mapToUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setMiddleName(user.getMiddleName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setRole(user.getRole());

        if (user.getAddress() != null) {
            AddressDTO dto = new AddressDTO();
            dto.setStreet(user.getAddress().getStreet());
            dto.setCity(user.getAddress().getCity());
            dto.setState(user.getAddress().getState());
            dto.setCountry(user.getAddress().getCountry());
            dto.setZipCode(user.getAddress().getZipCode());
            response.setAddress(dto);
        }
        return response;
    }
}