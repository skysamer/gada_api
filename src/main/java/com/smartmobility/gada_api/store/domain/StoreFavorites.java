package com.smartmobility.gada_api.store.domain;

import com.smartmobility.gada_api.member.domain.Member;
import io.swagger.annotations.ApiModel;
import lombok.Getter;

import javax.persistence.*;

@Entity @Table(name = "store_favorites")
@ApiModel(value = "가게 즐겨찾기 연결 엔티티")
@Getter
public class StoreFavorites {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}
