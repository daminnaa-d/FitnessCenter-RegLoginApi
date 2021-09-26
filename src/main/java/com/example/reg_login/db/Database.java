package com.example.reg_login.db;

import com.example.reg_login.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Database {
    private static Database database;
    private List<User> users = new ArrayList<>();

    private Database(){
    }

    public static Database getDatabase(){
        if(database == null){
            database = new Database();
        }
        return database;
    }

}
