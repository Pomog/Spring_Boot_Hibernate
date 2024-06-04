package com.pomogSpringBoot.testApp.service.dbService;

import com.pomogSpringBoot.testApp.dao.AuthorityDAO;
import com.pomogSpringBoot.testApp.dao.UserDao;
import com.pomogSpringBoot.testApp.entity.user.Authority;
import com.pomogSpringBoot.testApp.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService{
    private final UserDao userDao;
    private AuthorityDAO authorityDAO;
    
    @Autowired
    public UserServiceImpl(UserDao userDao, AuthorityDAO authorityDAO) {
        this.userDao = userDao;
        this.authorityDAO = authorityDAO;
    }
    
    @Override
    public User findByUserName(String userName) {
        User foundUser = userDao.findByUserName(userName);
        System.out.println("findByUserName");
        System.out.println(foundUser.getUsername());
        return foundUser;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getAuthorities()));
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Authority> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());
    }
}