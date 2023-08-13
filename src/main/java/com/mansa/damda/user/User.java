package com.mansa.damda.user;


import com.mansa.damda.market.Market;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="user_id")
    private Long userId;

    @Column(name ="user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "account")
    private String account;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id")
    private Market market;

    //기본 생성자 (JPA를 위해 필요)
    public User(){
    }

}
