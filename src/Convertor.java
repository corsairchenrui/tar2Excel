import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;


public class Convertor {
	public static final void convertZip(File ZipFile,HSSFWorkbook workbook){
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws ZipException {
		// TODO Auto-generated method stub
		try {
			ZipFile file = new ZipFile(new File("E:/INTTRACEOUT1120140213.zip"));
			String outputFile = "E:/workbook.xls";
			BufferedInputStream bis = new BufferedInputStream(file.getInputStream(file.getEntry("INTTRACEOUT1120140213")));
			FileOutputStream fos;
			byte buffer[] = new byte[1024];
			int length = 0;
			StringBuffer sb = new StringBuffer();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while((length = bis.read(buffer)) > 0){
				baos.write(buffer, 0, length);
				baos.flush();
				
			}
			sb.append(baos.toString());
			String[] lines = sb.toString().split("\\n");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("bla");
			for(int i=0;i<lines.length;i++){
				HSSFRow row = sheet.createRow(i);
				String[] rowData = lines[i].split("!");
				for(int j=0;j<rowData.length;j++){
					HSSFCell cell = row.createCell(j, Cell.CELL_TYPE_STRING);
					cell.setCellValue(rowData[j]);
				}
			}
			wb.write(fos = new FileOutputStream(outputFile));
			fos.close();
			baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
