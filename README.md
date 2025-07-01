📚 Library Management System


A modern Java-based desktop application to streamline library operations with an intuitive Swing GUI.


🚀 1. Introduction and Problem Statement
Libraries face challenges in managing book inventories efficiently due to manual processes that are time-consuming and error-prone. The Library Management System is a Java desktop application designed to simplify book management tasks, offering a sleek GUI for librarians to add, update, delete, search, import, and export book records. With features like color-coded status indicators and CSV integration, it enhances productivity and organization.
Problem Statement: Create a Java application with a Swing-based GUI to manage a library’s book inventory, supporting CRUD operations, CSV import/export, and a visually appealing interface with status tracking.

🎨 2. Design Overview
The application features a tabbed interface for seamless navigation, dividing functionality into two intuitive tabs:

Manage Books: Input fields for adding or updating book details (title, author, ISBN, year, status).
View Library: A table displaying all books with options to search, delete, import, and export.

🖼️ Wireframe/Mockup
┌──────────────────────────────────────────────────┐
│ 📖 Library Management System                     │
└──────────────────────────────────────────────────┘
│ [Tabs: Manage Books | View Library]             │
├──────────────────────────────────────────────────┤
│ 📝 Manage Books Tab:                            │
│ Title: [     ] Author: [     ] ISBN: [     ]    │
│ Year: [     ] Status: [Available/Borrowed]      │
│ [Add/Update Btn] [Clear Form Btn]               │
├──────────────────────────────────────────────────┤
│ 📊 View Library Tab:                            │
│ Search: [          ] [Search Btn] [Export Btn]  │
│ [Import Btn]                                    │
│ Table: Title | Author | ISBN | Year | Status    │
│ [Delete Selected Btn] [Refresh Btn]             │
└──────────────────────────────────────────────────┘


Layout: JTabbedPane with GridBagLayout for precise input alignment in the Manage Books tab and BorderLayout for the View Library tab.
Design Goal: Modern, clean, and user-friendly with color-coded status (🟢 Available, 🔴 Borrowed).


🛠️ 3. Technology Stack and Java GUI Toolkit



Component
Details



Language
Java (JDK 11)


GUI Toolkit
Swing (lightweight, VS Code-friendly)


Build Tool
Maven


IDE
Visual Studio Code with Java Extension Pack and Maven for Java


Libraries
- javax.swing, javax.swing.table: GUI and table rendering- java.awt: Layouts and colors- java.io: CSV import/export- java.util: List, Optional for data management


Swing was chosen for its simplicity and compatibility with educational environments like VS Code, delivering a robust GUI without external dependencies.

📂 4. Implementation Details and Code Structure
The project is structured as a Maven project for easy setup in VS Code:
Directory Structure
LibraryManagementSystem/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── LibraryManagementSystem.java
├── pom.xml


LibraryManagementSystem.java: Main class with GUI setup, event handling, and an inner Book class for data modeling.
pom.xml: Configures Maven dependencies and build settings (provided below).

The application follows an MVC-inspired design:

Model: Inner Book class (title, author, ISBN, year, status).
View: Swing components (JTabbedPane, JTable, JTextField, JComboBox, JButton).
Controller: Event listeners for user interactions.


🖥️ 5. Key GUI Components and Event Handling
🎨 GUI Components



Component
Description



JFrame
Main window (850x500 pixels, centered)


JTabbedPane
Tabs for "Manage Books" and "View Library"


JTextField
Inputs for title, author, ISBN, year, and search


JComboBox
Selects book status (Available/Borrowed)


JTable
Displays books (Title, Author, ISBN, Year, Status)


JButton
Actions: Add/Update, Clear, Search, Export, Import, Delete, Refresh


JScrollPane
Scrollable table view


JLabel
Labels for input fields


JOptionPane
Error messages and deletion confirmation


🖱️ Event Handling

Add/Update Button: Adds a new book or updates an existing one (using ISBN as a unique key).
Clear Form Button: Resets input fields in the Manage Books tab.
Search Button: Filters books by title, author, ISBN, or year (case-insensitive).
Export Button: Saves book data to books.csv.
Import Button: Loads books from books.csv, skipping the header.
Delete Button: Removes selected book after confirmation.
Refresh Button: Reloads all books in the table.

✨ GUI Enhancements

Color-Coded Status: 🟢 Green for "Available", 🔴 Red for "Borrowed" in the table.
Responsive Layout: GridBagLayout for precise input alignment, BorderLayout for table and actions.
Single Selection: Restricts table to single-row selection for clarity.


⚠️ 6. Challenges Faced and Solutions



Challenge
Solution



Color-coding table status
Custom DefaultTableCellRenderer to set text color based on status.


CSV import/export without libraries
Used PrintWriter for export and BufferedReader for import.


Preventing duplicate books
Used ISBN as a unique identifier with Optional to check for duplicates.


Input validation for ISBN and year
Regex for 13-digit ISBN; year range check (1800–2025).



🧪 7. Testing and User Experience Feedback

Testing:
Manual Testing: Validated adding, updating, deleting, searching, importing, and exporting books. Tested edge cases:
Empty input fields
Invalid ISBN (non-13-digit)
Invalid year (<1800 or >2025)
Missing or malformed CSV file


Environment: Tested in VS Code with JDK 11 on Windows.


User Experience:
Pros: Tabbed interface is intuitive; color-coded status improves readability; error messages guide users.
Feedback: Users appreciated the clean design but suggested adding update confirmation dialogs.
Usability: Simple navigation, fast search, and reliable CSV operations enhance librarian efficiency.




🌟 8. Conclusion and Future Improvements
The Library Management System delivers a modern, efficient solution for managing library books. Its Swing-based GUI, combined with CRUD operations, CSV import/export, and color-coded status tracking, makes it ideal for small to medium libraries. The application is easy to set up in VS Code and meets educational project requirements with a professional look.
🔮 Future Improvements

Integrate a database (e.g., SQLite) for persistent storage.
Add confirmation dialogs for book updates.
Enhance search with filters (e.g., status or year range).
Include custom icons and UI themes for a polished look.
Improve CSV import robustness for malformed files.
Implement user authentication for secure access.


🛠️ Setup Instructions for VS Code

Create Project Structure:

Create a folder: LibraryManagementSystem.
Create src/main/java and place LibraryManagementSystem.java in it.
Add pom.xml (below) in the root folder.


Install Extensions:

Install "Extension Pack for Java" and "Maven for Java" in VS Code.


Run the Application:

Open the project in VS Code.
VS Code will detect pom.xml and configure the project.
Right-click LibraryManagementSystem.java and select "Run Java".
Or, in the terminal:mvn clean package
java -cp target/library-management-system-1.0-SNAPSHOT.jar LibraryManagementSystem






Built with 💻 Java and 🎨 Swing for a seamless library management experience.