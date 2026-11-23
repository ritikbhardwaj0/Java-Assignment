# FeedbackApp

A Java Servlet application that accepts feedback and displays the submitted fields with the server's current date and time. This project is configured for Apache Tomcat 10.1 and its Jakarta Servlet 6.0 API.

## Project layout

```text
FeedbackApp/
├── feedback.html
├── WEB-INF/
│   ├── web.xml
│   └── classes/                 # created by build.sh
├── src/com/example/FeedbackServlet.java
└── build.sh
```

## Build and run

Set `TOMCAT_HOME` to the Tomcat 10.1 installation directory, then run:

```sh
TOMCAT_HOME=/path/to/apache-tomcat-10.1.62 ./build.sh
```

The script compiles the servlet against Tomcat's `servlet-api.jar` into `WEB-INF/classes`. Deploy this directory as `$TOMCAT_HOME/webapps/FeedbackApp` (this project can also be used directly there). Start Tomcat and open:

```text
http://localhost:8080/FeedbackApp/feedback.html
```

The form posts to `/submitFeedback`, which is mapped to `com.example.FeedbackServlet` in `WEB-INF/web.xml`. The servlet API is supplied by Tomcat and must not be bundled with the application.
