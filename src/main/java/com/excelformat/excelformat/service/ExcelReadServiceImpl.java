package com.excelformat.excelformat.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadServiceImpl {

	public String sendCsv() {
		// TODO Auto-generated method stub
		FileInputStream file=null;
		XSSFWorkbook workbook=null;
		Sheet datatypeSheet=null;
		String data[][] = null;
		 StringBuffer sb=new StringBuffer();
		try {
			file=new FileInputStream(new File("D:\\ExcelFiles\\Book.xlsx"));
			System.out.println("hello...........");
			 workbook= new XSSFWorkbook(file);
			 datatypeSheet = workbook.getSheetAt(0);
			 /*
			 int rows=datatypeSheet.getLastRowNum();
			 int numberOfCells = -1;
			 Iterator<Row> iterator = datatypeSheet.iterator();
			 if(iterator.hasNext()){
				 Row headerRow = (Row) iterator.next();
				 numberOfCells = headerRow.getPhysicalNumberOfCells();
			 }
			 //int numberOfCells = headerRow.getPhysicalNumberOfCells();
			 
			 System.out.println("rows are    "+rows+" numberOfCells "+numberOfCells );
			 
			 String consol="";
			//iterating all rows
			 int ss=0;
			 for(int columnNum=0;columnNum<numberOfCells;columnNum++){
				 StringBuffer sb=new StringBuffer();
				 for(int rowNum=1;rowNum <= rows;rowNum++){
				 
				 Cell cell=datatypeSheet.getRow(rowNum).getCell(columnNum);
				 if(cell.getCellTypeEnum() == CellType.STRING){
					// System.out.print(cell.getStringCellValue() + "\n");
				   sb.append(cell.getStringCellValue()+",");
					 //ss++;
					 if(ss==4){
						 System.exit(0);
					 }
				 }
				 else if(cell.getCellTypeEnum() == CellType.NUMERIC){
					 //System.out.print(cell.getNumericCellValue() + "\n");
					 sb.append(cell.getNumericCellValue()+",");
				 }
				 sb.append("\t");
				// System.out.println();
			 }
			 consol=consol.concat(sb.toString());
			 }
			 System.out.println(consol);
			 System.exit(0);*/
			 int numberOfCells =-1;
			 Iterator<Row> iterator = datatypeSheet.iterator();
			 if(iterator.hasNext()){
				 Row headerRow = (Row) iterator.next();
				 numberOfCells = headerRow.getPhysicalNumberOfCells();
			 }
//			  headerRow.getPhysicalNumberOfCells();
//maintain column count
			
			 while (iterator.hasNext()){
				 Row currentRow=iterator.next();
				 int col=0;
				Iterator<Cell> cellIterator=currentRow.iterator();
				 int datecell=0;
				 float negquantity=0;
				 while (cellIterator.hasNext()){
					 
					 Cell currentCell = cellIterator.next();
					 
					 if (currentCell.getCellTypeEnum() == CellType.STRING) {
						 switch(col){
						 case 0:System.out.print(currentCell.getStringCellValue() + ",,");
						 		sb.append(currentCell.getStringCellValue()+",,");
						 			break;
						 default:System.out.print(currentCell.getStringCellValue() + ",");
						 sb.append(currentCell.getStringCellValue()+",");break;
						 }
	                        
	                       
	                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
	                    	switch(datecell){
	                    	case 2:SimpleDateFormat sfd=new SimpleDateFormat("yyyy/MM/dd");
	                    			
	                    		System.out.print(sfd.format(currentCell.getDateCellValue()).replaceAll("/", "")+ ",");
	                    		sb.append(sfd.format(currentCell.getDateCellValue()).replaceAll("/", "")+",");
	                    		break;
	                    	case 3:System.out.print((int)currentCell.getNumericCellValue() + ",,,");
	                    			sb.append((int)currentCell.getNumericCellValue()+",,,");
	                    		negquantity=(int)currentCell.getNumericCellValue();break;
	                    	case 4:System.out.print((int)currentCell.getNumericCellValue() + ",,,,,");
	                    	sb.append((int)currentCell.getNumericCellValue() + ",,,,,");break;
	                    	default:System.out.print((int)currentCell.getNumericCellValue() + ",");
	                    	sb.append((int)currentCell.getNumericCellValue() + ",");break;
	                    	}
	                    	
	                    	 datecell++;       
	                    }
					 
					 col++;
					
				 }
				 negquantity=0-negquantity;
              	System.out.printf("%.4f",negquantity);
              	sb.append(negquantity+"000");
              	sb.append("\n");
				 System.out.println();
			 }
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

return sb.toString();
	}

}
