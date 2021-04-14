package dp.home_food_order_center.server.utils;

import dp.home_food_order_center.server.error.GlobalServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/12/2021
 */
public class ExportToExcelUtil {
    public static final String TYPE_OF_ZIP = ".zip";
    public static final String TYPE_OF_EXCEL_FILE = ".xlsx";
    protected static final String REGEX_DECIMAL = "^[0-9]+[.][0-9]+$";
    protected final Logger logger = LogManager.getLogger(this.getClass());
    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;

    private Field[] fields = null;
    private int headerRow = -1;
    private XSSFWorkbook targetWorkbook = null;
    private XSSFSheet targetSheet = null;
    private final String logId;

    public ExportToExcelUtil(String logId) {
        this.logId = logId;
    }

    private void closeXls() throws IOException {
        targetWorkbook.close();
        Field[] fields = null;
        int headerRow = -1;
        XSSFWorkbook targetWorkbook = null;
        XSSFSheet targetSheet = null;
    }

    /**
     * creatNewSheet
     *
     * @param excel
     * @param name
     * @return
     */
    private XSSFSheet creatNewSheet(XSSFWorkbook excel, String name) {
        return excel.createSheet(name);
    }

    /**
     * createHeaderRow
     *
     * @param targetWorkbook
     * @param sheet
     * @param headers
     * @param title
     * @return
     * @throws IOException
     */
    private int createHeaderRow(XSSFWorkbook targetWorkbook, XSSFSheet sheet, List<String> headers, String title) throws IOException {
        try {
            //Set Header Style
            int row = 0;
            int outRow = 1;
            XSSFCellStyle cellStyle = targetWorkbook.createCellStyle();
            cellStyle.setBorderBottom(BorderStyle.MEDIUM);
            cellStyle.setBorderTop(BorderStyle.MEDIUM);
            cellStyle.setBorderLeft(BorderStyle.MEDIUM);
            cellStyle.setBorderRight(BorderStyle.MEDIUM);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            if (!title.isEmpty()) {
                XSSFRow newRow = sheet.createRow(0);
                XSSFCell cell = newRow.createCell(0);
                cell.setCellValue(title);
                cell.setCellStyle(cellStyle);
                CellRangeAddress adr = new CellRangeAddress(0, 0, 0, headers.size() - 1);
                sheet.addMergedRegion(adr);
                row++;
                outRow += row;
            }

            // add headers
            XSSFRow newRow = sheet.createRow(row);
            for (int i = 0; i < headers.size(); i++) {
                XSSFCell cell = newRow.createCell(i);
                cell.setCellValue(headers.get(i));
                cell.setCellStyle(cellStyle);
            }
            for (int colNum = 0; colNum < headers.size(); colNum++)
                targetWorkbook.getSheetAt(0).autoSizeColumn(colNum);
            return outRow;
        } catch (Exception e) {
            throw new IOException("Грешка при създаване на имената на колоните в ексел файла!", e);
        }

    }

    /**
     * insertHeader
     *
     * @param reportName
     * @param title
     * @param entity
     * @param <E>
     * @throws GlobalServiceException
     */
    public <E> void insertHeader(String reportName, String title, E entity) throws GlobalServiceException {
        logger.info(String.format("%s: Start insertHeader method", logId));
        try {
            targetWorkbook = new XSSFWorkbook();
            targetSheet = creatNewSheet(targetWorkbook, reportName);
            targetSheet = targetWorkbook.getSheetAt(0);
        } catch (Exception e) {
            logger.error(String.format("%s: Error while create excel file or sheet.", logId), e);
            throw new GlobalServiceException(logId, "Грешка при създаване на ексел файла или при създаване на лист (sheet).",
                    " Error while create excel file or sheet.", e);
        }

        try {
            List<String> headers = new ArrayList<>();
            Field[] declaredFields = entity.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                headers.add(declaredField.getName());
            }

            this.fields = declaredFields;
            this.headerRow = createHeaderRow(targetWorkbook, targetSheet, headers, title);
        } catch (Exception e) {
            logger.error(String.format("%s: Error get entity properties", logId), e);
            throw new GlobalServiceException(logId,"Грешка при прочитане на параметрите na ентити класа.",
                   "Error get entity properties", e);
        }

