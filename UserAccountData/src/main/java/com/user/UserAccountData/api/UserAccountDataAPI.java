package com.user.UserAccountData.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.UserAccountData.dto.UserDTO;
import com.user.UserAccountData.exception.UserAccountDataException;
import com.user.UserAccountData.service.UserAccountDataService;

@RestController
@RequestMapping("/api")
public class UserAccountDataAPI {
    @Autowired
    private UserAccountDataService userAccountDataService;

    @Autowired
    private Environment environment;

    @PostMapping(value = "/user")
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO)
    throws UserAccountDataException {
        Integer userId = userAccountDataService.addUser(userDTO);
        String successMsg = environment.getProperty(
            "API.CREATE_SUCCESS"
        ) + userId;

        return new ResponseEntity<>(successMsg, HttpStatus.CREATED);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<UserDTO> getUserByUserId(
        @PathVariable Integer userId
    ) throws UserAccountDataException {
        UserDTO userDTO = userAccountDataService.getUserByUserId(userId);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
