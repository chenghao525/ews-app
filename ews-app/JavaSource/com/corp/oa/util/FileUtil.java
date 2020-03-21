package com.corp.oa.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import com.corp.frame.bean.JSONArray;
import com.corp.frame.core.base.SystemProperty;
import com.corp.frame.core.dao.DataManager;

public class FileUtil {
	public static final String UPLOAD_FILE_SAVE_PATH = "uploadFileSavePath";

	public static String getDocumentPath() {
		return getPath(UPLOAD_FILE_SAVE_PATH);
	}

	/**
	 * 得到系统配置的上传文件保存路径
	 * 
	 * @return String
	 */
	public static String getPath(String loadPath) {
		String path = SystemProperty.getProperty(loadPath);
		if (path == null || "".equals(path)) {
			throw new java.lang.IllegalStateException("上传文件的保存路径没有配置。" + loadPath + "=?");
		}

		File file = new File(path);
		if (!file.isDirectory()) {
			file.mkdirs();
		}
		return path;
	}

	/**
	 * 将文移到当前年月的文件夹,并返回相对路径
	 * 
	 * @param fileName
	 *            String
	 */
	public static String move(String fileName, String loadPath) {
		String path = getPath(loadPath);

		// 返回年月的文件路径字符串
		String abstractPath = getAbstractPath(path);

		File file = new File(path + fileName);
		if (file.isFile()) {
			InputStream is = null;
			OutputStream out = null;
			try {
				is = new FileInputStream(file);
				path = path + abstractPath + fileName;
				// System.out.println(path);
				File toFile = new File(path);
				if (toFile.exists()) {
					toFile.delete();
				}
				toFile.createNewFile();
				out = new FileOutputStream(toFile);
				byte[] b = new byte[256];
				for (int length = 0; (length = is.read(b, 0, 256)) > 0;) {
					out.write(b, 0, length);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					if (is != null) {
						is.close();
					}
				} catch (IOException ex1) {
				}
				try {
					if (out != null) {
						out.close();
					}
				} catch (IOException ex2) {
				}
			}
			file.delete();
		}
		return abstractPath;
	}

	/**
	 * 将文件在同一个目录下复制一份 fileName 原来文件名 path 文件的路径 cFileName 复制后的文件名
	 */
	public static void copy(String fileName, String cFileName, String path) {
		// 原文件
		File file = new File(path + fileName);
		if (file.isFile()) {
			InputStream is = null;
			OutputStream out = null;
			try {
				// 原文件的一个输入流
				is = new FileInputStream(file);
				// 复制文件的路径
				File toFile = new File(path + cFileName);
				if (toFile.exists()) {
					toFile.delete();
				}
				// 创建复制文件
				toFile.createNewFile();
				// 复制文件的输出流
				out = new FileOutputStream(toFile);
				// 复制
				byte[] b = new byte[256];
				for (int length = 0; (length = is.read(b, 0, 256)) > 0;) {
					out.write(b, 0, length);

				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					if (is != null) {
						is.close();
					}
				} catch (IOException ex1) {
				}
				try {
					if (out != null) {
						out.close();
					}
				} catch (IOException ex2) {
				}
			}
			// file.delete();
		}
		// return abstractPath;
	}

	/**
	 * 将文件在同一个目录下复制一份 fileName 原来文件名 path 文件的路径 cFileName 复制后的文件名
	 */
	public static void copy(String fullFileName, String fullDesFileName) {
		// 原文件
		File file = new File(fullFileName);
		if (file.isFile()) {
			InputStream is = null;
			OutputStream out = null;
			try {
				// 原文件的一个输入流
				is = new FileInputStream(file);
				// 复制文件的路径
				File toFile = new File(fullDesFileName);

				// 新建本地目录
				File dir = new File(fullDesFileName);
				dir = new File(dir.getParent());
				if (!dir.exists()) {
					dir.mkdirs();
				}

				if (toFile.exists()) {
					toFile.delete();
				}
				// 创建复制文件
				toFile.createNewFile();
				// 复制文件的输出流
				out = new FileOutputStream(toFile);
				// 复制
				byte[] b = new byte[256];
				for (int length = 0; (length = is.read(b, 0, 256)) > 0;) {
					out.write(b, 0, length);

				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					if (is != null) {
						is.close();
					}
				} catch (IOException ex1) {
				}
				try {
					if (out != null) {
						out.close();
					}
				} catch (IOException ex2) {
				}
			}
			// file.delete();
		}
		// return abstractPath;
	}

