package com.mt.brightauthorization.repoitory;

import com.mt.brightauthorization.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {

    Users findByPhone(String phone);
}
