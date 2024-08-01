package com.nandonecode.fullstack_project.controller;


import com.nandonecode.fullstack_project.confirmation.ApiResponse;
import com.nandonecode.fullstack_project.exception.UserNotFoundException;
import com.nandonecode.fullstack_project.model.User;
import com.nandonecode.fullstack_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController // Indica che questa classe è un controller RESTful
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired // Inietta automaticamente il bean userRepository
    private UserRepository userRepository;

    @PostMapping("/user")  // Mappa le richieste HTTP POST all'URL /user su questo metodo
    User newUser(@RequestBody User newUser) {  // Deserializza il corpo della richiesta in un oggetto User
        return userRepository.save(newUser);  // Salva il nuovo utente nel database e lo restituisce come risposta
    }

    @GetMapping("/user")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    ApiResponse deleteUser(@PathVariable Long id){
        if( !userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return new ApiResponse("User with id " + id + " has been deleted sucessfully.");
    }

}
