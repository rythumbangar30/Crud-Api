package com.springboot.web.apiUser;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static List<User> list=new ArrayList<>();
    private static int id=2;
    static {
        User userOne=new User(1,"Cream","Indore","active");
        User userTwo=new User(2,"Centre","Dewas","active");
        list.add(0,userOne);
        list.add(1,userTwo);
    }
    public static List<User> getAllUsers(){
        return list;
    }

    public static User getUserById(int userId){
        for (User user : list) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;

//        for(int i=0;i< list.size();i++){
//            if(list.get(i).getId()==userId){
//                return list.get(i);
//            }
//        }
//        return null;
    }

    public static User addUser(User user){
        id=id+1;
        user.setId(id);
        list.add(user);
        return user;
    }

    public static User updateUser(int userId,User user){
        int index=getIndexOfList(userId);
        user.setId(userId);
        list.add(index,user);
        return user;

//        for(int i=0;i< list.size();i++){
//            if(list.get(i).getId()==userId){
//                user.setId(userId);
//                list.add(i,user);
//                return user;
//            }
//        }
//        return null;
    }

    public static User deleteUser(int userId){
        int index=getIndexOfList(userId);
        return list.remove(index);

//        for(int i=0;i< list.size();i++){
//            if(list.get(i).getId()==userId){
//                return list.remove(i);
//
//            }
//        }
//        return null;
    }

    static int getIndexOfList(int userId){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getId()==userId){
                return i;
            }
        }
        return -1;
    }
}
