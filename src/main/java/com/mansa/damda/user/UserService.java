package com.mansa.damda.user;


import com.mansa.damda.market.MarketRepository;
import com.mansa.damda.order.OrderRepository;
import com.mansa.damda.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MarketRepository marketRepository;
    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public void create(UserSignupDTO userSignupDTO){
        //중복 사용자 처리
        if (userRepository.existsByUserName(userSignupDTO.getUserName())) {
            throw new IllegalArgumentException("이미 사용 중인 사용자명입니다.");
        }
        //사용자 등록
        User user = new User();
        user.setUserNickName(userSignupDTO.getUserNickName());
        user.setUserName(userSignupDTO.getUserName());
        user.setPassword(userSignupDTO.getPassword());
        user.setPhoneNumber(userSignupDTO.getPhoneNumber());
        user.setMarket(marketRepository.findById(userSignupDTO.getMarketId())
                .orElseThrow(() -> new IllegalArgumentException("시장 조회 오류")));
        user.setAccountBank("");
        user.setAccountDigit(0);
        user.setAccountName("");

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


    @Transactional
    public void updateUser(Long userId, UserUpdateDTO updateDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 조회 오류"));

        if(updateDTO.getUserName() != null){
            user.setUserName(updateDTO.getUserName());
        }
        if(updateDTO.getUserNickName() != null){
            user.setUserNickName(updateDTO.getUserNickName());
        }
        if(updateDTO.getPassword() != null){
            user.setPassword(updateDTO.getPassword());
        }
        if(updateDTO.getPhoneNumber() != null){
            user.setPhoneNumber(updateDTO.getPhoneNumber());
        }
        if(updateDTO.getMarketId() != null){
            user.setMarket(marketRepository.findById(updateDTO.getMarketId())
                    .orElseThrow(() -> new IllegalArgumentException("시장 조회 오류")));
        }
        userRepository.save(user);
    }

    public UserAccountDTO getUserAccount(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 id 오류"));
        UserAccountDTO userAccountDTO = new UserAccountDTO();

        userAccountDTO.setAccountBank(user.getAccountBank());
        userAccountDTO.setAccountDigit(user.getAccountDigit());
        userAccountDTO.setAccountName(user.getAccountName());

        return userAccountDTO;
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 id 오류"));
    }

    @Transactional
    public void issueStampToUser(Long userId, Long stampId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 id 오류"));
        user.getStamps().add(stampId); // 사용자의 스탬프 목록에 추가
        userRepository.save(user); // 변경사항 저장
    }

    public List<Long> getUserStamps(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 id 오류"));

        return user.getStamps();
    }

}
