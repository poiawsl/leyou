package com.leyou.service;

import com.leyou.common.Exceptions.LyException;
import com.leyou.common.eEnum.ExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Service
@Slf4j
public class UploadService {

    /*
     *设置允许上传的图片格式
     */
    private static final List<String> allowTypes = Arrays.asList("image/jpeg","image/png","image/bmp");

    /**
     * 文件上传
     * @param file
     * @return
     */
    public String uploadImage(MultipartFile file) {
        try {
            //1. 文件格式校验  file.getContentType()
            String contentType = file.getContentType();
            //allowTypes 寻找file.getContentType()格式
            if(!allowTypes.contains(contentType)){
                throw new LyException(ExceptionEnum.UPLOAD_TYPE_ERROR);
            }
            //2. 校验图片内容
            BufferedImage image = ImageIO.read(file.getInputStream());
            if(image == null){
                throw  new LyException(ExceptionEnum.UPLOAD_TYPE_ERROR);
            }
            //3.准备上传路径
            File dist = new File("F:\\Gallery photo\\"+file.getOriginalFilename());
            //4.实现上传
            file.transferTo(dist);
            return "http://image.leyou.com/"+file.getOriginalFilename();
        } catch (IOException e){
            log.error("文件上传失败",e);
            throw new LyException(ExceptionEnum.UPLOAD_ERROR);
        }

    }
}
