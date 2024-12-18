package com.example.perfectNumber;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public List<QueryReturn> query() {
    	String sql = "SELECT id, log FROM audit_logs";
    	
    	 return jdbcTemplate.query(sql, queryRowMapper);
    }
    
    public int save(String log) {
        String sql = "INSERT INTO audit_logs (log) VALUES (?)";
        return jdbcTemplate.update(sql, log);
    }
	
    // Definir um RowMapper para mapear o resultado da consulta para a classe Cliente
    private RowMapper<QueryReturn> queryRowMapper = (rs, rowNum) -> {
    	QueryReturn queryReturn = new QueryReturn();
    	queryReturn.setId(rs.getInt("id"));
    	queryReturn.setLog(rs.getString("log"));

        
        return queryReturn;
    };    
    
}
