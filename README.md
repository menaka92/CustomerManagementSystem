# Customer Management System

This project is a Customer Management System following the MVC (Model-View-Controller) architecture.

## Project Structure

- **Model**: 
  - `Customer.java`: Defines the `Customer` entity with properties like `id`, `name`, `address`, and `salary`.
  - `DatabaseCode.java`: Contains methods to interact with the database, such as `saveCustomer`, `updateCustomer`, `deleteCustomer`, and `loadTable`.

- **View**: 
  - `CustomerForm.fxml`: Defines the layout of the customer form, including text fields for customer details, a table to display customer data, and buttons for saving and deleting customers.

- **Controller**: 
  - `CustomerFormController.java`: Contains methods to initialize the form, load data into the table, handle user actions (such as saving and deleting customers), and update the view based on user input.

## Data Flow

1. **Initialization**: The `initialize` method in `CustomerFormController` loads all customer data into the table and sets up listeners for search input and table selection.
2. **User Input**: When the user enters a search term, the table data is filtered based on the search term.
3. **Save/Update Customer**: When the user clicks the "Save Customer" button, the customer data is saved or updated in the database, and the table is reloaded.
4. **Delete Customer**: When the user clicks the "Delete" button, the customer is deleted from the database, and the table is reloaded.

## Singleton Pattern

The `CrudUtil` class uses the Singleton pattern to ensure a single instance for database operations.

## Technologies Used

- Java
- JavaFX
- MySQL

## How to Run

1. Set up the MySQL database and update the connection details in `CrudUtil.java`.
2. Open the project in IntelliJ IDEA.
3. Run the application.

## License

This project is licensed under the MIT License.
