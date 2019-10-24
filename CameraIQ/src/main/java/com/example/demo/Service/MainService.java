package com.example.demo.Service;

import com.example.demo.Exception.MainException;
import com.example.demo.Pojo.Organization;
import com.example.demo.Pojo.User;
import com.example.demo.Repository.OrganizationRepository;
import com.example.demo.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MainService
{

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private UserRepository userRepository;

    private final static Logger logger = LoggerFactory.getLogger(MainService.class);

    public Organization createOrganization(Organization organization) throws MainException
    {
        logger.info("-Entered into the createOrganization() service method-");

        Organization newOrganization = organizationRepository.save(organization);

        logger.info("-Exited from the createOrganization() service method-");

        return newOrganization;
    }

    public User createUser(User user) throws MainException
    {
        logger.info("-Entered into the createUser() service method-");

        User newUser = userRepository.save(user);

        logger.info("-Exited from the createUser() service method-");

        return newUser;
    }

    public Optional<User> findUserById(Long Id) throws MainException
    {
        logger.info("-Entered into the findUserById() service method-");

        if (Id < 0)
            throw new MainException(501, "Id can not be negative");

        Optional<User> curr = userRepository.findById(Id);

        if (!curr.isPresent())
            throw new MainException(502, "No record found");

        logger.info("-Exited from the findUserById() service method-");

        return curr;
    }

    public ResponseEntity updateUser(User user, Long Id) //throws MainException
    {
        logger.info("-Entered into the updateUser() service method-");

        try
        {
            Optional<User> currUser = findUserById(Id);

            if (currUser.isPresent())
            {
                User obj = currUser.get();

                obj.setAddress(user.getAddress());
                obj.setEmail(user.getEmail());
                obj.setFirstname(user.getFirstname());
                obj.setLastname(user.getLastname());
                obj.setOrganization(user.getOrganization());
                userRepository.save(obj);
                return ResponseEntity.ok("User Updated successfully");
            }
            else
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Permission denied to update the User");
            }
        }
        catch (MainException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Permission denied to update the User");
        }
    }

    public ResponseEntity deleteUser(Long Id)
    {
        try
        {
            Optional<User> currUser = findUserById(Id);

            if (currUser.isPresent())
            {
                userRepository.deleteById(Id);
                return ResponseEntity.ok("User deleted successfully");
            }
            else
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Permission denied to delete the User");

            }
        }
        catch (MainException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Permission denied to delete the User");
        }
    }

    public List<User> getAllUsersWithOrgID(Long Id)
    {
        Optional<Organization> organization = organizationRepository.findById(Id);

        return userRepository.findAllByOrganization(organization.get());
    }

    public Organization getAllOrganizationWithUserId(Long Id)
    {
        Optional<User> user = userRepository.findById(Id);

        return organizationRepository.findByUsers(user.get());
    }
}
