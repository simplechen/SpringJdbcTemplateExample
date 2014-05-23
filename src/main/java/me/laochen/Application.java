package me.laochen;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import me.laochen.dao.DeviceDao;
import me.laochen.dao.DeviceInfoDao;
import me.laochen.po.DeviceInfo;

public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DeviceDao dao2 = new DeviceDao();
		dao2.todo();
		System.err.println("=---sdafasdj");
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		DeviceInfoDao dao =(DeviceInfoDao) ctx.getBean("deviceInfoDao");
		
		DeviceInfo device = new DeviceInfo();
		device.setImei_no("a----slkfasklf");
//		device.setVersion("2.2");
//		device.setCreated_at(new Date());
//		dao.addWithDevice(device);
//		dao.add(device);
		
//		List<DeviceInfo> ds = dao.findAll();
		List<DeviceInfo> ds = dao.queryAllByAttr(device);
//		
		for (DeviceInfo deviceInfo : ds) {
			System.err.println(deviceInfo);
		}
		
		device.setImei_no("0001");
		Date date = dao.getCreatedAt(device);
		System.err.println(date);
		
		System.err.println("------------------------");
		
		int num = dao.countWithImeiNo("a----slkfasklf");
		System.err.println("num:"+num);
	}
}
