package nju.lighting.presentation.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Created on 2017/12/9.
 * Description
 * @author 陈俊宇
 */
public class ImageViewHelper {


    public static ImageView delete() {
        ImageView delete = new ImageView("/images/垃圾桶.png");
        unifySize(delete, 20, 20);
        return delete;
    }


    private static void unifySize(ImageView imageView, double width, double height) {
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
    }
}
