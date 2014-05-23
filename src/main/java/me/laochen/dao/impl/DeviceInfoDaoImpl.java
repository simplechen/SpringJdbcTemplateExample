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
	 * ������ϲ���������
	 */
	public int addWithDevice(DeviceInfo deviceInfo) {

		SqlParameterSource ps = new BeanPropertySqlParameterSource(deviceInfo);
		KeyHolder keyholder = new GeneratedKeyHolder();

		namedParameterJdbcTemplate.update(INSERT, ps, keyholder);

		// ����KeyHolder����������Եõ���Ӻ�������ֵ
		int m = keyholder.getKey().intValue();
		return m;
	}

	/**
	 * �Զ��� DeviceInfoMapper ʹ��
	 */
	@SuppressWarnings("unchecked")
	public List<DeviceInfo> findAll() {
		/**
		 * �������ӳ���е�ֵ�洢��˫�ֽ������У�˳����SQL�ַ����е�λ�ò�����ͬ��
		 * query()�����԰���Map����������Ϊ����һ���Ӧһ�У���List��һ���Ӧһ�У��ķ�ʽ���ز�ѯ���
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
	 * BeanPropertyRowMapper ʹ�� BeanPropertySqlParameterSource ʹ��
	 */
	public List<DeviceInfo> queryAllByAttr(DeviceInfo deviceInfo) {
//		logger.info("param device:"+deviceInfo);
		String sql = "select * from device_info where imei_no=:imei_no";
		SqlParameterSource ps = new BeanPropertySqlParameterSource(deviceInfo);
		return namedParameterJdbcTemplate.query(sql, ps,
				new BeanPropertyRowMapper<DeviceInfo>(DeviceInfo.class));
	}

	/**
	 * ʹ��BeanPropertySqlParameterSource
	 */
	public Date getCreatedAt(DeviceInfo deviceInfo) {
		String sql = "select created_at from device_info where imei_no=:imei_no";
		SqlParameterSource ps = new BeanPropertySqlParameterSource(deviceInfo);
		return namedParameterJdbcTemplate.queryForObject(sql, ps, Date.class);
	}

	/**
	 * ʹ�� MapSqlParameterSource
	 */
	public int countWithImeiNo(String imeiNo) {
		String sql = "select count(*) as num from device_info where imei_no=:imei_no";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("imei_no", imeiNo);
		return namedParameterJdbcTemplate.queryForInt(sql,params);
	}
}
