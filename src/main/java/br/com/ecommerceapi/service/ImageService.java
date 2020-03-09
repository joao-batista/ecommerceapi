package br.com.ecommerceapi.service;

import br.com.ecommerceapi.entity.User;
import br.com.ecommerceapi.exception.FileStorageException;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.filters.Canvas;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@Service
public class ImageService {

    public byte[] processImage(MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {

            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            ByteArrayOutputStream os = new ByteArrayOutputStream();

            BufferedImage bufferedImage =
            Thumbnails.of(file.getInputStream())
                    .forceSize(500, 500)
                    .allowOverwrite(true)
                    .outputFormat("jpg")
                    .asBufferedImage();

            ImageIO.write(bufferedImage, "jpg", os);
            //byte[] bytes = Base64.getEncoder().encode(os.toByteArray());
            byte[] bytes = os.toByteArray();
            //byte[] bytes = new byte[inputStream.available()];

            return bytes;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
}
