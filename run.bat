@echo off
call cd com.webproject
call mvn -o clean install
call cd..
call mvn -o clean install
call copy D:\Home_Auto\raspberry\Latest\Apache-Felix\apache-ibm\OSGi-Dev-master\myosgi\target\myosgi-0.0.1-SNAPSHOT.jar D:\Home_Auto\raspberry\Latest\Apache-Felix\apache-ibm\OSGi-Dev-master\launcher\target\bundles

call copy D:\Home_Auto\raspberry\Latest\Apache-Felix\apache-ibm\OSGi-Dev-master\com.webproject\target\com.webproject-0.0.1-SNAPSHOT.jar D:\Home_Auto\raspberry\Latest\Apache-Felix\apache-ibm\OSGi-Dev-master\launcher\target\bundles

call cd launcher

call java -jar target/osgi-fw-launcher-0.0.1-SNAPSHOT.jar
