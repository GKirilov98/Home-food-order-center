package dp.home_food_order_center.server.utils;

import dp.home_food_order_center.server.error.GlobalServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.File;
import java.nio.file.Files;
/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 6/4/2021
 */
public class FileToHttpEntity {
    protected static final Logger logger = LogManager.getLogger(FileToHttpEntity.class);

    /**
     * Конвертира файла във HttpEntity
     *
     * @param file
     * @return
     * @throws GlobalServiceException
     */
    public static HttpEntity convert(File file) throws GlobalServiceException {
        if (file == null) {
            logger.error("The file is null!");
            throw new GlobalServiceException(null, "ZIP файла не е създаден! Подаден е null на конвертора.", "The file is null!");
        }

        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            header.set("Content-Disposition", "inline; filename=" + file.getName());
            header.setContentLength(fileContent.length);
            return new HttpEntity<>(fileContent, header);
        } catch (Exception e) {
            logger.error("Can not convert file to HttpEntity!", e);
            throw new GlobalServiceException(null, "Грешка при конвертиране на File в HttpEntity", "Unexpected error while convert File into HttpEntity", e);
        }
    }
}
