package com.user.service.UserService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.service.UserService.entities.User;

public interface UserRepository extends JpaRepository<User,String> {

}
