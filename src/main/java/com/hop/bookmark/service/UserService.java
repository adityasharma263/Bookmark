package com.hop.bookmark.service;

import com.hop.bookmark.dto.UserData;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;

public interface UserService extends UserDetailsService{

    String addUser(UserData userData);

    String getTokenByUserNameAndPass(UserData userData);
}
