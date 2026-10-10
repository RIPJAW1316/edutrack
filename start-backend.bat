@echo off
echo ========================================================
echo   Starting EduTrack Spring Boot Backend on Port 8081
echo ========================================================
cd cs
mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=h2
pause
