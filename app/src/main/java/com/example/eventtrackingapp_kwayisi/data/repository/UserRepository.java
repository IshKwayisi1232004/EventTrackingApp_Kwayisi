package com.example.eventtrackingapp_kwayisi.data.repository;

import com.example.eventtrackingapp_kwayisi.data.local.User;
import com.example.eventtrackingapp_kwayisi.data.local.UserDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {
    private UserDao userDao;
    private ExecutorService executorService;

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
        executorService = Executors.newSingleThreadExecutor();
    }

    public void register(String email, String password){
        executorService.execute(() -> {
            userDao.insertUser(new User(email, password));
        });
    }

    public void login(String email, String password, LoginCallback callback){
        executorService.execute(() -> {
            User user = userDao.getUser(email);
            boolean success = user != null && user.getPassword().equals(password);
            callback.onResult(success);
        });
    }

    public interface LoginCallback {
        void onResult(boolean success);
    }
}
