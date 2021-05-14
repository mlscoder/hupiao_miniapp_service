import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class Excel {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Administrator\\Desktop\\zheng.xlsx");
        String path = "C:\\Users\\Administrator\\Desktop\\zheng\\";
        System.out.println("start---");

        try {
            FileInputStream in = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(in);
            Sheet sheet = wb.getSheetAt(0); //取得“测试.xlsx”中的第一个表单
            int firstRowNum = sheet.getFirstRowNum();
            int lastRowNum = sheet.getLastRowNum();

            Row row = null;
            Cell cell_a = null;
            Cell cell_b = null;
            int count = 0;
            for (int i = firstRowNum + 1; i <= lastRowNum; i++) {


                row = sheet.getRow(i); //取得第i行 （从第二行开始取，因为第一行是表头）
                if (row != null) {
                    row.getCell(0).setCellType(CellType.STRING);//取得i行的第1列 
                    cell_a = row.getCell(0);
                    String cellValue = null;
                    if (cell_a != null) {
                        cellValue = cell_a.getStringCellValue().replace(" ", "");
                    }

                    //System.out.println(cellValue)
                    String cell_bvalue = null;
                    if (row.getCell(1) != null) {
                        row.getCell(1).setCellType(CellType.STRING);
                        cell_b = row.getCell(1);//取得i行的第2列 

                        if (cell_b != null) {
                            cell_bvalue = cell_b.getStringCellValue().replace(" ", "");
                        }
                    }

                    String name = path + (count++) + ".txt";
                    writeTxt(name, cellValue + cell_bvalue);
                }

            }
            System.out.println("done---");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void writeTxt(String txtPath, String content) {
        OutputStreamWriter oStreamWriter = null;
        File file = new File(txtPath);
        try {
            if (file.exists()) {
                //判断文件是否存在，如果不存在就新建一个txt
                file.createNewFile();
            }

            oStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
            oStreamWriter.append(content);
            oStreamWriter.flush();
            oStreamWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
