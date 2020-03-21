package com.corp.monitor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.corp.basic.Constant;
import com.corp.basic.SystemInfoConstant;
import com.corp.oa.util.G;

public class SystemMonitorServlet extends HttpServlet {
	private static final long serialVersionUID = -8556925580014043705L;
	protected final Log logger = LogFactory.getLog(getClass());

	@Override
	public void init() throws ServletException {

		// 通用系统线程
		Thread threadComm = new Thread(new SystemCommonMonitor());
		threadComm.start();

		// 如果当前的server不是允许执行thread的server，退出
		String mThreadServer = Constant.getThreadRunServer();
		String hostName = G.getLocalHostName();
		if (!hostName.equalsIgnoreCase(mThreadServer)) {
			logger.info("当前主机" + hostName + "不被允许监控线程.");
			return;
		}

		Thread thread = new Thread(new SystemMonitor());
		thread.start();

		Thread threadLower = new Thread(new SystemLowerMonitor());
		threadLower.start();
	}

}

class SystemCommonMonitor implements Runnable {

	protected final Log logger = LogFactory.getLog(getClass());
	// 3小时循环一次
	final int sleepMinute = 180;

	@Override
	public void run() {
		while (true) {
			try {

				Thread.sleep(sleepMinute * 60 * 1000);

				// ************************************
				// 此处的操作放在sleep后进行操作

				// 因为前面进入系统已经执行过一次，因此放在sleep后执行，避免同一时间点重复执行
				// 重置消息类型的键值对应关系
				SystemInfoConstant.refreshSysKey();

				// ************************************

			} catch (Exception e) {
				e.printStackTrace();
				Thread.currentThread().start();
			}
		}
	}
}

// 系统高频操作的监控
class SystemMonitor implements Runnable {

	protected final Log logger = LogFactory.getLog(getClass());

	private final int sleepMinute = 5;

	@Override
	public void run() {

		while (true) {
			try {

				Thread.sleep(sleepMinute * 60 * 1000);

			} catch (Exception e) {
				e.printStackTrace();
				Thread.currentThread().start();
			}
		}
	}

}

// 系统低频操作的监控
class SystemLowerMonitor implements Runnable {

	protected final Log logger = LogFactory.getLog(getClass());

	// 60分钟循环一次
	private final int sleepMinute = 60;

	@Override
	public void run() {

		while (true) {
			try {

				Thread.sleep(sleepMinute * 60 * 1000);

			} catch (Exception e) {
				e.printStackTrace();
				Thread.currentThread().start();
			}
		}
	}

}
