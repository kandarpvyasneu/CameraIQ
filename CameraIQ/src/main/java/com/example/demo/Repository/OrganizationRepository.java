package com.example.demo.Repository;

import com.example.demo.Pojo.Organization;
import com.example.demo.Pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long>
{

    //@Query(value = "SELECT new com.example.demo.Pojo.User(u.firstname,u.lastname, u.email,u.phone,u.address,u.organization=o) from User u INNER JOIN Organization o ON u.id =o.id where o.id=?1", nativeQuery = true)
    //List<User> findByOrganization_Id(Long Id);

    Organization findByUsers(User user);
}
