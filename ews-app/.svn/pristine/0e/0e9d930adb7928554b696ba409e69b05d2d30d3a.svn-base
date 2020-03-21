package com.corp.oa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.exception.GeneralException;

public class Excel {

	private String filename;// 文件名

	private String filepath;// 路径

	private String title;// 标题

	private String field;// 字段

	private String maskField; // 需屏蔽的字段

	private int maskLength = 0; // 需屏蔽的尾部长度

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMaskField() {
		return maskField;
	}

	public void setMaskField(String maskField) {
		this.maskField = maskField;
	}

	public int getMaskLength() {
		return maskLength;
	}

	public void setMaskLength(int maskLength) {
		this.maskLength = maskLength;
	}

	// 返回当前目录下新生成的一个临时文件名，全路径
	private String setNewTemplFile() throws Exception {

		String todate = System.currentTimeMillis() + "";
		String fname = "";
		fname = FileUtil.getDocumentPath();

		// 当前日期
		String curDate = G.getCurrentDate().substring(0, 7).replaceAll("-", "");
		fname += curDate + "/";

		// 新建本地目录
		File dir = new File(fname);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		if (getFilepath() == null || "".equals(getFilepath()))
			setFilepath(fname);

		if (getFilename() == null || "".equals(getFilename()))
			setFilename(todate + ".xls");

		return getFilepath() + getFilename();

	}

	// 生成临时的xls文件，并返回全路径
	public String makeExcel(Collection<DataObject> coll) throws Exception {

		String titlename[] = title.split(",");// 标题分割
		String fieldname[] = field.split(",");// 字段分割
		for (int i = 0, j = 0; i < titlename.length && j < fieldname.length; i++, j++) {
			if ("".equals(titlename[i]) || titlename.length != fieldname.length
					|| "".equals(fieldname[j])) {
				throw new GeneralException("生成文件时，配置属性发生错误");
			}
		}

		String tempFile = "";
		if ((filepath == null || "".equals(filepath)) || filename == null
				|| "".equals(filename))
			tempFile = setNewTemplFile();
		else
			tempFile = filepath + filename;

		WritableWorkbook wwb = Workbook.createWorkbook(new File(tempFile));

		// 创建Excel工作表
		WritableSheet ws = wwb.createSheet("dataExported", 0);

		// 填加excel标题
		for (int i = 0; i < titlename.length; i++) {
			Label labelA = new jxl.write.Label(i, 0, titlename[i]);
			ws.addCell(labelA);
		}

		Iterator<DataObject> iter = coll.iterator();
		DataObject dob = null;
		Label label = null;
		int i = 1;// 控制行坐标

		// 字段的值添加到excel
		String temp = "";
		while (iter.hasNext()) {
			dob = iter.next();
			// j控制列坐标
			for (int j = 0; j < fieldname.length; j++) {
				temp = dob.getString(fieldname[j]);
				if (maskField != null && maskField.indexOf(fieldname[j]) >= 0) {
					temp = G.setStringMask(temp, this.maskLength);
				}
				label = new Label(j, i, temp);
				ws.addCell(label);
			}
			i++;
		}
		// 写入Exel工作表
		wwb.write();

		// 关闭Excel工作薄对象
		wwb.close();
		return tempFile;
	}

	@SuppressWarnings("resource")
	public void pushFileToDownload(HttpServletResponse rep) {

		// 下载excel文件
		try {
			String na = this.getFilename();
			String pa = this.getFilepath();

			String fileName = na.toString(); // 读到流中
			InputStream inStream = new FileInputStream(pa + na);
			// 设置输出的格式
			rep.setContentType("xls");
			rep.addHeader("Content-Disposition", "attachment; filename=\""
					+ fileName + "\"");
			// 循环取出流中的数据
			byte[] b = new byte[500];
			int len;
			while ((len = inStream.read(b)) > 0) {
				rep.getOutputStream().write(b, 0, len);
			}
			rep.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 读取指定的xls文件内容，默认是有列头显示在第一行的。必须事先传入field字段内容
	 * 
	 * @param xlsFile
	 *            xls文件的全路径+文件名
	 * @param headExist
	 *            是否有列头在第一行显示
	 * @return
	 */
	public ArrayList<DataObject> readExcelFile(String xlsFile, boolean headExist) {

		ArrayList<DataObject> al = null;

		try {
			WorkbookSettings workbookSettings = new WorkbookSettings();
			workbookSettings.setEncoding("ISO-8859-1");

			Workbook book = Workbook.getWorkbook(new File(xlsFile),
					workbookSettings);
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);

			int startCol = 0, maxCol = 0, maxRow = 0, startRow = 0;
			Cell cell = null;
			String result = "";

			if (getField() == null || "".equals(getField()))
				throw new GeneralException("生成文件时，配置属性发生错误");

			String[] xlsField = getField().split(",");
			maxCol = xlsField.length - 1;// 找到最大列
			int i = 0;
			try {
				for (i = 0; i <= 60000; i++) {// 找到最大行
					maxRow = i - 1;
					cell = sheet.getCell(0, i);
					result = cell.getContents();
					if (result == null || "".equals(result.trim())) {
						maxRow = i - 1;
						break;
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			if (headExist) // 有列头显示在文件中
				startRow = 1;

			DataObject dob = null;
			int idx = 0;

			al = new ArrayList<DataObject>();
			for (int k = startRow; k <= maxRow; k++) {
				dob = new DataObject();
				for (int j = startCol; j <= maxCol; j++) {
					cell = sheet.getCell(j, k);
					result =G.nvl(cell.getContents(),"");

					result = result.replaceAll("？", "");
					result = result.replaceAll("[?]", "");
					dob.setString(xlsField[j], result);
				}
				al.add(idx++, dob);
			}

			book.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return al;
	}

	/**
	 * 读取指定的xls文件内容，默认是有列头显示在第一行的。必须事先传入field字段内容
	 * 
	 * @param xlsFile
	 *            xls文件的全路径+文件名
	 * @return
	 */
	public ArrayList<DataObject> readExcelFile(String xlsFile) {
		return readExcelFile(xlsFile, true);
	}
}
