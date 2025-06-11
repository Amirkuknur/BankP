package com.example.Testing.Service;

import com.example.Testing.Entiity.UserEntity;
import com.example.Testing.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class Appservice {

    @Autowired
    private UserRepository userRepository;
    public ResponseEntity<String> createAccount(UserEntity user) {
         try {
             userRepository.save(user);
             return  ResponseEntity.status(200).body("Account Created");
         }catch (Exception e){
             return ResponseEntity.status(500).body("Sorry");
         }
    }

    public ResponseEntity<Map<String, String>> loginAccount(Map<String, String> loginData) {
        String username=loginData.get("username");
        String password=loginData.get("password");
        Map<String,String> response=new HashMap<>();
        Optional<UserEntity> user = userRepository.findByPassword(password);
        if(user.isPresent() && password.equals(user.get().getPassword())){
            UserEntity foundUser =user.get();
           response.put("Message","LoginSuccess");
            response.put("id", String.valueOf(foundUser.getId()));
            response.put("username",user.get().getUsername());
           response.put("Balance", String.valueOf(user.get().getBalance()));

           return ResponseEntity.ok(response);
        }
        response.put("error", "Invalid credentials");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    public ResponseEntity<String> withdrwalMoney(UserEntity withdrwal) {
        int userId = withdrwal.getId();
        Double amountToWithdraw = withdrwal.getBalance(); // This is the amount user wants to withdraw

        Optional<UserEntity> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            double currentBalance = user.getBalance();

            if (amountToWithdraw > 0 && currentBalance >= amountToWithdraw) {
                user.setBalance(currentBalance - amountToWithdraw); // ✅ correct logic
                userRepository.save(user);
                return ResponseEntity.ok("Withdrawal successful. New Balance: " + user.getBalance());
            } else {
                return ResponseEntity.badRequest().body("Insufficient balance or invalid amount.");
            }
        } else {
            return ResponseEntity.badRequest().body("User not found.");
        }
    }


    public ResponseEntity<String> AddMoneys(UserEntity addMoney) {
        int UserId=addMoney.getId();
        Double CurrentMoney=addMoney.getBalance();

        Optional<UserEntity> Userbalnce = userRepository.findById(UserId);
        if(Userbalnce.isPresent()){
            UserEntity user=Userbalnce.get();
            Double AddMoney=user.getBalance();
            if(CurrentMoney>0 && AddMoney>=0){
                user.setBalance(CurrentMoney+AddMoney);
                userRepository.save(user);
                return ResponseEntity.ok("Add successful. New Balance: " + user.getBalance());
            }else {
                return ResponseEntity.badRequest().body("Insufficient balance or invalid amount.");
            }
        }else {
            return ResponseEntity.badRequest().body("User not found.");
        }
        }


}
