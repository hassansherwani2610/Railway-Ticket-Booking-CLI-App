# 🚆 Railway Ticket Booking App (RTBA)

A **console-based Railway Ticket Booking Application** built in **Java** using **Gradle**. This project simulates a basic railway reservation system with user authentication, train listings, and ticket booking functionality, using **local JSON files** as a lightweight database.

---

## 📌 Project Overview

RTBA is designed as an academic / learning project to demonstrate:
- Object-Oriented Programming (OOP) concepts in Java
- Layered architecture (Entities, Services, Utils)
- File-based persistence using JSON
- Secure password handling with hashing
- Gradle-based project structure

The application runs in the **terminal/console** and provides an interactive menu-driven experience.

---

## ✨ Features

- 👤 **User Registration & Login**
- 🔐 **Password Hashing for Security**
- 🚆 **View Available Trains**
- 🎟️ **Book Railway Tickets**
- 📄 **View Booked Tickets**
- 💾 **Persistent Storage using JSON files**
- ❌ **Exit Safely from Application**

---

## 🛠️ Tech Stack

| Technology | Description |
|-----------|-------------|
| Java | Core programming language |
| Gradle | Build automation tool |
| JSON | Local data storage |
| OOP | Clean object-oriented design |

---

## 📂 Project Structure

```
RTBA/
│
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/org/example/
│   │   │   │   ├── App.java              # Application entry point
│   │   │   │   ├── entities/              # Data models
│   │   │   │   │   ├── User.java
│   │   │   │   │   ├── Train.java
│   │   │   │   │   └── Ticket.java
│   │   │   │   ├── services/              # Business logic
│   │   │   │   │   ├── TrainService.java
│   │   │   │   │   └── UserBookingService.java
│   │   │   │   ├── utils/                 # Utility classes
│   │   │   │   │   └── PasswordHashUtil.java
│   │   │   │   └── localDB/               # Local JSON database
│   │   │   │       ├── users.json
│   │   │   │       └── trains.json
│   │   └── test/                          # Test resources
│   └── build.gradle
│
├── gradle/                                # Gradle wrapper files
├── gradlew / gradlew.bat
├── settings.gradle
└── .gitignore
```

---

## ▶️ How to Run the Project

### Prerequisites
- Java JDK 17 or later (recommended)
- Gradle (or use Gradle Wrapper)

### Steps

1. **Clone the repository**
```bash
git clone https://github.com/your-username/RTBA.git
cd RTBA
```

2. **Build the project**
```bash
./gradlew build
```
(Windows users: `gradlew.bat build`)

3. **Run the application**
```bash
./gradlew run
```

4. **Follow on-screen instructions** in the console.

---

## 🔑 User Authentication

- Passwords are **hashed** using `PasswordHashUtil`
- User data is stored securely in `users.json`
- No plain-text passwords are saved

---

## 📁 Local Database

The application uses file-based storage:

- `users.json` → Registered users & credentials
- `trains.json` → Available train details

These files act as a **mock database** for simplicity.

---

## 🎯 Learning Objectives

- Practice Java OOP principles
- Understand service-based architecture
- Work with JSON data in Java
- Implement basic authentication logic
- Use Gradle for project management

---

## 🚧 Limitations

- Console-based UI only
- No real-time database
- Single-user session at a time
- No concurrency handling

---

## 📌 Future Improvements

- Add GUI (JavaFX / Swing)
- Integrate real database (MySQL / PostgreSQL)
- Add seat availability logic
- Improve exception handling
- Add unit tests

---

## 👨‍💻 Author

**Hassan Ahmed Khan Sherwani**  
BS Computer Science – UBIT  

---

## 📜 License

This project is for **educational purposes**. Feel free to fork, modify, and learn from it.

---

⭐ If you find this project helpful, consider giving it a star on GitHub!

