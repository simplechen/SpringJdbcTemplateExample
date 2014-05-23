package me.laochen.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class AbstractDao {
	@Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
}
