🛡️ SheShield – Women Safety & Incident Alert System

SheShield is a Spring Boot–based Women Safety & Alert System designed to enable quick incident reporting, live tracking, and centralized monitoring through an admin dashboard.
It supports real-time map visualization, severity-based filtering, and reporting analytics.

🚀 Features :
👩‍💻 User Features

Report incidents with location, type, severity, and description.

Automatically capture GPS coordinates during report submission.

Notify emergency contacts via email or SMS (optional module).

🧭 Admin / Dashboard Features

Web-based Thymeleaf dashboard to view all reported incidents.

Live incident map powered by Google Maps API.

Auto-refresh every 10 seconds to keep data updated.

Filter incidents by severity, type, and date range.

Generate JSON reports for frontend visualization or analytics.


🧩 Tech Stack
Layer	Technology
Backend	Java 17, Spring Boot, Spring MVC, Spring Data JPA
Frontend	Thymeleaf, HTML5, CSS3, JavaScript (ES6)
Database	MySQL
Visualization	Google Maps JavaScript API
Build Tool	Maven
Others	Lombok, JPA, Validation API


🗂️ Project Structure

![project Structure  Screenshot](https://github.com/Vivek0375/shesheild/blob/5a1521c156e06431dfda6158514aa0e2a199e521/Screenshot%202025-10-22%20162734.png?raw=true)


⚙️ Setup & Run Locally:

Prerequisites

Java 17+

Maven 3+

MySQL running locally

Google Maps API key

Steps:

Clone the repo

git clone https://github.com/<your-username>/SheShield.git
cd SheShield


Configure MySQL
Update src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/sheshield_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.thymeleaf.prefix=classpath:/templates/


Add Google Maps API key
In dashboard.html, replace:

<script src="https://maps.googleapis.com/maps/api/js?key=YOUR_GOOGLE_MAPS_API_KEY"></script>


Build and run

mvn spring-boot:run


Access the dashboard

http://localhost:8080/dashboard

🧮 Future Enhancements:

📊 Add analytics for incident trends by date, type, and region.

🔔 Add email/SMS notifications for critical severity incidents.

📱 Develop a React or Android frontend for user-side reporting.

🧠 Integrate ML model for risk prediction in unsafe zones.


## 🖥️ Live Dashboard Preview:

![SheShield Dashboard](https://github.com/Vivek0375/shesheild/blob/5a1521c156e06431dfda6158514aa0e2a199e521/Screenshot%202025-10-22%20162734.png?raw=true)
> The dashboard displays real-time incident data with Google Maps integration and auto-refresh.

---

## 📜 License:

This project is licensed under the [MIT License](https://github.com/Vivek0375/shesheild/blob/main/LICENSE).


🧑‍💼 Author:

Vivek Yadav

Backend Developer | Java | Spring Boot | MySQL | AWS

![Github Link](https://github.com/Vivek0375)

