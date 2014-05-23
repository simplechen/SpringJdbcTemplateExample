package me.laochen.po;

import java.io.Serializable;
import java.util.Date;

public class DeviceInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8077359701940875901L;
	
	
	public Integer id;
	public String imei_no;
	public String version;
	public Date created_at;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImei_no() {
		return imei_no;
	}
	public void setImei_no(String imei_no) {
		this.imei_no = imei_no;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	
	
	@Override
	public String toString() {
		return "DeviceInfo [id=" + id + ", imei_no=" + imei_no + ", version="
				+ version + ", created_at=" + created_at + "]";
	}

}
