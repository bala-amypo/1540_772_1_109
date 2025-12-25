package com.example.demo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/simple-status")
public class SimpleStatusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Handle null request safely (required by tests)
        if (resp == null) {
            return;
        }

        resp.setContentType("text/plain");

        PrintWriter writer = resp.getWriter();
        writer.write("Credit Card Reward Maximizer is running");
        writer.flush(); // CRITICAL: tests verify flush() is called
    }
}
