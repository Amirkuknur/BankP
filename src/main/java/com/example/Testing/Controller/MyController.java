package com.example.Testing.Controller;

import com.example.Testing.Entiity.UserEntity;
import com.example.Testing.Repository.UserRepository;
import com.example.Testing.Service.Appservice;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:5175/")
public class MyController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Appservice appservice;
    @PostMapping("/createAccount")
    public ResponseEntity<String> Account(@RequestBody UserEntity user) {
        return appservice.createAccount(user);
    }



    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> Login(@RequestBody Map<String,String> loginData){
            return appservice.loginAccount(loginData);
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<String> withdrawal(@RequestBody UserEntity withdrwal){
        return appservice.withdrwalMoney(withdrwal);
    }

    @PostMapping("/AddMoney")
    public ResponseEntity<String> AddMoney(@RequestBody UserEntity AddMoney){
        return appservice.AddMoneys(AddMoney);
    }



}
