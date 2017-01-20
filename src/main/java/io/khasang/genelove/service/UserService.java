package io.khasang.genelove.service;

import io.khasang.genelove.dao.UserDAO;
import io.khasang.genelove.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class UserService {

    @Autowired
    UserDAO userDAO;

    public void addUser(User user){
        userDAO.addUser(user);
    }

    public void deleteUser(User user){
        userDAO.deleteUser(user);
    }

    public void editUser(User user){
        userDAO.editUser(user);
    }

    public User getUserByLogin(String login){
        return userDAO.getUserByLogin(login);
    }

    public User getUserById(int id){
        return userDAO.getUserById(id);
    }

    public List<User> getUserAll(){
        return userDAO.getUserAll();
    }

    public void addAuthorisation(User user){
        userDAO.addAuthorisation(user);
    }

    public Role getRoleByName(String name){
        return userDAO.getRoleByName(name);
    }

    public Role getRoleById(int id){
        return userDAO.getRoleById(id);
    }

    public void addFavourite(User user,Favourite favourite){
        userDAO.addFavourite(user, favourite);
    }

    public void deleteFavourite(User user, Favourite favourite) {
        userDAO.deleteFavourite(user, favourite);
    }


}