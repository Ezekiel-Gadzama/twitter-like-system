package com.example.messages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.example.users.UserServlet; // Import UserServlet
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MessageServlet extends HttpServlet {
  private static final int MAX_MESSAGES = 10;
  private static List<Message> messages = new ArrayList<>();
  private UserServlet userServlet = new UserServlet(); // Create an instance of UserServlet

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String username = request.getParameter("username");
    String content = request.getParameter("content");

    if (!userServlet.isUserRegistered(username)) { // Check if user is registered
      response.getWriter().write("User not registered. Please register to post messages.");
      return;
    }

    if (content == null || content.length() > 400) {
      response.getWriter().write("Message must be between 1 and 400 characters.");
      return;
    }

    Message message = new Message(username, content);
    if (messages.size() >= MAX_MESSAGES) {
      messages.remove(0); // Remove oldest message
    }
    messages.add(message);

    response.getWriter().write("Message posted by " + username);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/plain");
    StringBuilder feed = new StringBuilder("Latest Messages:\n");

    for (Message message : messages) {
      feed.append("[").append(message.getUsername()).append("]: ").append(message.getContent())
          .append(" (Likes: ").append(message.getLikes()).append(")\n");
    }

    response.getWriter().write(feed.toString());
  }

  public static List<Message> getMessages() {
    return messages;
  }
}
