package com.learn.store_procedure.controller;

import com.learn.store_procedure.model.mysql.Personalinfo;
import com.learn.store_procedure.service.UserProfileServiceInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserProfileController {


    @Autowired
    UserProfileServiceInf userProfileServiceInf;

    @GetMapping(path = "/user-profiles/{userid}", produces = "application/json")
    public ResponseEntity<?> getUserprofile(@PathVariable  String userid) {
        //call service
        List<Personalinfo> list = userProfileServiceInf.inqUserProfile(userid);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
