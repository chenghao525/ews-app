package com.corp.showImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;

public class ShowImageServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8721334041567115535L;

	// 根目录
	private String rootPath = null;

	// 没有图片时的默认图片路径
	private String defaultImage = null;

	//
	// 读取内容写入输出流
	//
	private void readAndWrite(InputStream is, OutputStream os)
			throws IOException {
		int bufSize = 1024, bytes = 0;
		byte[] buffer = new byte[bufSize];
		while ((bytes = is.read(buffer, 0, bufSize)) > 0) {
			os.write(buffer, 0, bytes);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		InputStream is = null;
		OutputStream os = null;
		DataManager dm = null;
		try {
			dm = DataManager.getInstance();

			String filename = "";

			// 传入的人员编号
			String empid = request.getParameter("s");

			String sql = "";

			sql = "select t.path from t_info_image t "
					+ " where t.state='1' and t.info_type='expressemployee' "
					+ "and t.sid=(select sid from t_info_express_employee a "
					+ " where a.state='1' and a.empid='" + empid + "')";
			DataObject dob = dm.findByPrimaryKey(sql, null);
			if (dob != null) {
				filename = dob.getString("PATH");
			}

			if (filename == null || "".equals(filename)) {
				filename = defaultImage;
			} else {
				filename = filename.replace("/", "\\");
				filename = rootPath + filename.trim();
			}

			response.setContentType("image/jpeg");

			is = new FileInputStream(new File(filename));
			os = response.getOutputStream();
			readAndWrite(is, os);

		} catch (Exception e) {
		} finally {
			if (is != null)
				is.close();
			if (os != null)
				os.close();
			try {
				if (dm != null) {
					dm.close();
				}
			} catch (Exception e) {
			}
		}
	}

	//
	// 取webxml中根目录和默认图片的配置
	//
	@Override
	public void init() throws ServletException {
		rootPath = getServletConfig().getInitParameter("rootPath");
		defaultImage = getServletConfig().getInitParameter("defaultImage");
		if (rootPath == null || defaultImage == null)
			throw new ServletException(
					"must config for rootPath and defaultImage!");
		rootPath = rootPath.trim();
		defaultImage = defaultImage.trim();
	}
}