	/*
	 * 返回年月的文件路径字符串
	 */
	private static String getAbstractPath(String path) {
		Calendar cl = Calendar.getInstance();
		int year = cl.get(Calendar.YEAR);
		int month = cl.get(Calendar.MONTH);
		StringBuffer buffer = new StringBuffer();
		buffer.append(year);
		buffer.append("/");
		month++;
		if (month < 10) {
			buffer.append(0);
		}
		buffer.append(month);
		buffer.append("/");
		File renameTo = new File(path + buffer.toString());
		if (!renameTo.isDirectory()) {
			renameTo.mkdirs();
		}
		return buffer.toString();
	}

	/*
	 * 
	 * 返回值 删除后附件的总数
	 * 
	 * 参数说明 al 所有附件的信息 str 待删除的记录 num 每条记录包含的元素个数
	 */
	@SuppressWarnings("rawtypes")
	public static ArrayList getAfterDel(ArrayList al, String[] str, int num) {
		int temp, temp1, temp2;

		for (int i = str.length - 1; i >= 0; i--) {
			temp = Integer.parseInt(str[i]);
			temp1 = (temp - 1) * num;
			temp2 = temp * num - 1;
			for (int j = temp2; j >= temp1; j--) {
				if (j < al.size())
					al.remove(j);
			}
		}
		return al;
	}

	// 获得附件的列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList getFjList(String sid) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList result = new ArrayList();
		String strSql = "select * from  t_docs_fj where state='1' and parent_sid=" + sid;
		try {
			conn = DataManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(strSql);
			while (rs.next()) {
				result.add(rs.getString("File_Name"));
				result.add(rs.getString("Ext_Name"));
				result.add("" + Float.valueOf(rs.getString("File_Size")).floatValue() * 1000.0);
				result.add(rs.getString("SID"));
				result.add(FileUtil.getDocumentPath());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static String getCurrentPath() throws Exception {
		File directory = new File("");// 设定为当前文件夹
		return directory.getAbsolutePath();// 获取绝对路径
	}

	/**
	 * 将字节内容存为文件
	 * 
	 * @param filename
	 * @param b
	 */
	public static void saveFile(String filename, String content) throws RuntimeException {

		File file = new File(filename);
		File dir = new File(file.getParent());
		if (!dir.exists()) {
			dir.mkdirs();
		}

		try {
			FileOutputStream fos = new FileOutputStream(filename);
			Writer out = new OutputStreamWriter(fos, "utf-8");
			out.write(content);
			out.close();
			fos.close();

		} catch (FileNotFoundException e) {
			throw new RuntimeException("文件[" + filename + "]保存失败", e);
		} catch (IOException e) {
			throw new RuntimeException("文件[" + filename + "]保存失败", e);
		}
	}

	/**
	 * 读取文本文件，使用系统默认字符集
	 * 
	 * @param file
	 * @return
	 */
	public static String readTextFile(String file) {
		return readTextFile(file, Charset.defaultCharset().toString(), false);
	}

	public static String readTextFile(String file, String charset) {
		return readTextFile(file, charset, false);
	}

	/**
	 * 读取文本文件，指定字符集
	 * 
	 * @param file
	 * @param charset
	 * @return
	 */
	public static String readTextFile(String file, String charset, boolean asLine) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),
					charset));
			StringBuffer sb = new StringBuffer();
			String stmp = null;
			while ((stmp = br.readLine()) != null) {
				sb.append(stmp);
				if (!asLine) {
					sb.append("\r\n");
				}
			}
			br.close();
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			System.out.println("read file exception:" + e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println("read file exception:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("read file exception:" + e.getMessage());
		}
		return null;
	}

	public static JSONArray getJaFromDataTxt(String txtName) {
		try {
			String js = (FileUtil.readTextFile(txtName, "utf-8", true));
			if (!js.startsWith("[")) {
				js = js.substring(1);
			}
			org.json.JSONArray ja = null;
			ja = new org.json.JSONArray(js);

			JSONArray newJa = new JSONArray(ja.toString());

			return newJa;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static void main(String args[]) {

	}
}
