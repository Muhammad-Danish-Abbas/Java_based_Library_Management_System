import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.Optional;

public class LibraryManagementSystem {
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private JTextField titleField, authorField, isbnField, yearField, searchField;
    private JComboBox<String> statusBox;
    private JTable bookTable;
    private DefaultTableModel tableModel;
    private List<Book> bookList = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryManagementSystem().createAndShowGUI());
    }

    private void createAndShowGUI() {
        frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 500);
        frame.setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Manage Books", createManageBooksPanel());
        tabbedPane.addTab("View Library", createViewLibraryPanel());

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    private JPanel createManageBooksPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        titleField = new JTextField(15);
        authorField = new JTextField(15);
        isbnField = new JTextField(15);
        yearField = new JTextField(15);
        statusBox = new JComboBox<>(new String[]{"Available", "Borrowed"});

        JButton addUpdateButton = new JButton("Add/Update");
        JButton clearButton = new JButton("Clear Form");

        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Title:"), gbc);
        gbc.gridx = 1; panel.add(titleField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Author:"), gbc);
        gbc.gridx = 1; panel.add(authorField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("ISBN (13 digits):"), gbc);
        gbc.gridx = 1; panel.add(isbnField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; panel.add(new JLabel("Year:"), gbc);
        gbc.gridx = 1; panel.add(yearField, gbc);

        gbc.gridx = 0; gbc.gridy = 4; panel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1; panel.add(statusBox, gbc);

        gbc.gridx = 0; gbc.gridy = 5; panel.add(addUpdateButton, gbc);
        gbc.gridx = 1; panel.add(clearButton, gbc);

        addUpdateButton.addActionListener(e -> addOrUpdateBook());
        clearButton.addActionListener(e -> clearForm());

        return panel;
    }

    private JPanel createViewLibraryPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        JButton exportButton = new JButton("Export");
        JButton importButton = new JButton("Import");
        topPanel.add(new JLabel("Search:"));
        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(exportButton);
        topPanel.add(importButton);

        String[] columns = {"Title", "Author", "ISBN", "Year", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        bookTable = new JTable(tableModel);
        bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Color-coded status rendering
        bookTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = table.getValueAt(row, 4).toString();
                if ("Available".equals(status)) {
                    c.setForeground(new Color(0, 128, 0)); // Green
                } else if ("Borrowed".equals(status)) {
                    c.setForeground(Color.RED);
                } else {
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(bookTable);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton deleteButton = new JButton("Delete Selected");
        JButton refreshButton = new JButton("Refresh");

        bottomPanel.add(deleteButton);
        bottomPanel.add(refreshButton);

        searchButton.addActionListener(e -> searchBooks());
        exportButton.addActionListener(e -> exportToCSV());
        importButton.addActionListener(e -> importFromCSV());
        deleteButton.addActionListener(e -> deleteSelectedBook());
        refreshButton.addActionListener(e -> refreshTable());

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void addOrUpdateBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        String isbn = isbnField.getText();
        String yearStr = yearField.getText();
        String status = (String) statusBox.getSelectedItem();

        if (title.isEmpty() || author.isEmpty() || isbn.isEmpty() || yearStr.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isbn.matches("\\d{13}")) {
            JOptionPane.showMessageDialog(frame, "ISBN must be exactly 13 digits.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int year;
        try {
            year = Integer.parseInt(yearStr);
            if (year < 1800 || year > 2025) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Year must be between 1800 and 2025.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Optional<Book> existing = bookList.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst();
        if (existing.isPresent()) {
            Book book = existing.get();
            book.setTitle(title);
            book.setAuthor(author);
            book.setYear(year);
            book.setStatus(status);
        } else {
            bookList.add(new Book(title, author, isbn, year, status));
        }

        refreshTable();
        clearForm();
    }

    private void clearForm() {
        titleField.setText("");
        authorField.setText("");
        isbnField.setText("");
        yearField.setText("");
        statusBox.setSelectedIndex(0);
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Book book : bookList) {
            tableModel.addRow(new Object[]{book.getTitle(), book.getAuthor(), book.getIsbn(), book.getYear(), book.getStatus()});
        }
    }

    private void searchBooks() {
        String keyword = searchField.getText().toLowerCase();
        tableModel.setRowCount(0);
        for (Book book : bookList) {
            if (book.getTitle().toLowerCase().contains(keyword) ||
                book.getAuthor().toLowerCase().contains(keyword) ||
                book.getIsbn().contains(keyword) ||
                Integer.toString(book.getYear()).contains(keyword)) {
                tableModel.addRow(new Object[]{book.getTitle(), book.getAuthor(), book.getIsbn(), book.getYear(), book.getStatus()});
            }
        }
    }

    private void deleteSelectedBook() {
        int row = bookTable.getSelectedRow();
        if (row >= 0) {
            String isbn = (String) tableModel.getValueAt(row, 2);
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure to delete this book?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                bookList.removeIf(book -> book.getIsbn().equals(isbn));
                refreshTable();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "No book selected.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void exportToCSV() {
        try (PrintWriter writer = new PrintWriter(new File("books.csv"))) {
            writer.println("Title,Author,ISBN,Year,Status");
            for (Book book : bookList) {
                writer.printf("%s,%s,%s,%d,%s%n", book.getTitle(), book.getAuthor(), book.getIsbn(), book.getYear(), book.getStatus());
            }
            JOptionPane.showMessageDialog(frame, "Exported to books.csv successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error exporting to CSV.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void importFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("books.csv"))) {
            String line;
            reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String title = parts[0];
                    String author = parts[1];
                    String isbn = parts[2];
                    int year = Integer.parseInt(parts[3]);
                    String status = parts[4];
                    bookList.add(new Book(title, author, isbn, year, status));
                }
            }
            refreshTable();
            JOptionPane.showMessageDialog(frame, "Books imported successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error importing CSV.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Inner Book class
    static class Book {
        private String title, author, isbn, status;
        private int year;

        public Book(String title, String author, String isbn, int year, String status) {
            this.title = title;
            this.author = author;
            this.isbn = isbn;
            this.year = year;
            this.status = status;
        }

        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public String getIsbn() { return isbn; }
        public int getYear() { return year; }
        public String getStatus() { return status; }

        public void setTitle(String title) { this.title = title; }
        public void setAuthor(String author) { this.author = author; }
        public void setIsbn(String isbn) { this.isbn = isbn; }
        public void setYear(int year) { this.year = year; }
        public void setStatus(String status) { this.status = status; }
    }
}
