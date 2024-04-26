## Introduction

This project allow to maintain a list of image URLs and play a slideshow.
The project based on Spring Boot, Angular, Postgres technology stack.
This part of project is backend project based on Java/Spring Boot.
Project expose set of APIs, which should be confused by project client APP.

## Features

List of key features or functionalities of the project.

- Add Image URL
- Upload Image
- Show List of Images
- Delete Image
- Download image
- Present images in slideshow

## Prerequisites

- Java v8+
- Spring Boot v3.x+
- Postgres v10+

## Installation & Execution

1. install prerequisites
2. download sources to repository
3. Create Postgres DB & Schema
4. update application.properties file with data base connection details
5. run it by mvn spring-boot:runs command
 
## Configuration

Update application.properties with Postgres DB details
spring.datasource.url=  Postgres Connection
spring.datasource.username= Postgres User
spring.datasource.password= Postgres 

## API Endpoints

- /addImage: Add a new image URL, including proposed duration.
- /uploadImage: Upload a new image, including proposed duration.
- /deleteImage/{id}: Delete an existing image URL.
- /images: Retrieve a list of image URLs and their durations.