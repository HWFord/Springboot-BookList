package com.fr.hwf.springboot.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.hwf.springboot.entites.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}