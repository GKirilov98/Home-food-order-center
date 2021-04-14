package dp.home_food_order_center.server.service;

import dp.home_food_order_center.server.error.GlobalServiceException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 3/29/2021
 */
public interface IImageService {
    /**
     * Upload image in cloudinary
     * @param file - image file
     * @return - public id and url of image
     * @throws GlobalServiceException
     */
    Map<String, String> uploadImageCloudinary( MultipartFile file) throws GlobalServiceException;

    /**
     *  delete image from cloudinary
     * @param publicId
     * @throws GlobalServiceException
     */
    void deleteImageCloudinary(String publicId) throws GlobalServiceException;
}
