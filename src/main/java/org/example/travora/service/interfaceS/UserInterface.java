package org.example.travora.service.interfaceS;

import org.example.travora.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserInterface {

    public User getUser(String userId) ;
    public  User updateUser (String userId) ;
    public List<User> gettAllUser() ;
    UserDetailsService userDetailsService();

}