        logger.info(String.format("%s: Finished insertHeader method", logId));
    }

    /**
     * insertCell
     *
     * @param entity
     * @param <E>
     * @throws GlobalServiceException
     */
    public <E> void insertCell(E entity) throws GlobalServiceException {
        try {
            // create new row
            XSSFRow row = targetSheet.createRow(headerRow);
            for (int i = 0; i < fields.length; i++) {
                Object valueObject = fields[i].get(entity);
                if (valueObject == null) {
                    valueObject = "";
                }

                String value = valueObject.toString();
                final XSSFCell newCell = row.createCell(i);
                try {
                    if (value.matches(REGEX_DECIMAL)) {
                        newCell.setCellValue(Double.parseDouble(value));
                    } else {
                        newCell.setCellValue(value);
                    }
                } catch (NumberFormatException e) {
                    newCell.setCellValue(value);
                }
            }

            this.headerRow += 1;
        } catch (Exception e) {
            logger.error(String.format("%s: Error inserting row", logId), e);
            throw new GlobalServiceException(logId, "Грешка при добавяне на редове.", "Error inserting row", e);
        }
    }

    /**
     * createExcelFile
     *
     * @param rootPath
     * @param fileName
     * @return
     * @throws GlobalServiceException
     */
    public String createExcelFile(String rootPath, String fileName) throws GlobalServiceException {
        logger.info(String.format("%s: Starting createExcelFile method", logId));
        String fullPathExcel = String.format("%s%s%s%s%s", rootPath, logId, File.separator, fileName, TYPE_OF_EXCEL_FILE);
        try {
            File file = new File(fullPathExcel);
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (Exception e) {
            logger.error(String.format("%s: Error creating excel", logId), e);
            throw new GlobalServiceException(logId, "Грешка при прочитане и запис в ексел!", "Error creating excel", e);
        } finally {
            logger.info(String.format("%s: Finished createExcelFile method", logId));
        }

        return fullPathExcel;
    }

    /**
     * fillExcelFile
     *
     * @param fullPathExcel
     * @throws GlobalServiceException
     */
    public void fillExcelFile(String fullPathExcel) throws GlobalServiceException {
        logger.info(String.format("%s: Starting createExcelFile method", logId));
        try (FileOutputStream outputStream = new FileOutputStream(fullPathExcel)) {
            targetWorkbook.write(outputStream);
            this.closeXls();
        } catch (Exception e) {
            logger.error(String.format("%s: Error creating excel", logId), e);
            throw new GlobalServiceException(logId, "Грешка при прочитане и запис в ексел!", "Error creating excel", e);
        } finally {
            logger.info(String.format("%s: Finished createExcelFile method", logId));
        }
    }

    /**
     * createZipFile
     *
     * @param excelFilePath
     * @param zipPath
     * @param fileName
     * @throws GlobalServiceException
     */
    public void createZipFile(String excelFilePath, String zipPath, String fileName) throws GlobalServiceException {
        try {
            logger.info(String.format("%s: Start createZipFile method", logId));
            FileOutputStream fileOutputStream = new FileOutputStream(zipPath);
            ZipOutputStream zipOut = new ZipOutputStream(fileOutputStream);
            FileInputStream fileInputStream = new FileInputStream(excelFilePath);
            ZipEntry zipEntry = new ZipEntry(fileName + TYPE_OF_EXCEL_FILE);
            zipOut.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fileInputStream.read(bytes)) != -1) {
                zipOut.write(bytes, 0, length);
            }

            fileInputStream.close();
            zipOut.close();
            fileOutputStream.close();

        } catch (Exception exc) {
            logger.error(String.format("%s: Unexpected error: %s", logId, exc.getMessage()), exc);
            throw new GlobalServiceException(logId, "Грешка при зип на данни !", "Unexpected error", exc);
        } finally {
            logger.info(String.format("%s: Finished createZipFile method", logId));
        }

    }

}
