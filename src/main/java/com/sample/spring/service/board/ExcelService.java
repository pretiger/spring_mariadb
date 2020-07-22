package com.sample.spring.service.board;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sample.spring.domain.board.Board;

@Service
public class ExcelService {
	
	private static final Logger logger=LoggerFactory.getLogger(ExcelService.class);
	
//    	HSSF : EXCEL 2007 이전 버전(.xls)에서 사용하는 방식
//    	XSSF : EXCEL 2007 이후 버전(2007포함 .xlsx)에서 사용하는 방식
//    	SXSSF : XSSF의 Streaming Version으로 메모리를 적게 사용하여 대용량 엑셀 다운로드에 주로 사용되는 방식
//    	SXSSF 방식->생성자 방식에서의 기본 flush 크기는 100 이며, -1 지정시 무제한이다. 
//    	쓰기전용이며 읽기는 불가능하다. 
    public SXSSFWorkbook excelFileDownloadBoard(List<Board> list) {
        SXSSFWorkbook workbook = new SXSSFWorkbook(100);
        // 시트 생성
        Sheet sheet = workbook.createSheet("Board_List");
        //시트 열 너비 설정
        sheet.setColumnWidth(0, 3000);
        sheet.setColumnWidth(0, 3000);
        sheet.setColumnWidth(0, 3000);
        sheet.setColumnWidth(0, 3000);
        sheet.setColumnWidth(0, 3000);
        sheet.setColumnWidth(0, 3000);
        // 헤더 행 생
        Row headerRow = sheet.createRow(0);
        // 해당 행의 첫번째 열 셀 생성
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Number");
        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("UserID");
        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Name");
        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("Subject");
        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("Content");
        headerCell = headerRow.createCell(5);
        headerCell.setCellValue("Writing Date");

        Row bodyRow = null;
        Cell bodyCell = null;
        for(int i=0; i<list.size(); i++) {
            Board board = list.get(i);
            bodyRow = sheet.createRow(i+1);
            bodyCell = bodyRow.createCell(0);
            bodyCell.setCellValue(board.getNum());
            bodyCell = bodyRow.createCell(1);
            bodyCell.setCellValue(board.getWriter());
            bodyCell = bodyRow.createCell(2);
            bodyCell.setCellValue(board.getName());
            bodyCell = bodyRow.createCell(3);
            bodyCell.setCellValue(board.getSubject());
            bodyCell = bodyRow.createCell(4);
            bodyCell.setCellValue(board.getContent());
            bodyCell = bodyRow.createCell(5);
            bodyCell.setCellValue(board.getRegdate().toString());
        }
//        서버의 특정 디렉토리에 파일로 생성 할 경우
//        FileOutputStream out;
//		try {
//			out = new FileOutputStream("e:/upload/board_list.xlsx");
//			workbook.write(out); 
//			workbook.dispose(); //디스크에 임시로 저장해 두었던 파일을 삭제하는 메소드
//			out.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
        
        return workbook;
    }
    
    public List<Board> uploadExcelBoard(MultipartFile excelFile){
    	List<Board> list=new ArrayList<>();
        try {
            OPCPackage opcPackage = OPCPackage.open(excelFile.getInputStream());
            XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
            // 첫번째 시트 불러오기
            Sheet sheet = workbook.getSheetAt(0);
            
            for(int i=1; i<sheet.getLastRowNum() + 1; i++) {
                Board dto = new Board();
                Row row = sheet.getRow(i);
                // 행이 존재하기 않으면 패스
                if(null == row) {
                    continue;
                }
                // 행의 두번째 열(이름부터 받아오기) 
                Cell cell = row.getCell(0);
                if(null != cell) dto.setWriter(cell.getStringCellValue());
                // 행의 세번째 열 받아오기
                cell = row.getCell(1);
                if(null != cell) dto.setSubject(cell.getStringCellValue());
                // 행의 네번째 열 받아오기
                cell = row.getCell(2);
                if(null != cell) dto.setContent(cell.getStringCellValue());
               
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
