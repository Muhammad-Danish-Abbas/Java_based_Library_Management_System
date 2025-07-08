# 📚 Library Management System

A sleek Java-based desktop application to streamline library operations with an intuitive Swing GUI.

---
[Library Management System](https://images.search.yahoo.com/search/images;_ylt=AwrjIcmzD21oOVcDQXVXNyoA;_ylu=Y29sbwNncTEEcG9zAzEEdnRpZAMEc2VjA3BpdnM-?p=library+management+system+project&fr2=piv-web&type=E210US91215G0&fr=mcafee)
## 🚀 1. Introduction and Problem Statement

Managing library inventories manually is time-consuming and prone to errors. The Library Management System is a Java desktop application designed to simplify book management for librarians. It offers a modern GUI to add, update, delete, search, import, and export book records, with features like color-coded status indicators and CSV integration to enhance efficiency.

**Problem Statement:** Develop a Java application with a Swing-based GUI to manage a library’s book inventory, supporting CRUD operations, CSV import/export, and a visually appealing interface with status tracking.

---

# How to Run in PowerShell
## Save file as: LibraryManagementSystem.java

### Open PowerShell and run:

powershell
```java
cd "$HOME\Desktop"
javac LibraryManagementSystem.java
java LibraryManagementSystem
```
## 🎨 2. Design Overview

The application features a tabbed interface for intuitive navigation, with two tabs:

* **Manage Books:** Input fields for adding or updating book details (title, author, ISBN, year, status).
* **View Library:** A table displaying books with options to search, delete, import, and export.

### 🖼️ Wireframe/Mockup

```java
🔼 Library Management System
[Tabs: Manage Books | View Library]

📒 Manage Books Tab:
Title: [     ] Author: [     ] ISBN: [     ]
Year: [     ] Status: [Available/Borrowed]
[Add/Update Btn] [Clear Form Btn]

📊 View Library Tab:
Search: [          ] [Search Btn] [Export Btn] [Import Btn]
Table: Title | Author | ISBN | Year | Status
[Delete Selected Btn] [Refresh Btn]
```

* **Layout:** JTabbedPane with GridBagLayout for the Manage Books tab and BorderLayout for the View Library tab.
* **Design Goal:** Modern, user-friendly interface with 🟢 green for "Available" and 🔴 red for "Borrowed" status.

---

## 🛠️ 3. Technology Stack and Java GUI Toolkit

| Component   | Details                                   |
| ----------- | ----------------------------------------- |
| Language    | Java (JDK 11)                             |
| GUI Toolkit | Swing (lightweight, VS Code-friendly)     |
| Build Tool  | Maven                                     |
| IDE         | Visual Studio Code                        |
| Libraries   | javax.swing, java.awt, java.io, java.util |

Swing was selected for its simplicity and compatibility with educational environments like VS Code.

---

## 📂 4. Implementation Details and Code Structure

The project is structured as a Maven project:

```java
LibraryManagementSystem/
├── src/
│   └── main/
│       └── java/
│           └── LibraryManagementSystem.java
└── pom.xml
```

* **LibraryManagementSystem.java:** Main class with GUI setup, event handling, and an inner `Book` class.
* **pom.xml:** Configures Maven build and dependencies.

**MVC Structure:**

* **Model:** `Book` class
* **View:** Swing UI components
* **Controller:** Event handlers

---

## 💻 5. Key GUI Components and Event Handling

### 🎨 GUI Components

| Component   | Description                          |
| ----------- | ------------------------------------ |
| JFrame      | Main window                          |
| JTabbedPane | Tabs for navigation                  |
| JTextField  | Input fields                         |
| JComboBox   | Book status selection                |
| JTable      | Book data table                      |
| JButton     | Trigger actions (Add, Delete, etc.)  |
| JScrollPane | Scrollable view for JTable           |
| JLabel      | Field labels                         |
| JOptionPane | Dialogs for errors and confirmations |

### 👡 Event Handling

* **Add/Update Button:** Add or update book by ISBN.
* **Clear Form:** Resets all input fields.
* **Search:** Filters books by text input.
* **Export:** Saves data to `books.csv`.
* **Import:** Reads data from `books.csv`.
* **Delete:** Removes selected book.
* **Refresh:** Reloads all books.

### ✨ GUI Enhancements

* **Color-coded Status:** 🟢 Green = Available, 🔴 Red = Borrowed
* **Layouts:** GridBagLayout and BorderLayout
* **Single Selection Mode** for table clarity

---

## ⚠️ 6. Challenges Faced and Solutions

| Challenge                   | Solution                                |
| --------------------------- | --------------------------------------- |
| Table status color-coding   | Custom `DefaultTableCellRenderer`       |
| CSV operations without libs | Used `PrintWriter` and `BufferedReader` |
| Avoiding duplicate entries  | Checked `ISBN` with `Optional`          |
| Input validation            | Regex and numeric range checks          |

---

## 🧪 7. Testing and User Experience Feedback

### 🔧 Testing

* Manual tests for all major features
* Validated edge cases:

  * Empty fields
  * Invalid ISBN or Year
  * Missing CSV files

### 👥 User Experience

* Pros: Clean UI, clear feedback, fast interaction
* Feedback: Add confirmation for updates
* Bonus: CSV import/export is smooth and fast

---

## 🌟 8. Conclusion and Future Improvements

The Library Management System delivers an efficient, user-friendly desktop solution for small to medium-sized libraries. Its clear structure and use of Java Swing make it ideal for academic and entry-level development.

### 🔮 Future Enhancements

* Integrate database (e.g., SQLite)
* Add update confirmation dialogs
* Improve search with filters and ranges
* Enhance GUI with icons and themes
* Secure the system with user login

---

## 🛠️ Setup Instructions (VS Code)

### 1. Create Project Structure:

```java
LibraryManagementSystem/
├── src/main/java/LibraryManagementSystem.java
└── pom.xml
```

### 2. Install Extensions:

* "Extension Pack for Java"
* "Maven for Java"

### 3. Run the Application:

* Open the folder in VS Code
* Right-click `LibraryManagementSystem.java` > Run Java

**Or via Terminal:**

```bash
mvn clean package
java -cp target/library-management-system-1.0-SNAPSHOT.jar LibraryManagementSystem
```

---

> Built with 💻 Java and 🎨 Swing for a seamless library management experience. 📖
