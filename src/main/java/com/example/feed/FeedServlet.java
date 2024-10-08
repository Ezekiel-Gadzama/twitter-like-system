package com.example.feed;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import com.example.messages.Message;
import com.example.messages.MessageServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/messages/feed")
public class FeedServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("application/json");
    PrintWriter out = response.getWriter();

    // Retrieve messages from MessageServlet
    List<Message> messages = MessageServlet.getMessages();

    // Build JSON response with the last 10 messages
    out.println("[");
    for (int i = 0; i < messages.size(); i++) {
      Message message = messages.get(i);
      out.printf("{\"username\":\"%s\", \"content\":\"%s\", \"likes\":%d}", message.getUsername(),
          message.getContent(), message.getLikes());
      if (i < messages.size() - 1) {
        out.println(",");
      }
    }
    out.println("]");
  }
}
