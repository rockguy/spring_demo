package demo.service;

import demo.model.User;
import demo.repository.RequestRepository;
import demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> list() {
        return userRepository.findAll();
    }

    @Transactional
    public void saveUser(User user) {
        requestRepository.saveAll(user.getRequests());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
    }

    public User getUser(User user) {
        return userRepository.getOne(user.getLogin());
    }
}
