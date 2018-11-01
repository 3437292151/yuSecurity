package com.yu.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.yu.beans.dto.UserDTO;
import com.yu.beans.dto.UserQueryCondition;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2018-7-17.
 */
@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    @GetMapping
    public List<UserDTO> queryAllUser(){
        List<UserDTO> userDTOList = new ArrayList<>();
        userDTOList.add(new UserDTO());
        userDTOList.add(new UserDTO());
        userDTOList.add(new UserDTO());
        return userDTOList;
    }

    @GetMapping("/name")
    public List<UserDTO> queryUserByName(@RequestParam String userName){

        System.out.println(userName);
        List<UserDTO> userDTOList = new ArrayList<>();
        userDTOList.add(new UserDTO());
        userDTOList.add(new UserDTO());
        userDTOList.add(new UserDTO());
        return userDTOList;
    }

    @GetMapping("/condition")
    @JsonView(UserDTO.UserSimpleView.class)
    public List<UserDTO> queryUserByCondition(@PageableDefault(page = 2, size = 17, sort = {"username"}, direction = Sort.Direction.DESC) Pageable pageable, UserQueryCondition userQueryCondition){

        log.info("pageable page: {}; size: {}; sort: {}; userQueryCondition: {}",pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort(), ReflectionToStringBuilder.toString(userQueryCondition, ToStringStyle.MULTI_LINE_STYLE));
        List<UserDTO> userDTOList = new ArrayList<>();
        userDTOList.add(new UserDTO());
        userDTOList.add(new UserDTO());
        userDTOList.add(new UserDTO());
        return userDTOList;
    }
    @GetMapping("/{id:\\d+}")
    @JsonView(UserDTO.UserSimpleView.class)
    public UserDTO getInfo(@PathVariable String id) {
//		throw new RuntimeException("user not exist");
        System.out.println("进入getInfo服务");
        UserDTO user = new UserDTO();
        user.setId(id);
        user.setUsername("tom");
        return user;
    }

    @PostMapping
    public UserDTO create(@Valid @RequestBody UserDTO user, BindingResult errors) {

        if (errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> log.info("error: {}",error.getDefaultMessage()));
        }

        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());

        user.setId("1");
        return user;
    }

    @PutMapping("/{id:\\d+}")
    public UserDTO update(@Valid @RequestBody UserDTO user, BindingResult errors) {

        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());

        user.setId("1");
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id) {
        System.out.println(id);
    }
}
