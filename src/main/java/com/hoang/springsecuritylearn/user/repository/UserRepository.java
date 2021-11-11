package com.hoang.springsecuritylearn.user.repository;

import com.hoang.springsecuritylearn.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
