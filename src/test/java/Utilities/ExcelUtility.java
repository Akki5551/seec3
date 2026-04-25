package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility
{
    public FileInputStream fis;
    public XSSFWorkbook wb;
    public XSSFSheet sh;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    public FileOutputStream fos;
    public String path;

    public ExcelUtility(String path)
    {
        this.path = path;
    }

    public int getrowcount(String sheetname) throws IOException
    {
        fis = new FileInputStream(path);
        wb = new XSSFWorkbook(fis);
        sh = wb.getSheet(sheetname);
        int rows = sh.getLastRowNum();
        wb.close();
        fis.close();
        return rows;
    }

    public int getcellcount(String sheetname, int rownum) throws IOException
    {
        fis = new FileInputStream(path);
        wb = new XSSFWorkbook(fis);
        sh = wb.getSheet(sheetname);
        row = sh.getRow(rownum);
        int cells = row.getLastCellNum();
        wb.close();
        fis.close();
        return cells;
    }
    
    public String getcelldata(String sheetname, int rownum, int cellnum) throws IOException
    {
        fis = new FileInputStream(path);
        wb = new XSSFWorkbook(fis);
        sh = wb.getSheet(sheetname);
        row = sh.getRow(rownum);
        cell = row.getCell(cellnum);

        String data;

        try
        {
            DataFormatter df = new DataFormatter();
            data = df.formatCellValue(cell);
        }
        catch (Exception e)
        {
            data = "";
        }

        wb.close();
        fis.close();
        return data;
    }

    public void setcelldata(String sheetname, int rownum, int cellnum, String cellvalue) throws IOException
    {
        File fl = new File(path);

        if (!fl.exists())
        {
            wb = new XSSFWorkbook();
            fos = new FileOutputStream(path);
            wb.write(fos);
        }

        fis = new FileInputStream(path);
        wb = new XSSFWorkbook(fis);
        sh = wb.getSheet(sheetname);

        if (sh == null)
        {
             wb.createSheet(sheetname);
        }

       sh= wb.getSheet(sheetname);
       
        row = sh.getRow(rownum);
        
        if (row == null)
        {
            row = sh.createRow(rownum);
        }

        cell = row.getCell(cellnum);
        if (cell == null)
        {
            cell = row.createCell(cellnum);
        }

        cell.setCellValue(cellvalue);

        fos = new FileOutputStream(path);
        wb.write(fos);
        wb.close();
        fis.close();
        fos.close();
    }
    
    
    public void setgreencolor(String filepath, String sheetname, int rownum, int cellnum) throws IOException
    {
        fis = new FileInputStream(filepath);
        wb = new XSSFWorkbook(fis);

        sh = wb.getSheet(sheetname);

        row = sh.getRow(rownum);

        cell = row.getCell(cellnum);

        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);

        fos = new FileOutputStream(filepath);
        wb.write(fos);

        wb.close();
        fis.close();
        fos.close();
    }

    public void setredcolor(String filepath, String sheetname, int rownum, int cellnum) throws IOException
    {
        fis = new FileInputStream(filepath);
        wb = new XSSFWorkbook(fis);

        sh = wb.getSheet(sheetname);

        row = sh.getRow(rownum);

        cell = row.getCell(cellnum);

        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);

        fos = new FileOutputStream(filepath);
        wb.write(fos);

        wb.close();
        fis.close();
        fos.close();
    }
    
}