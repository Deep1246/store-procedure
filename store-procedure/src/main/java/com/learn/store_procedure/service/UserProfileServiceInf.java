package com.learn.store_procedure.service;

import com.learn.store_procedure.model.mysql.Personalinfo;
import com.learn.store_procedure.repos.mysqldb.UserProfielRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileServiceInf {

    @Autowired
    private UserProfielRepo userProfile;

    public List<Personalinfo> inqUserProfile(String userid) {

        List<Personalinfo> userProfilesById = userProfile.getUserProfilesById(userid);
    return userProfilesById;

    }
}
