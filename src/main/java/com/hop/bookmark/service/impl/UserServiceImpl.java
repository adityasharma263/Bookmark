package com.hop.bookmark.service.impl;

import com.hop.bookmark.dao.UserDao;
import com.hop.bookmark.dto.UserData;
import com.hop.bookmark.model.UserModel;
import com.hop.bookmark.service.UserService;
import com.hop.bookmark.utility.JwtTokenUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl  implements UserService {

    private final static String USER_NOT_FOUND= "User with username %s is not found";

    private final static String BAD_CREDENTIALS= "bad credentials";

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, username)));
    }

    @Override
    public String addUser(UserData userData) {
        String encodedPass = bCryptPasswordEncoder.encode(userData.getPassword());
        userData.setPassword(encodedPass);
        UserModel userModel  = userDao.save(convertToEntity(userData));
        return jwtTokenUtil.generateToken(userModel);
    }

    @Override
    public String getTokenByUserNameAndPass(UserData userData) {
        if(userData.getUsername() != null && bCryptPasswordEncoder.matches(userData.getPassword(), loadUserByUsername(userData.getUsername())
                .getPassword())){
            return jwtTokenUtil.generateToken(convertToEntity(userData));
        }
        return BAD_CREDENTIALS;

    }

    private UserData convertToDto(UserModel userModel) {
        UserData userData = modelMapper.map(userModel, UserData.class);
        return userData;
    }

    private UserModel convertToEntity(UserData userData) throws ParseException {
        UserModel userModel = modelMapper.map(userData, UserModel.class);
        return userModel;
    }
}
