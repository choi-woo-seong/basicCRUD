package com.my.basicCRUD.entity;

import jakarta.persistence.*;
import lombok.Data;

//@Entity로 정의되어 있는 클래스는 데이터베이스에 테이블과 1:1 매핑 (결국 테이블이다)
@Entity
@Table(name = "member")
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    @Column(name = "member_name",length = 100, nullable = false)
    private String name;
    private int age;
    private String address;

}
