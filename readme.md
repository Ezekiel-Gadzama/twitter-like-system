# Twitter-Like System

A simple distributed service-based system that simulates a Twitter-like application where users can post short messages, read them, and like them. This system is implemented using Java Servlets, following service-based architectural principles.

## Table of Contents
- [Overview](#overview)
- [Business Requirements](#business-requirements)
- [Architecture](#architecture)
- [Services](#services)
- [API Endpoints](#api-endpoints)
  - [User Service](#user-service)
  - [Message Service](#message-service)
  - [Like Service](#like-service)
- [How to Run the Project](#how-to-run-the-project)
- [Testing the Endpoints](#testing-the-endpoints)
  - [Using Postman](#using-postman)
  - [Using curl](#using-curl)

## Overview

This project is a part of the Software Architecture course assignment to implement a service-based system as described in the "Fundamentals of Software Architecture" (FOSA) book. The Twitter-like system allows users to:
- Register with a username (no passwords required).
- Post short messages (up to 400 characters).
- View a feed of the last 10 messages.
- Like messages posted by other users.

The goal is to apply the principles of service-based architecture, focusing on business context separation and scalability, without implementing front-end UIs or load balancers.

## Business Requirements

- Users must register before posting messages.
- Messages are public and can be viewed by anyone.
- The feed displays the last 10 messages.
- Messages can be liked by registered users.

## Architecture

The system consists of three main services:
1. **User Service** - Handles user registration.
2. **Message Service** - Allows users to post and retrieve messages.
3. **Like Service** - Allows users to like messages.

These services are separated based on business logic rather than technical partitioning.

## Services

### 1. **User Service**
Handles user registration. Users can register with a unique username. No passwords or additional data are required.

### 2. **Message Service**
Handles posting and viewing messages. Registered users can post messages of up to 400 characters. The feed displays the last 10 messages.

### 3. **Like Service**
Handles liking posts. Registered users can like messages, and the like count is updated for each message.

## API Endpoints

### User Service

- **Register a User**  
  `POST /users/register`  
  **Body Parameters**:
  - `username` (string): The username of the user.

  **Example**:
  ```bash
  curl -X POST http://localhost:8080/TwitterLikeSystem/users/register -d "username=JohnDoe"


## Message Service

### Post a Message
- **Method:** `POST /messages/post`
- **Body Parameters:**
  - `username` (string): The username of the poster.
  - `content` (string): The content of the message (max 400 characters).

  **Example:**
  ```bash
  curl -X POST http://localhost:8080/TwitterLikeSystem/messages/post -d "username=JohnDoe&content=Hello World"
  

# TwitterLikeSystem API

## View the Feed

- **Method:** `GET /messages/feed`
- **Description:** Returns the last 10 messages posted in the system.

### Example:

    ```bash
    curl -X GET http://localhost:8080/TwitterLikeSystem/messages/feed

# Like Service

## View the Feed

- **Method:** `POST /likes/like`
- **Body Parameters:**
  - `username` (string): The username of the user liking the message.
  - `content` (string): The ID of the message to be liked.

### Example:

    ```bash
    curl -X POST http://localhost:8080/TwitterLikeSystem/likes/like -d "username=JohnDoe&messageId=1"

# How to Run the Project

## Clone the Repository

Clone the project repository from the provided URL:

    ```bash
    git clone <https://github.com/Ezekiel-Gadzama/twitter-like-system.git>
    cd TwitterLikeSystem

## Build the Project

Use Maven to build the project:

    ```bash
    mvn clean install

## Run the Application

Deploy the WAR file on an Apache Tomcat server (version 10.1.26 is recommended). Start the server:

    ```bash
    <tomcat-directory>/bin/startup.sh


## Access the Application

The application should be running at:  
[http://localhost:8080/TwitterLikeSystem](http://localhost:8080/TwitterLikeSystem).


## Testing the Endpoints

### Using Postman

#### Register a User
- **Method**: POST  
- **URL**: `http://localhost:8080/TwitterLikeSystem/users/register`  
- **Body (x-www-form-urlencoded)**:
  - `username`: JohnDoe  

#### Post a Message
- **Method**: POST  
- **URL**: `http://localhost:8080/TwitterLikeSystem/messages/post`  
- **Body (x-www-form-urlencoded)**:
  - `username`: JohnDoe  
  - `content`: Hello World  

#### View the Feed
- **Method**: GET  
- **URL**: `http://localhost:8080/TwitterLikeSystem/messages/feed`  

#### Like a Message
- **Method**: POST  
- **URL**: `http://localhost:8080/TwitterLikeSystem/likes/like`  
- **Body (x-www-form-urlencoded)**:
  - `username`: JohnDoe  
  - `messageId`: 1  


### Using curl
Refer to the API Endpoints section above for example curl commands.

  #### Register a User
    ```bash
    curl -X POST http://localhost:8080/TwitterLikeSystem/users/register -d "username=JohnDoe"

#### Post a Message
    ```bash
    curl -X POST http://localhost:8080/TwitterLikeSystem/messages/post -d "username=JohnDoe&content=Hello World"

#### View the Feed
    ```bash
    curl -X GET http://localhost:8080/TwitterLikeSystem/messages/feed

#### Like a Message
    ```bash
    curl -X POST http://localhost:8080/TwitterLikeSystem/likes/like -d "username=JohnDoe&messageId=1"
