package com.hop.bookmark.dao;

import com.hop.bookmark.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserModel, Integer> {

    Optional<UserModel> findByUsername(String username);
}
