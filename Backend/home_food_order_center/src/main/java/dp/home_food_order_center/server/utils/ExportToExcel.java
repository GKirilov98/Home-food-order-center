package dp.home_food_order_center.server.utils;

import dp.home_food_order_center.server.error.GlobalServiceException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 6/4/2021
 */
public class ExportToExcel {
    public static final String ROOT_EXPORT_PATH = "." + File.separator + "exportToExcel" + File.separator;
    public static final String TYPE_OF_ZIP = ".zip";
    public static final String TYPE_OF_EXCEL_FILE = ".xlsx";
    protected static final String REGEX_DECIMAL = "^[0-9]+[.][0-9]+$";
    protected final Logger logger = LogManager.getLogger(this.getClass());
    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;

    private int headerRow = -1;
    private XSSFWorkbook targetWorkbook = null;
    private XSSFSheet targetSheet = null;
    private final String logId;
    private Field[] fields;

    public ExportToExcel(String logId) {
        this.logId = logId;
    }

    private void closeXls() throws IOException {
        targetWorkbook.close();
        fields = null;
        headerRow = -1;
        targetWorkbook = null;
        targetSheet = null;
    }

    public File export(String[] fileNames, String[] titles, List<List<?>> data) throws GlobalServiceException {
        try {
            if (data.size() == 0 || data.get(0).size() == 0) {
                throw new Exception("Липсват данни!");
            }

            if (data.size() != fileNames.length || data.size() != titles.length) {
                throw new Exception("Няма съвпадение в данните");
            }

            String fullZipPath = String.format("%s%s_%s.%s", ROOT_EXPORT_PATH, fileNames[0], logId, TYPE_OF_ZIP);
            for (int i = 0; i < fileNames.length; i++) {
                String fileName = fileNames[i];
                String title = titles[i];
                List<?> datum = data.get(i);

                boolean firstIteration = true;
                for (Object e : datum) {
                    if (firstIteration) {
                        this.insertHeader(fileName, title, e);
                        firstIteration = false;
                    }

                    this.insertCell(e);
                }
                String fullPathExcel = String.format("%s%s%s%s%s", ROOT_EXPORT_PATH, logId, File.separator, fileName, TYPE_OF_EXCEL_FILE);
                this.createExcelFile(fullPathExcel, fileName);
            }


            this.createZipFile(fullZipPath);

            File notCompressedExcel = new File(ROOT_EXPORT_PATH + logId);
            for (File file : notCompressedExcel.listFiles()) {
                file.delete();
            }

            notCompressedExcel.delete();
            return new File(fullZipPath);


//
//            //deleting excel file
//            File notCompressedExcel = new File(fullPathExcel);
//            notCompressedExcel.delete();
//            notCompressedExcel.getParentFile().delete();
//
//            return new File(fullZipPath);
        } catch (GlobalServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new GlobalServiceException(logId, "Грешка при експорт на данни!", e.getMessage(), e);
        }
    }


