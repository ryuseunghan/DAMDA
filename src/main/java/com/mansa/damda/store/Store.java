package com.mansa.damda.store;


import com.mansa.damda.category.Category;
import com.mansa.damda.market.Market;
import com.mansa.damda.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "store_description")
    private String storeDescription;

    //JPA는 객체 전체를 참조 이때 jpa에서 db로 저장할 때 user_id col에 저장
    //EAGER LOADING 단점으로 인해 LAZY 이용
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id")
    private Market market;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
