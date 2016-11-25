package com.jege.spring.boot.jdbc.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jege.spring.boot.jdbc.dao.IUserDao;
import com.jege.spring.boot.jdbc.entity.User;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:jdbc CRUD
 */
@Repository
public class UserDaoImpl implements IUserDao {
  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public void dropTable() {
    jdbcTemplate.update("drop table t_user if exists");
  }

  @Override
  public void createTable() {
    jdbcTemplate.update(
	"create table t_user (id bigint generated by default as identity, age integer, name varchar(255), primary key (id))");
  }

  @Override
  public void save(User user) {
    jdbcTemplate.update("insert into t_user(name,age) values(?,?)", user.getName(), user.getAge());
  }

  @Override
  public List<User> findAll() {
    return jdbcTemplate.query("select id,name,age from t_user", BeanPropertyRowMapper.newInstance(User.class));
  }

  @Override
  public void deleteAll() {
    jdbcTemplate.update("delete from t_user");
  }

  @Override
  public List<User> findByNameLike(String name) {
    return jdbcTemplate.query("select id,name,age from t_user where name like ?", new Object[] { name },
	BeanPropertyRowMapper.newInstance(User.class));
  }

}
