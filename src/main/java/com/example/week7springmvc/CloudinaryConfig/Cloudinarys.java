package com.example.week7springmvc.CloudinaryConfig;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Cloudinarys {
    public String cloud(String name, MultipartFile file) {
        Map config = new HashMap();
        config.put("cloud_name", "dryoxxqql");
        config.put("api_key", "655831578736855");
        config.put("api_secret", "TIsvKvJ-f7xVmuIP_vrtDJj3OVc");
        Cloudinary cloudinary = new Cloudinary(config);


// Upload
        try {
            cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("public_id", name));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        String url = cloudinary.url().transformation(new Transformation().width(250).height(250).crop("fill")).generate(name);
        System.out.println(url);
        return url;
    }
}