package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FeedbackServlet extends HttpServlet {
    private static final Set<String> ALLOWED_TYPES = new HashSet<String>(Arrays.asList(
            "Complaint", "Suggestion", "Compliment"));
    private static final DateTimeFormatter DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm:ss a");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String name = trim(request.getParameter("name"));
        String email = trim(request.getParameter("email"));
        String feedbackType = trim(request.getParameter("feedbackType"));
        String comments = trim(request.getParameter("comments"));

        if (name.isEmpty() || email.isEmpty() || comments.isEmpty()
                || !ALLOWED_TYPES.contains(feedbackType)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeError(response, "Please provide all fields and select a valid feedback type.");
            return;
        }

        String submittedAt = LocalDateTime.now().format(DATE_TIME_FORMAT);
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\"><head><meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        out.println("<title>Feedback Received</title>");
        out.println("<style>body{font-family:Arial,sans-serif;max-width:680px;margin:48px auto;padding:0 20px;color:#202938}h1{color:#245a8d}.card{background:#f3f7fb;padding:20px;border-radius:6px}dt{font-weight:bold;margin-top:12px}dd{margin:4px 0 0;white-space:pre-wrap;overflow-wrap:anywhere}a{display:inline-block;margin-top:20px;color:#245a8d}</style>");
        out.println("</head><body><h1>Feedback received</h1>");
        out.println("<p>Thank you, " + escapeHtml(name) + ", for your feedback!</p>");
        out.println("<div class=\"card\"><dl>");
        out.println("<dt>Name</dt><dd>" + escapeHtml(name) + "</dd>");
        out.println("<dt>Email</dt><dd>" + escapeHtml(email) + "</dd>");
        out.println("<dt>Feedback type</dt><dd>" + escapeHtml(feedbackType) + "</dd>");
        out.println("<dt>Comments</dt><dd>" + escapeHtml(comments) + "</dd>");
        out.println("<dt>Submitted on</dt><dd>" + escapeHtml(submittedAt) + "</dd>");
        out.println("</dl></div><a href=\"feedback.html\">Submit another response</a>");
        out.println("</body></html>");
    }

    private static String trim(String value) {
        return value == null ? "" : value.trim();
    }

    private static String escapeHtml(String value) {
        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#x27;");
    }

    private static void writeError(HttpServletResponse response, String message)
            throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><title>Invalid feedback</title></head>");
        out.println("<body><h1>Feedback not submitted</h1><p>" + escapeHtml(message) + "</p>");
        out.println("<p><a href=\"feedback.html\">Return to the form</a></p></body></html>");
    }
}
