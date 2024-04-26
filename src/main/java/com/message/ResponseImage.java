package com.message;

public class ResponseImage {
  private String id;
  private String name;
  private String url;
  private String type;
  private int duration;
  private long size; 

  public ResponseImage(String id, String name, String url, String type, int duration, long size) {
    this.id = id;
    this.name = name;
    this.url = url;
    this.type = type;
    this.duration = duration;
    this.size = size;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
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

  public void setDuration(int duration) {
    this.duration = duration;
  }
  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

}
