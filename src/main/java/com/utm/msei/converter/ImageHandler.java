package com.utm.msei.converter;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ImageHandler {


    public static byte[] imageToByte() {
        try {
            return ImageHandler.imageToByte(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] imageToByte(String imagePath) throws IOException {
        if (imagePath != null) {
            BufferedImage bImage = ImageIO.read(new File(imagePath));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            return bos.toByteArray();
        }
//        BufferedImage bImage = ImageIO.read(new File("../resources/com/utm/msei/images/defaultPersonImage.jpg"));
        BufferedImage bImage = ImageIO.read(new File("D:\\Univer\\Licenta\\Aplicatie\\MSEI\\MSEI\\src\\main\\resources\\com\\utm\\msei\\images\\defaultPersonImage.jpg"));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos);
        return bos.toByteArray();
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
