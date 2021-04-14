package dp.home_food_order_center.server.data.view.image;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 3/30/2021
 */
public class ReqImageViewDemo implements Serializable {
    private MultipartFile[] file;

    public ReqImageViewDemo() {
    }

    public MultipartFile[] getFile() {
        return file;
    }

    public void setFile(MultipartFile[] file) {
        this.file = file;
    }
}
