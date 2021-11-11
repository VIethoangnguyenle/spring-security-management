package com.hoang.springsecuritylearn.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Table(name = "User")
@Entity(name = "User")
@ToString
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String idCard;

    private String fullName;

    private String address;

    private Integer age;

    private Date birthDay;

    private Date createAt = new Timestamp(System.currentTimeMillis());

    private Date lastModified = new Timestamp(System.currentTimeMillis());
}
