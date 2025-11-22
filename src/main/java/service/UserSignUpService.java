package service;

import dao.UserSignUpRepository;
import errorHandle.DuplicateWordException;
import errorHandle.ResourceNotFoundException;
import model.UserSignUp;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserSignUpService {

    private final UserSignUpRepository repo;

    public UserSignUpService(UserSignUpRepository repo) {
        this.repo = repo;
    }

    public List<UserSignUp> getAllUser(){ return repo.findAll(); }

    public UserSignUp getUserById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with " + id));
    }

    public UserSignUp createUser(UserSignUp user){
        if(repo.existsByMobile(user.getMobile())){
            throw new DuplicateWordException("Mobile Number already exist " + user.getMobile());
        } else {
            return repo.save(user);
        }
    }

    public UserSignUp updateUser(Long id, UserSignUp user){
        UserSignUp existingUser = getUserById(id);

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setMobile(user.getMobile());
        existingUser.setPassword(user.getPassword());
        existingUser.setConfirmPassword(user.getConfirmPassword());

        return repo.save(existingUser);
    }

    public String deleteUser(Long id){
        if(!repo.existsById(id)){
            throw new ResourceNotFoundException("User not found to delete ");
        }
        repo.deleteById(id);
        return "User Deleted Successfully";
    }

    public boolean authenticate(String mobile, String password) {
        return repo.findByMobile(mobile)
                .map(user ->  (user.getPassword()).equals(password))
                .orElse(false);
    }

    public Optional<UserSignUp> getUserDetetails(Long mobile){
        return repo.findByMobile(String.valueOf(mobile));
    }

}
