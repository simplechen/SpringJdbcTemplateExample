package me.laochen.dao;

import java.util.Date;
import java.util.List;

import me.laochen.po.DeviceInfo;

public interface DeviceInfoDao {
	public void add(DeviceInfo deviceInfo);
	public int addWithDevice(DeviceInfo deviceInfo);
	public List<DeviceInfo> findAll();
	
	public int count();
	
	public List<DeviceInfo> queryAllByAttr(DeviceInfo deviceInfo);
	
	public Date getCreatedAt(DeviceInfo deviceInfo);
	
	public int countWithImeiNo(final String imeiNo);
}
