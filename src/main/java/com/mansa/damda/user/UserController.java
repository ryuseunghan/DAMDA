package com.mansa.damda.user;


import com.mansa.damda.order.PurchaseHistoryDTO;
import com.mansa.damda.product.ProductUpdateDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Api(tags = "Users API")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public void signup(@RequestBody UserSignupDTO userSignupDTO){
        userService.create(userSignupDTO);
    }

    @PostMapping("/login")
    public User login(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }

    @PatchMapping("/update/{userId}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long userId, @RequestBody UserUpdateDTO updateDTO) {
        userService.updateUser(userId, updateDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/account")
    public ResponseEntity<UserAccountDTO> getUserAccount(@RequestParam Long userId) {
        UserAccountDTO userAccountDTO = userService.getUserAccount(userId);
        return ResponseEntity.ok(userAccountDTO);
    }

    @GetMapping("/{userId}/stamps")
    public ResponseEntity<List<Long>> getUserStamps(@PathVariable Long userId) {
        List<Long> stamps = userService.getUserStamps(userId);
        return ResponseEntity.ok(stamps);
    }
}
