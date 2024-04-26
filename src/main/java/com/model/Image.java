package com.model;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "images")
public class Image {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  private Date uploadDate;
  private Time uploadTime;

  private String name;

  private String type;

  private int duration; 

  private byte[] data;

  public Image() {
  }

  public Image(String name, String type, int duration, byte[] data) {
    this.name = name;
    this.type = type;
    this.data = data;
    this.duration = duration;
    this.uploadDate = new Date();
    this.uploadTime = new Time(uploadDate.getTime());
  }

  public String getId() {
    return id;
  }
  
  public Date getUploadDate() {
    return uploadDate;
  }

  public void setUploadDate(Date uploadDate) {
    this.uploadDate = uploadDate;
  }

  public Time getUploadTime() {
    return uploadTime;
  }

  public void setUploadedTime(Time uploadTime) {
    this.uploadTime = uploadTime;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getDuration() {
    return duration;
  }

  public void setDutation(int duration) {
    this.duration = duration;
  }

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }

}
