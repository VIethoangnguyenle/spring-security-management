package com.hoang.springsecuritylearn.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Table(name = "`User`")
@Entity(name = "`User`")
@ToString
@Getter
@Setter
@NoArgsConstructor
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String province;

    private String userId;

    private String idCard;

    private String fullName;

    private String mobile;

    private String address;

    private Integer age;

    private Date birthDay;

    private Date createAt = new Timestamp(System.currentTimeMillis());

    private Date lastModified = new Timestamp(System.currentTimeMillis());
}
