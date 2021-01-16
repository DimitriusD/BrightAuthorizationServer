package com.mt.brightauthorization.repoitory;

import com.mt.brightauthorization.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByName(String roleName);
}
