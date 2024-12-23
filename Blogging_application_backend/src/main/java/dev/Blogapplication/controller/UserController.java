package dev.Blogapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.Blogapplication.dto.Userdto;
import dev.Blogapplication.payload.ApiResponse;
import dev.Blogapplication.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/Users")
public class UserController {
  
    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Userdto> addUser(@Valid @RequestBody Userdto userDto){
        
        Userdto user = userService.addUser(userDto);
        return ResponseEntity.ok(user);

    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<Userdto>> getAllUsers(){

        List<Userdto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<Userdto> getUserById(@PathVariable("id") String id){

        Userdto user = userService.getUserById(id);
        return new ResponseEntity<Userdto>(user, HttpStatus.OK);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<Userdto> updateUser(@PathVariable("id") String id, @Valid @RequestBody Userdto userDto){

        Userdto updatedUser = userService.updateUser(id, userDto);
        return new ResponseEntity<Userdto>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") String id){

        ApiResponse apiResponse = new ApiResponse();
        userService.deleteUser(id);

        apiResponse.setMessage("User " + id + " deleted successfully");
        apiResponse.setStatus(HttpStatus.OK);
        return ResponseEntity.ok(apiResponse);
    }
}
