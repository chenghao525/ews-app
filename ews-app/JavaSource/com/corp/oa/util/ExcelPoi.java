package com.corp.oa.util;

import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.exception.GeneralException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author wqj
 */
public class ExcelPoi {

    private static String EXCEL_XLS = "xls";
    private static String EXCEL_XLSX = "xlsx";

    private String filename;// �ļ���

    private String filepath;// ·��

    private String title;
    private String fields;

    private String maskField; // �����ε��ֶ�

    private int maskLength = 0; // �����ε�β������

    private String sheetName; // sheet����

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return this.filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getFields() {
        return this.fields;
    }
    public void setFields(String fields) {
        this.fields = fields;
    }

    public int getMaskLength() {
        return this.maskLength;
    }

    public void setMaskLength(int maskLength) {
        this.maskLength = maskLength;
    }

    public String getMaskField() {
        return this.maskField;
    }

    public void setMaskField(String maskField) {
        this.maskField = maskField;
    }

    public String getSheetName() {
        if (G.isNvl(this.sheetName)) {
            return "sheet";
        }
        return this.sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    /**
     * ����xlsx��ʽ�ļ�
     * @param coll ����
     * @return
     * @throws Exception
     */
    public String makeXlsxFile(Collection<DataObject> coll) throws Exception {

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(getSheetName());

        return makeFile(wb, sheet, coll, ".xlsx");
    }

    /**
     * ����xls��ʽ�ļ�
     * @param coll ����
     * @return
     * @throws Exception
     */
    public String makeXlsFile(Collection<DataObject> coll) throws Exception {

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(getSheetName());

        return makeFile(wb, sheet, coll, ".xls");
    }

    public void pushFileToDownload(HttpServletResponse rep) {
        // ����excel�ļ�
        OutputStream out = null;
        try {
            String file = this.getFilename();
            String path = this.getFilepath();

            String fileName = file.toString(); // ��������
            InputStream inStream = new FileInputStream(path + file);
            // ��ȡ�ļ��������ʽ
            String type = file.endsWith(EXCEL_XLSX) ? "xlsx" : "xls";

            // ��������ĸ�ʽ
            rep.setContentType(type);
            rep.addHeader("Content-Disposition", "attachment; filename=\""
                    + fileName + "\"");

            out = rep.getOutputStream();
            // ѭ��ȡ�����е�����
            byte[] b = new byte[500];
            int len;
            while ((len = inStream.read(b)) > 0) {
                rep.getOutputStream().write(b, 0, len);
            }

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ʵ���ļ�
    private String makeFile(Workbook wb, Sheet sheet, Collection<DataObject> coll, String extName) throws Exception {

        String[] titleList = getTitle().split(",");
        String[] fieldList = getFields().split(",");
        isAllowedRules(titleList, fieldList);
        // ��ӱ���б���
        addSheetRowTitle(sheet, titleList);

        DataObject dob = null;

        Iterator<DataObject> it = coll.iterator();
        int index = 1;
        while (it.hasNext()) {
            dob = it.next();
            createRowAndCells(sheet, index, dob, fieldList);
            index++;
        }
        String fileAddr = workbookWriteToFile(wb, extName);

        wb.close();

        return fileAddr;
    }

    // ���ļ�д�뵽����
    private String workbookWriteToFile(Workbook wkk, String extName) throws Exception {

        String tempFile = "";
        if ((filepath == null || "".equals(filepath)) || filename == null
                || "".equals(filename)) {
            tempFile = setNewTemplFile(extName);
        } else {
            tempFile = filepath + filename;
        }
        File file = new File(tempFile);
        OutputStream out = new FileOutputStream(file);

        wkk.write(out);
        out.close();
        return tempFile;
    }

    private void isAllowedRules(String[] titleList, String[] fieldList) throws Exception {

        if (titleList.length != fieldList.length) {
            throw new GeneralException("�����ļ�ʱ���������Է�������");
        }
        for (int i = 0, j = 0; i < titleList.length && j < fieldList.length; i++, j++) {
            if ("".equals(titleList[i]) || "".equals(fieldList[i])) {
                throw new GeneralException("�����ļ�ʱ���������Է�������");
            }
        }
    }

    private void addSheetRowTitle(Sheet sheet, String[] titleList) {

        Row row = sheet.createRow(0);
        Cell cell = null;
        int idx = 0;
        for (String title: titleList) {
            cell = row.createCell(idx);
            cell.setCellValue(title);
            idx++;
        }
    }

    /**
     * ����row
     * @param sheet
     * @param rowIdx row����
     * @param dob ������
     */
    private void createRowAndCells(Sheet sheet, int rowIdx, DataObject dob, String[] fieldList) {
        Row row = sheet.createRow(rowIdx);
        Cell cell = null;

        String temp = null;
        int idx = 0;
        for (String key: fieldList) {
            cell = row.createCell(idx);
            temp = dob.getString(key);
            // ����
            if (maskField != null && maskField.indexOf(key) >= 0) {
                temp = G.setStringMask(temp, this.maskLength);
            }
            cell.setCellValue(temp);
            idx++;
        }
    }
    // ���ص�ǰĿ¼�������ɵ�һ����ʱ�ļ�����ȫ·��
    private String setNewTemplFile(String extensionName) throws Exception {

        String todate = System.currentTimeMillis() + "";
        String fname = "";
        fname = FileUtil.getDocumentPath();

        // ��ǰ����
        String curDate = G.getCurrentDate().substring(0, 7).replaceAll("-", "");
        fname += curDate + "/";

        // �½�����Ŀ¼
        File dir = new File(fname);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        if (getFilepath() == null || "".equals(getFilepath()))
            setFilepath(fname);

        if (getFilename() == null || "".equals(getFilename())) {
            if (extensionName == null || "".equals(extensionName)) {
                setFilename(todate + ".xlsx");
            } else {
                setFilename(todate + extensionName);
            }
        }

        return getFilepath() + getFilename();
    }

    public ArrayList<DataObject> readExcelFile(String fileString) {
        if (G.isNvl(fileString)) {
            return null;
        }

        try {
            File file = new File(fileString);
            FileInputStream in = new FileInputStream(file);
            checkExcelValid(file);
            Workbook wkk = getWorkBook(in, file);
            // ��ȡ��һ��sheet
            Sheet sheet = wkk.getSheetAt(0);
            String[] excelFields = getFields().split(",");
            return readOneSheet(sheet, excelFields);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * ��ȡ�ж��sheet���ļ�
     * @param fileString
     * @return
     * @throws Exception
     */
    public HashMap<String, ArrayList<DataObject>> readExcelFileOfSheets(String fileString) {

        if (G.isNvl(fileString)) {
            return null;
        }
        try {
            File file = new File(fileString);
            FileInputStream in = new FileInputStream(file);
            checkExcelValid(file);
            Workbook wkk = getWorkBook(in, file);
            // ��ȡsheet��������
            int sheetCount = wkk.getNumberOfSheets();

            HashMap<String, ArrayList<DataObject>> map =
                    new HashMap<String, ArrayList<DataObject>>();

            String[] excelFields = getFields().split(",");

            // ��ȡsheet
            Sheet sheet = null;
            String sheetName = null;
            for (int i = 0; i < sheetCount; i++) {
                sheet = wkk.getSheetAt(i);
                sheetName = sheet.getSheetName();
                map.put(sheetName, readOneSheet(sheet, excelFields));
            }

            return map;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private ArrayList<DataObject> readOneSheet(Sheet sheet, String[] excelFields) {
        int count = 0;

//        int maxCellCount = 0;
        Cell cell = null;
        String value = null;

        ArrayList<DataObject> ja = new ArrayList<DataObject>();
        DataObject dob = null;
        for (Row row: sheet) {
            // ������һ�� �����ǿ���
            if (count == 0 || isEmptyRow(row)) {
                count++;
                continue;
            }

            // ��ȡ�е���������
//            maxCellCount = row.getPhysicalNumberOfCells();

            dob = new DataObject();
            // ֻ������ǰ�����ֶεĳ���
            for (int i = 0; i < excelFields.length; i++) {
                cell = row.getCell(i);

                value = getValue(cell);
                value = value.replaceAll("��", "");
                value = value.replaceAll("[?]", "");
                dob.setString(excelFields[i], value);
            }
            count++;

            ja.add(dob);
        }

        return ja;
    }
    private String getValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        CellType type = cell.getCellTypeEnum();

        if (CellType.NUMERIC == type) {
            String dtValue = checkDate(cell);
            if (dtValue != null && !"".equals(dtValue)) {
                return dtValue;
            }
            double cur= cell.getNumericCellValue();
            long longVal = Math.round(cur);
            Object inputValue = null;
            if(Double.parseDouble(longVal + ".0") == cur) {
                inputValue = longVal;
            } else {
                inputValue = cur;
            }

            return String.valueOf(inputValue);

        }  else if (CellType.BLANK == type || CellType.ERROR == type){
            return "";
        }  else if (CellType.FORMULA == type) {
//            String formula = cell.getCellFormula();
//
//            System.out.println(formula);
            return String.valueOf(cell.getNumericCellValue());
        } else {
            return String.valueOf(cell.getStringCellValue());
        }

    }
    private String  checkDate(Cell cell) {
        String dfStr = cell.getCellStyle().getDataFormatString();
        if ("m/d/yy".equals(dfStr) || "yyyy/mm;@".equals(dfStr) ||
                "yy/m/d".equals(dfStr) || "mm/dd/yy".equals(dfStr) ||
                "yyyy/m/d".equals(dfStr) || "dd-mmm-yy".equals(dfStr)) {
            return new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
        } else if ("h:mm".equals(dfStr) || "hh:mm".equals(dfStr)) {
            return new SimpleDateFormat("HH:mm").format(cell.getDateCellValue());
        } else {
//            System.out.println("δ�����ʽ: " + dfStr);
            return "";
        }
    }

    private boolean isEmptyRow(Row row) {

        Cell cell = null;
        boolean isEmpty = true;
        for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
            cell = row.getCell(i);
            if (!G.isNvl(getValue(cell))) {
                isEmpty = false;
                break;
            }
        }

        return isEmpty;
    }


    private void checkExcelValid(File file) throws Exception {
        if (!file.exists()) {
            throw new FileNotFoundException("Excel�ļ�������");
        }
        if (!(file.isFile() && (file.getName().endsWith(EXCEL_XLS) ||
            file.getName().endsWith(EXCEL_XLSX)))) {
            throw new FileFormatException("��Excel�ļ�����xls����xlsx��ʽ");
        }
    }
    // ���ݲ�ͬ��excel��ʽ��ȡ��ͬworkbook
    private Workbook getWorkBook(InputStream in, File file) throws Exception {
        Workbook wb = null;
        if (file.getName().endsWith(EXCEL_XLS)) {
            wb = new HSSFWorkbook(in);
        } else if (file.getName().endsWith(EXCEL_XLSX)) {
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

//    public static void main(String[] args) throws Exception {
//
//        ExcelPoi excel = new ExcelPoi();
//
//        excel.setFilename("kkk.xlsx");
//        excel.setFilepath("/Users/mac01/Desktop/shell/");
//
//        String names = "ҳ��,��ִ,����";
//        String fields = "page,guzhi,test";
//
//        ArrayList<DataObject> list = new ArrayList<DataObject>();
//
//        DataObject dob = new DataObject();
//        dob.setString("page", "1");
//        dob.setString("guzhi", "����");
//        dob.setString("test", "good");
//
//        list.add(dob);
//
//        dob = new DataObject();
//        dob.setString("page", "2");
//        dob.setString("guzhi", "����1");
//        dob.setString("test", "good1");
//
//        list.add(dob);
//
//        excel.setTitle(names);
//        excel.setFields(fields);
//
//        ArrayList<DataObject> list1 = excel.readExcelFile("/Users/mac01/Desktop/shell/kkk.xlsx");
//        System.out.println(list1.toString());
//
//    }
}
