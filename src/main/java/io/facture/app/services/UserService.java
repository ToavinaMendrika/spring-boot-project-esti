package io.facture.app.services;

import io.facture.app.entities.User;
import io.facture.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findByEmail(String email)
    {
        return userRepository.findAllByEmail(email);
    }

    public void saveUser(User user)
    {
        System.out.println(user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail());
        user.setName(user.getName());
        user.setCapital(user.getCapital());
        user.setActivity(user.getActivity());
        user.setCeo(user.getCeo());
        user.setCreated_at(new Date());
        userRepository.save(user);
    }
}
