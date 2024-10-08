package com.example.users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class UserServlet extends HttpServlet {
  private static List<User> users = new ArrayList<>(); // Static to persist across instances

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String username = request.getParameter("username");

    if (username != null && !username.isEmpty()) {
      if (!isUserRegistered(username)) {
        User user = new User(username);
        users.add(user);
        response.getWriter().write("User registered: " + username);
      } else {
        response.getWriter().write("User already registered: " + username);
      }
    } else {
      response.getWriter().write("Username cannot be empty.");
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    for (User user : users) {
      response.getWriter().write("User: " + user.getUsername() + "\n");
    }
  }

  // method to check if a user is registered
  public static boolean isUserRegistered(String username) {
    for (User user : users) {
      if (user.getUsername().equals(username)) {
        return true;
      }
    }
    return false;
  }
}

