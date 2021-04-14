package dp.home_food_order_center.server.controller;

import dp.home_food_order_center.server.data.view.image.ReqImageView;
import dp.home_food_order_center.server.data.view.image.ReqImageViewDemo;
import dp.home_food_order_center.server.error.GlobalServiceException;
import dp.home_food_order_center.server.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Timed;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 3/29/2021
 */
@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private IImageService imageService;

    /**
     * Upload image in cloudinary
     * @param file - image file
     * @return - public id and url of image
     * @throws GlobalServiceException
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImage(@ModelAttribute MultipartFile file) throws GlobalServiceException {
        Map<String, String> paramsImg = imageService.uploadImageCloudinary(file);
        return ResponseEntity.ok().body(paramsImg);
    }

    /**
     * Delete image (Async method)
     * @param imageView
     * @throws GlobalServiceException
     */
    @PostMapping("/delete")
    public void deleteImage(@RequestBody ReqImageView imageView) throws GlobalServiceException {
        imageService.deleteImageCloudinary(imageView.getImgPath());
    }
}
