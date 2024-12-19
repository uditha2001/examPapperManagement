package com.example.examManagementBackend.userManagement.userManagementController;

import com.example.examManagementBackend.userManagement.userManagementDTO.UserDTO;
import com.example.examManagementBackend.userManagement.userManagementDTO.UserProfileDTO;
import com.example.examManagementBackend.userManagement.userManagementServices.UserManagementServices;
import com.example.examManagementBackend.userManagement.userManagementServices.UserProfileServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserDetailsController {
    @Autowired
    private UserManagementServices userService;

    @Autowired
    private UserProfileServices userProfileServices;
    //used to add a user

    @PostMapping(path="/addUser")
    @PreAuthorize("hasRole('ADMIN')")
    public String addUser(@RequestBody UserDTO userdto) {
        String message=userService.saveUser(userdto);
        System.out.println(message);
        return message;
    }


    @PostMapping("/addUserWithRoles")
    public String addUserWithRoles(@RequestBody UserDTO userDTO) {
        return userService.saveUserWithRoles(userDTO);
    }
    // Assign a role to a user
    @PostMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<String> assignRoleToUser(@PathVariable Long userId, @PathVariable Long roleId) {
        userService.assignRoleToUser(userId, roleId);
        return ResponseEntity.ok("Role assigned successfully.");
    }

    // Get all users with roles
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsersWithRoles() {
        List<UserDTO> users = userService.getAllUsersWithRoles();
        return ResponseEntity.ok(users);
    }

    // Delete user by ID
    @DeleteMapping(path="/deleteUser/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        String message = userService.deleteUser(userId);
        return ResponseEntity.ok(message);
    }

    // Update user details with roles
    @PutMapping(path="/updateUserWithRoles/{userId}")
    public ResponseEntity<String> updateUserWithRoles(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        String message = userService.updateUserWithRoles(userId, userDTO);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/userProfile/{userId}")
    public ResponseEntity<UserProfileDTO> getUserProfile(@PathVariable Long userId) {
        // Call the service method to get user profile
        UserProfileDTO userProfile = userProfileServices.getUserProfile(userId);
        return ResponseEntity.ok(userProfile);
    }


    // Update user details (name, email, contact, bio)
    @PutMapping(path="/updateUserProfile/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody UserProfileDTO userProfileDTO) {
        String message = userProfileServices.updateUserProfile(userId, userProfileDTO);
        return ResponseEntity.ok(message);
    }

    // Update user profile image
    @PutMapping(path="/updateProfileImage/{userId}")
    public ResponseEntity<String> updateProfileImage(@PathVariable Long userId, @RequestParam("image") MultipartFile imageFile) {
        String message = userProfileServices.updateUserProfileImage(userId, imageFile);
        return ResponseEntity.ok(message);
    }

    // Delete user profile image
    @DeleteMapping(path="/deleteProfileImage/{userId}")
    public ResponseEntity<String> deleteProfileImage(@PathVariable Long userId) {
        String message = userProfileServices.deleteUserProfileImage(userId);
        return ResponseEntity.ok(message);
    }



}
