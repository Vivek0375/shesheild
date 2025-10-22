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

SheShield/
│
├── src/
│   ├── main/
│   │   ├── java/com/steptowardprotection/sheshield/
│   │   │   ├── controller/
│   │   │   │   ├── IncidentController.java
│   │   │   │   ├── IncidentDashboardController.java
│   │   │   │
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   └── SheShieldApplication.java
│   │   │
│   │   └── resources/
│   │       ├── templates/
│   │       │   └── dashboard.html
│   │       ├── application.properties
│   │       └── static/
│   │
│   └── test/
│
├── pom.xml
└── README.md
