package com.example.messages;

public class Message {
  private String username;
  private String content;
  private int likes;

  public Message(String username, String content) {
    this.username = username;
    this.content = content;
    this.likes = 0;
  }

  public String getUsername() {
    return username;
  }

  public String getContent() {
    return content;
  }

  public int getLikes() {
    return likes;
  }

  public void like() {
    this.likes++;
  }
}
