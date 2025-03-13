package com.tyna.usermicroservice.web;

import com.tyna.usermicroservice.data.Users;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

@RestController
public class UserController {

    private final List<Users> usersL = new ArrayList<>();

    // Constructor to initialize the user list
    public UserController() {
        usersL.add(new Users("ziane", "ziane@gmail.com", LocalDate.of(2002, 3, 31)));
        usersL.add(new Users("benyahia", "benyahia@gmail.com", LocalDate.of(2002, 4, 23)));
        usersL.add(new Users("toudon", "toudon@gmail.com", LocalDate.of(2001, 4, 23)));
        usersL.add(new Users("jumia", "jumia@gmail.com", LocalDate.of(2000, 3, 15)));
        usersL.add(new Users("alice", "alice@gmail.com", LocalDate.of(2000, 9, 30)));
        usersL.add(new Users("gaye", "gaye@gmail.com", LocalDate.of(2003, 8, 27)));
        usersL.add(new Users("meziani", "meziani@gmail.com", LocalDate.of(2001, 7, 15)));
        usersL.add(new Users("ouldbraham", "ouldbraham@gmail.com", LocalDate.of(2001, 4, 4)));
        usersL.add(new Users("jolie", "jolie@gmail.com", LocalDate.of(2003, 5, 12)));
        usersL.add(new Users("elish", "elish@gmail.com", LocalDate.of(2004, 8, 13)));
    }

    // Simple endpoint to test if the API is working
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World";
    }

    // Get all users from the list
    @GetMapping("/users")
    public List<Users> getUsers() {
        return usersL;
    }

    // Get a specific user by name
    @GetMapping("/users/{name}")
    public Object getUser(@PathVariable String name) {
        for (Users user : usersL) {
            if (user.getName().equalsIgnoreCase(name)) { // Ignore la casse
                return user; // Retourne l'utilisateur trouvé
            }
        }
        return "User not exist"; // Retourne un message d'erreur simple
    }


    // Add a new user to the list
    @PostMapping("/addUsers")
    public String addUser(@RequestBody Users user) {
        if (user.getName() != null && user.getEmail() != null && user.getBirthDate() != null) {
            usersL.add(user);
            return "Utilisateur ajouté avec succès !";
        } else {
            return "Erreur : Données invalides !";
        }
    }

    // Delete a user by name
    @DeleteMapping("/delete/{name}")
    public String deleteUser(@PathVariable String name) {
        boolean removed = usersL.removeIf(user -> user.getName().equals(name));
        if (removed) {
            return "Utilisateur supprimé avec succès !";
        } else {
            return "Utilisateur non trouvé !";
        }
    }

    // Update a user's email by searching for their current email
    @PutMapping("/updateEmail/{email}")
    public String updateEmail(@PathVariable String email, @RequestParam String newEmail) {
        for (Users user : usersL) {
            if (user.getEmail().equals(email)) {
                user.setEmail(newEmail);
                return "Email mis à jour avec succès !";
            }
        }
        return "Utilisateur non trouvé !";
    }

    // Check if a user exists based on their email
    @GetMapping("/users/exists/{email}")
    public boolean userExists(@PathVariable String email) {
        return usersL.stream().anyMatch(user -> user.getEmail().equals(email));
    }

    // Get all users born in a specific year
    @GetMapping("/users/byYear/{year}")
    public List<Users> getUsersByYear(@PathVariable int year) {
        List<Users> filteredUsers = new ArrayList<>();
        for (Users user : usersL) {
            if (user.getBirthDate().getYear() == year) {
                filteredUsers.add(user);
            }
        }
        return filteredUsers;
    }


}
