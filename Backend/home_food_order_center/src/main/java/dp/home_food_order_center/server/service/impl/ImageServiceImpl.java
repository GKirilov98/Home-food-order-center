package dp.home_food_order_center.server.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import dp.home_food_order_center.server.error.GlobalServiceException;
import dp.home_food_order_center.server.service.IImageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 3/29/2021
 */
@Service
public class ImageServiceImpl implements IImageService {
    private final Logger logger = LogManager.getLogger(ImageServiceImpl.class);

    private static final String API_KEY = "546934454118545";
    private static final String API_SECRET = "EYAqkSIRPYaNWhddbqOYRAShYQ4";
    private static final String CLOUD_NAME = "drb3wiwvh";

    @Override
    public Map<String, String > uploadImageCloudinary(MultipartFile image) throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        try {
            logger.info(String.format("%s: Starting uploadImageCloudinary service!", logId));

            File file = new File(image.getOriginalFilename());
            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                outputStream.write(image.getBytes());
            }

            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", CLOUD_NAME,
                    "api_key", API_KEY,
                    "api_secret", API_SECRET));
            Map upload = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            Map<String, String> resultMap = new HashMap<>();
            resultMap.put("public_id", upload.get("public_id").toString());
            resultMap.put("url", upload.get("url").toString());
            return resultMap;
        } catch (Exception e) {
            logger.error(String.format("%s: Unexpected uploadImageCloudinary service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected service error!");
        } finally {
            logger.info(String.format("%s: Finished uploadImageCloudinary service!", logId));
        }
    }

    @Async
    @Override
    public void deleteImageCloudinary(String publicId) throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        try {
            logger.info(String.format("%s: Starting deleteImageCloudinary service!", logId));
            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", CLOUD_NAME,
                    "api_key", API_KEY,
                    "api_secret", API_SECRET));
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());

        } catch (Exception e) {
            logger.error(String.format("%s: Unexpected deleteImageCloudinary service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected service error!");
        } finally {
            logger.info(String.format("%s: Finished deleteImageCloudinary service!", logId));
        }
    }
}
