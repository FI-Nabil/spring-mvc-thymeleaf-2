package com.finabil.service;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

import com.finabil.model.User;
// this annotation makes it singletone object
@Service
public class UserService {
    private List<User> userList = new ArrayList<>();
    public void addUser(User user){
        userList.add(user);
    }
    public List<User> getAllUsers(){

        return userList;
    }

}
