package com.example.Testing.Repository;

import com.example.Testing.Entiity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
   Optional<UserEntity> findByPassword(String password);


   Optional<UserEntity> findById(Integer id);


}
