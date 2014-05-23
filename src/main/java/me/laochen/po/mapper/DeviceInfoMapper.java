package me.laochen.po.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.laochen.po.DeviceInfo;

import org.springframework.jdbc.core.RowMapper;


public class DeviceInfoMapper implements RowMapper<Object> {

	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		DeviceInfo deviceInfo = new DeviceInfo();
//		deviceInfo.setCreated_at(rs.getDate("created_at"));
		deviceInfo.setId(rs.getInt("id"));
		deviceInfo.setImei_no(rs.getString("imei_no"));
		deviceInfo.setVersion(rs.getString("version"));	
		return deviceInfo;
	}
}
