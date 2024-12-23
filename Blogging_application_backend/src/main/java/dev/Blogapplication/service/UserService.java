package dev.Blogapplication.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.Blogapplication.dto.Userdto;
import dev.Blogapplication.entity.User;
import dev.Blogapplication.exception.DataNotFoundException;
import dev.Blogapplication.repository.UserRepo;
import dev.Blogapplication.utils.IdGenerator;

@Service
public class UserService {
    
      
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IdGenerator idGenerator;

    public Userdto addUser(Userdto userDto){

        User user = dtoToUser(userDto);
        user.setUser_id(idGenerator.generatedId("USER-"));
        User saveduser = userRepo.save(user);

        return userToDto(saveduser);
    }

    public List<Userdto> getAllUsers(){

        List<User> users = userRepo.findAll();

        List<Userdto> userDtos = users.stream().map(user-> userToDto(user)).collect(Collectors.toList());
        
        return userDtos;
        
    }

    public Userdto getUserById(String id){

        User user = userRepo.findById(id).orElseThrow(() -> new DataNotFoundException("User with ID " + id + " not found"));

        return userToDto(user);
    }

    public Userdto updateUser(String id, Userdto userDto){

        User user = userRepo.findById(id).orElseThrow(() -> new DataNotFoundException("User with ID " + id + " not found"));

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUser_id(user.getUser_id());

        User updatedUser = userRepo.save(user);

        return userToDto(updatedUser);
    }

    public void deleteUser(String id){

        userRepo.findById(id).orElseThrow(() -> new DataNotFoundException("User with ID " + id + " not found"));

        userRepo.deleteById(id);
    }


    private User dtoToUser(Userdto userDto){

        //Use ModelMapper to map 2 resources or entities
        User user = modelMapper.map(userDto, User.class);

        // user.setUser_id(userDto.getUser_id());
        // user.setFirstName(userDto.getFirstName());
        // user.setLastName(userDto.getLastName());
        // user.setPassword(userDto.getPassword());
        // user.setEmail(userDto.getEmail());
        // user.setAbout(userDto.getAbout());

        return user;
    }


    private Userdto userToDto(User user){

        Userdto userDto = modelMapper.map(user, Userdto.class);

        return userDto;
    }
}
