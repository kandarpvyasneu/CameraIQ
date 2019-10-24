package com.example.demo.Repository;

import com.example.demo.Pojo.Organization;
import com.example.demo.Pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>
{

    @Query(value = "UPDATE User u SET u.firstname = ?1, u.lastname = ?2, u.email = ?3, u.phone = ?4, u.address = ?5,u.organization.id =?6 WHERE u.userId = ?7", nativeQuery = true)
    void updateUserById(String firstname, String lastname, String email, String phone, String address, Long id, Long userId);

    List<User> findAllByOrganization(Organization organization);
}
