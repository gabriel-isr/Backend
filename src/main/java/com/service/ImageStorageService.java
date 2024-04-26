package com.service;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.model.Image;
import com.repository.ImageRepository;  

@Service
public class ImageStorageService {
  public final String IMG_URL_TYPE = "URL";

  @Autowired
  private ImageRepository imageRepository;

  public Image store(MultipartFile img, String duration) throws IOException {
    String imgName = StringUtils.cleanPath(img.getOriginalFilename());
    if(isImageNameExist(imgName)) {
      System.out.println("Image File with same name already exist: "+imgName);
      return null;
    }
    Image image = new Image(imgName, img.getContentType(),Integer.valueOf(duration), img.getBytes());
    System.out.println("Requet to Store Image File: "+imgName);
    return imageRepository.save(image);
  }
  
  public Image store(String imageUrl, String duration) throws IOException {
    Image image = new Image(imageUrl, IMG_URL_TYPE,Integer.valueOf(duration), null);
    if(isImageNameExist(imageUrl)) {
      System.out.println("Image with same URL already exist: "+imageUrl);
      return null;
    }
    System.out.println("Requet to Store Image URL: "+imageUrl);
    return imageRepository.save(image);
  }

  public Image getImage(String id) {
    return imageRepository.findById(id).get();
  }
  
  public Stream<Image> getAllImages() {
    return imageRepository.findAll().stream();
  }

  public void deleteImage(String id) {
    imageRepository.deleteById(id);
  }

  public boolean isUrlType(String type) {
    if(type == null || type.equals(IMG_URL_TYPE)) 
      return true;
    return false;
  }
  public boolean isImageNameExist (String name){
    if (imageRepository.findByName(name) != null) 
      return true;
    return false;
  }
}
