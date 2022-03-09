package by.mishkevich.my_restaurant.service.impl;


import by.mishkevich.my_restaurant.entity.User;
import by.mishkevich.my_restaurant.entity.enums.Status;
import by.mishkevich.my_restaurant.exeptions.UserNotFoundException;
import by.mishkevich.my_restaurant.repository.UserRepository;
import by.mishkevich.my_restaurant.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Valid
public class UserService implements IService <User, Long> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> userId = userRepository.findById(id);
        if (userId.isEmpty()) {
            throw new UserNotFoundException("There is no user with ID = " + id + " in database");
        }
        return userId.get();
    }

    @Override
    public void create(User entity) {
        userRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public  User findByUsername(String username) {
//        Optional <User> userName = userRepository.findByUsername(username);
//        if (userName.isEmpty()) {
//            throw new UserNotFoundException("There is no user with username = " + username + " in database");
//        }
//        return userName.get(); TODO
        return userRepository.findByUsername(username);
    }

    public List<User> findAllByStatus(Status status) {
        return userRepository.findAllByStatus(status);
    }

    public List<User> findAllByEnabled(boolean enabled) {
        return userRepository.findAllByEnabled(enabled);
    }


}
