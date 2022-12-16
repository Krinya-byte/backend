package com.novin.accountmanager.controller;

import com.novin.accountmanager.domain.User;
import com.novin.accountmanager.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private IUserRepository userRepository;

    @PostMapping
    public HttpStatus addUser(@RequestBody User user) {
        if (validateUser(user)) {
            return HttpStatus.BAD_REQUEST;
        }
        if (userRepository.findByUserName(user.getUserName()) != null) {
            return HttpStatus.CONFLICT;
        }
        userRepository.saveAndFlush(user);
        return HttpStatus.OK;
    }

    private boolean validateUser(User user) {
        return user == null
                || (user.getUserName() == null || user.getUserName().isEmpty())
                || (user.getName() == null || user.getName().isEmpty())
                || (user.getPassword() == null || user.getPassword().isEmpty());
    }

    @GetMapping("{userName}")
    @ResponseBody
    public ResponseEntity<User> getUserByName(@PathVariable String userName,
                                        @RequestParam(name="password",required = true) String password) {
        User user = userRepository.findByUserName(userName);
        if(user == null){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        if(password.equals(user.getPassword())){
            user.setLoginDate(new Date(System.currentTimeMillis()));
            userRepository.saveAndFlush(user);
            return new ResponseEntity<User>(user,HttpStatus.OK);
        }

        return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
    }
}
