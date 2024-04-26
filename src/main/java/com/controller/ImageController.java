package com.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.service.ImageStorageService;
import com.message.ResponseImage;
import com.message.ResponseMessage;
import com.model.Image; 

@Controller
@CrossOrigin("http://localhost:4200")
public class ImageController {

  @Autowired
  private ImageStorageService storageService;

  @PostMapping("/uploadImage")
  public ResponseEntity<ResponseMessage> uploadImage(@RequestParam("image") MultipartFile img,@RequestParam("duration") String duration) {
    String message = "";
    try {
      Image storedImage = storageService.store(img,duration);
      if(storedImage != null){
        message = "Imaged Uploaded Successfully!";
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
      }else{
        message = "Image with same name already exist!";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
      }      
    } catch (Exception e) {
      message = "Could not upload the image!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }
  @PostMapping("/addImage")
  public ResponseEntity<ResponseMessage> addImage(@RequestParam("imageurl") String imageUrl, @RequestParam("duration") String duration) {
    String message = "";
    try {
      Image storedImage = storageService.store(imageUrl,duration);
      if(storedImage != null){
        message = "Imaged URL saved Successfully!";
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
      }else{
        message = "Image with same URL already exist!";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
      } 
    } catch (Exception e) {
      message = "Could not add the image Url!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }

  @GetMapping("/images")
  public ResponseEntity<List<ResponseImage>> getAllImages() {
    List<ResponseImage> images = storageService.getAllImages().map(dbEntity -> {
      String downloadUri = dbEntity.getName();
      long imgSize = 0;
      if(!storageService.isUrlType(dbEntity.getType())){
         downloadUri = ServletUriComponentsBuilder
          .fromCurrentContextPath()
          .path("/images/")
          .path(dbEntity.getId())
          .toUriString();
          imgSize = dbEntity.getData().length;
      }      

      return new ResponseImage(
        dbEntity.getId(),
        dbEntity.getName(),
          downloadUri,
          dbEntity.getType(),
          dbEntity.getDuration(),
          imgSize);
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(images);
  }

  @GetMapping("/images/{id}")
  public ResponseEntity<byte[]> getImage(@PathVariable String id) {
    Image image = storageService.getImage(id);

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
        .body(image.getData());
  }

  @DeleteMapping("/deleteImage/{id}")
  public ResponseEntity<ResponseMessage> deleteImage(@PathVariable String id) {
    String message = "";
    try {
      storageService.deleteImage(id);

      message = "Image deleted successfully!";
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Could not delete the image!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }
}
