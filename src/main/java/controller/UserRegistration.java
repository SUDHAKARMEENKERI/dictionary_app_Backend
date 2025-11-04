package controller;

import model.LoginUser;
import model.UserSignUp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserSignUpService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*")
public class UserRegistration {

    private final UserSignUpService userSignUpService;

    public UserRegistration(UserSignUpService userSignUpService) {
        this.userSignUpService = userSignUpService;
    }

    @GetMapping
    public List<UserSignUp> getAllUser(){ return this.userSignUpService.getAllUser();}

    @GetMapping("/{id}")
    public UserSignUp getUserById(@PathVariable Long id){return this.userSignUpService.getUserById(id);}

    @PostMapping
    public UserSignUp createUser(@RequestBody UserSignUp user) {return this.userSignUpService.createUser(user);}

    @PutMapping("/{id}")
    public UserSignUp updateUser(@PathVariable Long id, @RequestBody UserSignUp user) {
        return this.userSignUpService.updateUser(id,user);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){ return this.userSignUpService.deleteUser(id); }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginUser request) {
        Map<String, Object> response = new HashMap<>();

        boolean isAuthenticated = userSignUpService.authenticate(request.getMobile(), request.getPassword());

        if (isAuthenticated) {
            response.put("status", "success");
            response.put("message", "Login successful");
            response.put("mobile", request.getMobile()); // example data
            response.put("isLogIn", true);
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "Invalid credentials");
            response.put("isLogIn", false);
            return ResponseEntity.status(401).body(response);
        }

    }

}
