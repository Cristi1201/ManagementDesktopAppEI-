package com.utm.msei.util;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ImageHandler {


    public static byte[] imageToByte() {
        return ImageHandler.imageToByte(null);
    }

    public static byte[] imageToByte(String imagePath) {
        try {
            if (imagePath != null) {
                if (imagePath.startsWith("file:/")) {
                    imagePath = imagePath.replace("file:/", "");
                }
                BufferedImage bImage = ImageIO.read(new File(imagePath));
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ImageIO.write(bImage, "jpg", bos);
                return bos.toByteArray();
            }
            BufferedImage bImage = ImageIO.read(new File("D:\\Univer\\Licenta\\Aplicatie\\MSEI\\MSEI\\src\\main\\resources\\com\\utm\\msei\\images\\defaultPersonImage.jpg"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            return bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static WritableImage byteToImage(byte[] byteImg) {
        try {
//        ByteArrayInputStream inStreambj = new ByteArrayInputStream(byteImg);
//        return ImageIO.read(inStreambj);

            ByteArrayInputStream in = new ByteArrayInputStream(byteImg);
            BufferedImage bImage = ImageIO.read(in);
            return SwingFXUtils.toFXImage(bImage, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
