package com.example.likes;

import java.io.IOException;
import java.util.List;
import com.example.messages.Message;
import com.example.messages.MessageServlet;
import com.example.users.UserServlet; // Import UserServlet
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LikeServlet extends HttpServlet {
  private UserServlet userServlet = new UserServlet(); // Create an instance of UserServlet

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String messageIdParam = request.getParameter("messageId");
    String username = request.getParameter("username");

    // Validate input
    if (!userServlet.isUserRegistered(username)) { // Check if user is registered
      response.getWriter().write("User not registered. Please register to like messages.");
      return;
    }
    if (messageIdParam == null) {
      response.getWriter().write("Message ID is required.");
      return;
    }

    try {
      int messageId = Integer.parseInt(messageIdParam);
      List<Message> messages = MessageServlet.getMessages();

      if (messageId < 0 || messageId >= messages.size()) {
        response.getWriter().write("Invalid message ID.");
        return;
      }

      Message message = messages.get(messageId);
      message.like();
      response.getWriter().write("Message liked by " + username);
    } catch (NumberFormatException e) {
      response.getWriter().write("Invalid message ID format.");
    }
  }
}
