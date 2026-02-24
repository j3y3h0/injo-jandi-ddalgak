// ExcelProcessor.java
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Apache POI 라이브러리를 사용하여 엑셀 파일을 읽고 쓰는 기능을 제공합니다.
 * .xlsx 형식의 파일을 처리합니다.
 */
public class ExcelProcessor {

    /**
     * 지정된 엑셀 파일과 시트 이름에서 데이터를 읽어옵니다.
     * 각 행은 List<String>으로, 전체 데이터는 List<List<String>>으로 반환됩니다.
     * 모든 셀은 문자열로 변환됩니다.
     *
     * @param filePath 엑셀 파일의 경로
     * @param sheetName 데이터를 읽을 시트의 이름
     * @return 엑셀 시트의 모든 데이터를 담은 List<List<String>>
     * @throws IOException 파일 읽기 중 오류 발생 시
     */
    public List<List<String>> readExcel(String filePath, String sheetName) throws IOException {
        List<List<String>> data = new ArrayList<>();
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis); // .xlsx 형식 워크북

        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            workbook.close();
            fis.close();
            throw new IOException("시트를 찾을 수 없습니다: " + sheetName);
        }

        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            List<String> rowData = new ArrayList<>();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                // 셀 타입을 문자열로 변환하여 처리
                switch (cell.getCellType()) {
                    case STRING:
                        rowData.add(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            rowData.add(cell.getDateCellValue().toString());
                        } else {
                            rowData.add(String.valueOf(cell.getNumericCellValue()));
                        }
                        break;
                    case BOOLEAN:
                        rowData.add(String.valueOf(cell.getBooleanCellValue()));
                        break;
                    case FORMULA:
                        // 수식 셀은 수식 결과값을 가져오도록 설정
                        rowData.add(cell.getCellFormula()); // 실제 사용 시 FormulaEvaluator로 평가해야 합니다.
                        break;
                    case BLANK:
                        rowData.add("");
                        break;
                    default:
                        rowData.add("");
                }
            }
            data.add(rowData);
        }

        workbook.close();
        fis.close();
        return data;
    }

    /**
     * 데이터를 새 엑셀 파일에 작성합니다.
     *
     * @param filePath 저장할 엑셀 파일의 경로
     * @param sheetName 데이터를 작성할 시트의 이름
     * @param data 엑셀에 작성할 데이터 (List<List<String>>)
     * @throws IOException 파일 쓰기 중 오류 발생 시
     */
    public void writeExcel(String filePath, String sheetName, List<List<String>> data) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);

        int rowNum = 0;
        for (List<String> rowData : data) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (String cellData : rowData) {
                Cell cell = row.createCell(colNum++);
                cell.setCellValue(cellData);
            }
        }

        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        workbook.close();
        fos.close();
    }
}
