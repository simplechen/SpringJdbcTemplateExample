package me.laochen.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.laochen.dao.DeviceInfoDao;
import me.laochen.po.DeviceInfo;
import me.laochen.po.mapper.DeviceInfoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository("deviceInfoDao")
public class DeviceInfoDaoImpl implements DeviceInfoDao {
//	final Logger logger = LoggerFactory.getLogger(DeviceInfoDaoImpl.class);
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public final static String FINDALL = "select * from device_info where 1";
	public final static String INSERT = "insert into device_info(`imei_no`,`version`,created_at) values(:imei_no,:version,:created_at)";

	public void add(DeviceInfo deviceInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("imei_no", deviceInfo.getImei_no());
		params.put("version", deviceInfo.getVersion());
		params.put("created_at", new Date());
		namedParameterJdbcTemplate.update(INSERT, params);
	}

	/**
	 * 添加资料并返回主键
	 */
	public int addWithDevice(DeviceInfo deviceInfo) {

		SqlParameterSource ps = new BeanPropertySqlParameterSource(deviceInfo);
		KeyHolder keyholder = new GeneratedKeyHolder();

		namedParameterJdbcTemplate.update(INSERT, ps, keyholder);

		// 加上KeyHolder这个参数可以得到添加后主键的值
		int m = keyholder.getKey().intValue();
		return m;
	}

	/**
	 * 自定义 DeviceInfoMapper 使用
	 */
	@SuppressWarnings("unchecked")
	public List<DeviceInfo> findAll() {
		/**
		 * 传入参数映射中的值存储在双字节数组中，顺序与SQL字符串中的位置参数相同。
		 * query()方法以包含Map（用列名作为键，一项对应一列）的List（一项对应一行）的方式返回查询结果
		 */
		// daoTmplt = getNamedParameterJdbcTemplate();
		List<DeviceInfo> deviceInfos = null;

		deviceInfos = namedParameterJdbcTemplate.query(FINDALL, new HashMap(),new DeviceInfoMapper());

		return deviceInfos;
	}

	public int count() {
		return namedParameterJdbcTemplate.getJdbcOperations().queryForInt(
				FINDALL);
	}

	/**
	 * BeanPropertyRowMapper 使用 BeanPropertySqlParameterSource 使用
	 */
	public List<DeviceInfo> queryAllByAttr(DeviceInfo deviceInfo) {
//		logger.info("param device:"+deviceInfo);
		String sql = "select * from device_info where imei_no=:imei_no";
		SqlParameterSource ps = new BeanPropertySqlParameterSource(deviceInfo);
		return namedParameterJdbcTemplate.query(sql, ps,
				new BeanPropertyRowMapper<DeviceInfo>(DeviceInfo.class));
	}

	/**
	 * 使用BeanPropertySqlParameterSource
	 */
	public Date getCreatedAt(DeviceInfo deviceInfo) {
		String sql = "select created_at from device_info where imei_no=:imei_no";
		SqlParameterSource ps = new BeanPropertySqlParameterSource(deviceInfo);
		return namedParameterJdbcTemplate.queryForObject(sql, ps, Date.class);
	}

	/**
	 * 使用 MapSqlParameterSource
	 */
	public int countWithImeiNo(String imeiNo) {
		String sql = "select count(*) as num from device_info where imei_no=:imei_no";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("imei_no", imeiNo);
		return namedParameterJdbcTemplate.queryForInt(sql,params);
	}
}
