#!/bin/sh
set -eu

APP_DIR=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
TOMCAT_HOME=${TOMCAT_HOME:-}

if [ -z "$TOMCAT_HOME" ]; then
    printf '%s\n' 'Set TOMCAT_HOME to your Tomcat 10.1 installation directory.' >&2
    exit 1
fi

SERVLET_API="$TOMCAT_HOME/lib/servlet-api.jar"
if [ ! -f "$SERVLET_API" ]; then
    printf 'Tomcat Servlet API not found: %s\n' "$SERVLET_API" >&2
    exit 1
fi

mkdir -p "$APP_DIR/WEB-INF/classes"
javac -encoding UTF-8 -cp "$SERVLET_API" \
    -d "$APP_DIR/WEB-INF/classes" \
    "$APP_DIR/src/com/example/FeedbackServlet.java"
printf 'Build successful. Classes written to %s/WEB-INF/classes\n' "$APP_DIR"