    public File export(String fileName, String title, List<?> data) throws GlobalServiceException {
        try {
            if (data.size() == 0) {
                throw new Exception("Липсват данни!");
            }

            boolean firstIteration = true;
            for (Object e : data) {
                if (firstIteration) {
                    this.insertHeader(fileName, title, e);
                    firstIteration = false;
                }

                this.insertCell(e);
            }

            String fullPathExcel = String.format("%s%s%s%s%s", ROOT_EXPORT_PATH, logId, File.separator, fileName, TYPE_OF_EXCEL_FILE);
            String fullZipPath = String.format("%s%s_%s.%s", ROOT_EXPORT_PATH, fileName, logId, TYPE_OF_ZIP);

            String excelFilePath = this.createExcelFile(fullPathExcel, fileName);
//            this.createZipFile(excelFilePath, fullZipPath, fileName);

            //deleting excel file
            File notCompressedExcel = new File(fullPathExcel);
            notCompressedExcel.delete();
            notCompressedExcel.getParentFile().delete();

            return new File(fullZipPath);
        } catch (GlobalServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new GlobalServiceException(logId, "Грешка при експорт на данни!", e.getMessage(), e);
        }
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
     * @param fileName
     * @param title
     * @param entity
     * @param <E>
     * @throws GlobalServiceException
     */
    private <E> void insertHeader(String fileName, String title, E entity) throws GlobalServiceException {
        logger.info(String.format("%s: Start insertHeader method", logId));
        try {
            targetWorkbook = new XSSFWorkbook();
            targetSheet = creatNewSheet(targetWorkbook, fileName);
            targetSheet = targetWorkbook.getSheetAt(0);
        } catch (Exception e) {
            logger.error(String.format("%s: Error while create excel file or sheet.", logId), e);
            throw new GlobalServiceException(logId, "Грешка при създаване на ексел файла или при създаване на лист (sheet).",
                    "Error while create excel file or sheet.", e);
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
            throw new GlobalServiceException(logId, "Грешка при прочитане на параметрите na ентити класа.",
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
    private <E> void insertCell(E entity) throws GlobalServiceException {
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
     * @param fileName
     * @return
     * @throws GlobalServiceException
     */
    private String createExcelFile(String fullPathExcel, String fileName) throws GlobalServiceException {
        logger.info(String.format("%s: Starting createExcelFile method", logId));
        try {
            File file = new File(fullPathExcel);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }

            file.createNewFile();
        } catch (Exception e) {
            logger.error(String.format("%s: Error creating excel", logId), e);
            throw new GlobalServiceException(logId, "Грешка при прочитане и запис в ексел!", "Error creating excel", e);
        } finally {
            logger.info(String.format("%s: Finished createExcelFile method", logId));
        }

        try (FileOutputStream outputStream = new FileOutputStream(fullPathExcel)) {
            targetWorkbook.write(outputStream);
            this.closeXls();
        } catch (Exception e) {
            logger.error(String.format("%s: Error creating excel", logId), e);
            throw new GlobalServiceException(logId, "Грешка при прочитане и запис в ексел!", "Error creating excel", e);
        }

        return fullPathExcel;
    }

//    /**
//     * fillExcelFile
//     *
//     * @param fullPathExcel
//     * @throws GlobalServiceException
//     */
//    private void fillExcelFile(String fullPathExcel) throws GlobalServiceException {
//        logger.info(String.format("%s: Starting createExcelFile method", logId));
//        try (FileOutputStream outputStream = new FileOutputStream(fullPathExcel)) {
//            targetWorkbook.write(outputStream);
//            this.closeXls();
//        } catch (Exception e) {
//            logger.error(String.format("%s: Error creating excel", logId), e);
//            throw new GlobalServiceException(logId, "Грешка при прочитане и запис в ексел!", "Error creating excel", e);
//        } finally {
//            logger.info(String.format("%s: Finished createExcelFile method", logId));
//        }
//    }

    /**
     * createZipFile
     *
     * @param zipPath -
     * @throws GlobalServiceException
     */
    private void createZipFile(String zipPath) throws GlobalServiceException {
        try {
            logger.info(String.format("%s: Start createZipFile method", logId));
            FileOutputStream fileOutputStream = new FileOutputStream(zipPath);
            ZipOutputStream zipOut = new ZipOutputStream(fileOutputStream);
            String folder = ROOT_EXPORT_PATH + logId;

            for (File excelFilePath : Objects.requireNonNull(new File(folder).listFiles())) {
                String fileName = excelFilePath.getName();
                FileInputStream fileInputStream = new FileInputStream(excelFilePath);
                ZipEntry zipEntry = new ZipEntry(fileName + TYPE_OF_EXCEL_FILE);
                zipOut.putNextEntry(zipEntry);
                byte[] bytes = new byte[1024];
                int length;
                while ((length = fileInputStream.read(bytes)) != -1) {
                    zipOut.write(bytes, 0, length);
                }

                fileInputStream.close();
            }

            zipOut.close();
            fileOutputStream.close();

//            ZipOutputStream zipOut = new ZipOutputStream(fileOutputStream);
//            FileInputStream fileInputStream = new FileInputStream(excelFilePath);
//            ZipEntry zipEntry = new ZipEntry(fileName + TYPE_OF_EXCEL_FILE);
//            zipOut.putNextEntry(zipEntry);
//            byte[] bytes = new byte[1024];
//            int length;
//            while ((length = fileInputStream.read(bytes)) != -1) {
//                zipOut.write(bytes, 0, length);
//            }
//
//            fileInputStream.close();
//            zipOut.close();
//            fileOutputStream.close();

        } catch (Exception exc) {
            logger.error(String.format("%s: Unexpected error: %s", logId, exc.getMessage()), exc);
            throw new GlobalServiceException(logId, "Грешка при зип на данни !", String.format("Unexpected error: %s", exc.getMessage()), exc);
        } finally {
            logger.info(String.format("%s: Finished createZipFile method", logId));
        }

    }
}
