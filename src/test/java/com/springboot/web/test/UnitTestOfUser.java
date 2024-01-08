package com.springboot.web.test;

import static org.hamcrest.JMock1Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


import com.springboot.web.apiUser.User;
import com.springboot.web.apiUser.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UnitTestOfUser {
    @InjectMocks
    private UserService userService;

    @Mock
    ArrayList<User> arrayList = new ArrayList<>();

    @Test
    public void testAllUsers() {
        List<User> list = UserService.getAllUsers();
        assertEquals( list.size(),2);
    }

    @Test
    public void getUserById(){
//        UserService userService=new UserService();
        User user=UserService.getUserById(1);
        assertEquals(1,user.getId());
        assertNotNull(user);
    }

//    @Test
    public void addUser() {
        User newUser = new User(3, "Krishna", "Bhopal", "active");

        arrayList.add(newUser);
        User addUser=UserService.addUser(newUser);

        assertEquals(addUser, newUser);
        assertEquals(arrayList.size(),1);
//        assertNotNull(addUser);

    }

    @Test
    public void updateUser(){
        User newUser = new User(1, "Krishna", "Bhopal", "active");
        User newTwoUser = new User(2, "prem", "Banaras", "inactive");
        User newThreeUser = new User(3, "Indu", "Dewas", "active");
        arrayList.add(newUser);
        assertEquals(1,arrayList.size());
        int userId=arrayList.get(1).getId();

        for(int i=0;i<arrayList.size();i++){
            if(arrayList.get(i).getId()==userId){

            }
        }
        int userId=UserService.getIndexOfList(1);
        User user=UserService.getUserById(userId);
        user.setName();

        User updateUser=UserService.updateUser(1,newUser);

        assertEquals(updateUser,newUser);
        assertEquals(newUser.getId(),updateUser.getId());
        assertEquals(newUser.getCity(),updateUser.getCity());
        assertEquals(newUser.getName(),updateUser.getName());
    }

    @Test
    public void deleteUser(){

    }
}
