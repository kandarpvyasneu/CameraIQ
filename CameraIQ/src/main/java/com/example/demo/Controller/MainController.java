package com.example.demo.Controller;

import com.example.demo.Exception.MainException;
import com.example.demo.Pojo.Organization;
import com.example.demo.Pojo.User;
import com.example.demo.Service.MainService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/")
public class MainController
{

    @Autowired
    private MainService mainService;

    private final static Logger logger = LoggerFactory.getLogger(MainController.class);

    @PostMapping(value = "/createOrganization")
    public ResponseEntity<Object> createOrganization(@Valid @RequestBody Organization organization)
    {
        logger.info("---Entered into the createOrganization() controller method---");

        Organization newOrganization = null;
        try
        {
            newOrganization = mainService.createOrganization(organization);
        }
        catch (MainException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        logger.info("---Exited from the createOrganization() controller method---");

        return ResponseEntity.ok().body((Organization) newOrganization);
    }

    @PostMapping(value = "/createUser")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user)
    {
        logger.info("---Entered into the createUser() controller method---");

        User newUser = null;

        try
        {
            newUser = mainService.createUser(user);
        }
        catch (MainException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
        logger.info("---Exited from the createUser() controller method---");

        return ResponseEntity.ok().body((User) newUser);
    }

    @PutMapping(value = "/updateUser/{Id}")
    public ResponseEntity updateUser(@PathVariable("Id") Long Id, @Valid @RequestBody User user)
    {
        logger.info("---Entered into the updateUser() controller method---");

        ResponseEntity response = mainService.updateUser(user, Id);
        logger.info("---Exited from the updateUser() controller method---");

        return response;
    }

    @DeleteMapping(value = "/deleteUser/{Id}")
    public ResponseEntity deleteUser(@PathVariable("Id") Long Id)
    {
        logger.info("---Entered into the deleteUser() controller method---");

        ResponseEntity response = mainService.deleteUser(Id);

        return response;
    }

    @GetMapping(value = "/getAllUser/{Id}")
    public ResponseEntity<Object> getAllUsersWithOrgID(@PathVariable("Id") Long Id)
    {
        //List<User> userList = new ArrayList<>();

        List<User> userList = mainService.getAllUsersWithOrgID(Id);

        //System.out.println("___" + userList.toString());

        return ResponseEntity.ok().body(userList);
    }

    @GetMapping(value = "/getAllOrganization/{Id}")
    public ResponseEntity<Object> getAllOrganizationWithUserId(@PathVariable("Id") Long Id)
    {
        Organization organization = mainService.getAllOrganizationWithUserId(Id);

        //System.out.println("___" + userList.toString());

        return ResponseEntity.ok().body((Organization) organization);
    }

}
