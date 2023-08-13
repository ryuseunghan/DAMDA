package com.mansa.damda.user;


import com.mansa.damda.market.MarketRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MarketRepository marketRepository;

    @Transactional
    public void create(UserSignupDTO userSignupDTO){
        //중복 사용자 처리
        if (userRepository.existsByUserName(userSignupDTO.getUserName())) {
            throw new IllegalArgumentException("이미 사용 중인 사용자명입니다.");
        }
        //사용자 등록
        User user = new User();
        user.setUserName(userSignupDTO.getUserName());
        user.setPassword(userSignupDTO.getPassword());
        user.setEmail(userSignupDTO.getEmail());
        user.setPhoneNumber(userSignupDTO.getPhoneNumber());
        user.setMarket(marketRepository.findById(userSignupDTO.getMarketId())
                .orElseThrow(() -> new IllegalArgumentException("시장 미입력 오류")));
        user.setAccount("");

        userRepository.save(user);
    }

    public User login(UserLoginDTO userLoginDTO) {
        String userName = userLoginDTO.getUserName();
        String password = userLoginDTO.getPassword();

        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        if (user.getPassword().equals(password)){
            return user;
        }
        else{
            throw new IllegalArgumentException("로그인 실패");
        }

    }
}
