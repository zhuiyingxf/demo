package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.example.pojo.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@ResponseBody
@Controller
    public class democontroller {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    //设置映射路径为/save，即外部访问路径
        @RequestMapping("/save")
        //设置当前操作返回结果为指定json数据（本质上是一个字符串信息）
        @ResponseBody
        public String save() {
            System.out.println("user save ...");
            return "{'info':'springmvc'}";

        }

        @RequestMapping("/jdbctest")
        public List<User>  jdbctest() {
            List<User> query = jdbcTemplate.query("select id,username as name,address from users", new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = new User();
                    user.setId(rs.getString("id"));
                    user.setName(rs.getString("name"));
                    return user;
                }
            }, null);
            return query;
        }

    }
