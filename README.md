# Ledger Application

## Description
The Ledger Application is a Java-based console application that helps users manage their financial transactions effectively. Users can add deposits, make payments, view their transaction ledger, and generate various reports. This application provides a clear interface for tracking and managing personal finances.

## Features
- **Add Deposit**: Allows users to add recent or old deposit transactions.
- **Make Payment**: Users can record payments made, with options for recent or old payments.
- **View Ledger**: Users can view all transactions, filtered by type (all, deposits, payments).
- **Generate Reports**: Users can generate reports based on various criteria, including month-to-date and previous month transactions.

## Classes
### 1. `LedgerApp`
This is the main class of the application that drives the user interface. It provides options for the user to add deposits, make payments, view the ledger, or exit the application. The class uses a `Scanner` for input and interacts with the `TransactionManager` and `ReportManager`.

### 2. `TransactionManager`
This class handles the management of transactions, including loading and saving them to a CSV file. It provides methods to add transactions, retrieve all transactions, and filter transactions into deposits and payments.

### 3. `ReportManager`
The `ReportManager` class is responsible for generating reports based on transactions. It can create month-to-date, previous month, and year-to-date reports, as well as search transactions by vendor name.

### 4. `Transaction`
This class represents a financial transaction. It includes details such as date, time, description, vendor, and amount. It provides methods to parse transactions from a string format and to convert a transaction to a string.

### 5. `Console`
This utility class simplifies input handling. It provides various methods for prompting the user for input, including strings, integers, doubles, and booleans. It also includes methods for displaying animations and printing lines.

## Screenshots
Here is a screenshot for the Home Screen Menu:

![App Screenshot](https://github.com/YearUp-Razeenali/AccountingLedgerApp/blob/main/images/homescreen.PNG)


Here is a screenshot for the Ledger Screen Menu:

![App Screenshot 1](https://github.com/YearUp-Razeenali/AccountingLedgerApp/blob/main/images/ledger.PNG)

Here is a screenshot for the Reports Screen Menu:

![App Screenshot 1](https://github.com/YearUp-Razeenali/AccountingLedgerApp/blob/main/images/homescreen.PNG)

## Interesting Code
One interesting piece of code is the `handleTransactionInput` method in the `LedgerApp` class, which manages user input for both deposits and payments.

```java
private static void handleTransactionInput(String date, String time, Scanner scanner, boolean isPayment) throws InterruptedException {
    try {
        String description = Console.PromptForString("Enter description: ");
        String vendor = Console.PromptForString("Enter vendor: ");
        float amount = Console.PromptForFloat("Enter amount: ");

        // Create transaction
        if (isPayment) {
            transactionManager.addTransaction(new Transaction(date, time, description, vendor, -amount)); // Negative for payment
            Console.Loadinganimation();
            System.out.println("Payment recorded successfully.");
            Console.printLine("-");
        } else {
            transactionManager.addTransaction(new Transaction(date, time, description, vendor, amount)); // Positive for deposit
            Console.Loadinganimation();
            System.out.println("Deposit added successfully.");
            Console.printLine("-");
        }
    } catch (InputMismatchException e) {
        System.out.println("Invalid input type. Please enter a valid number for amount.");
        scanner.nextLine();
    }
}
```
## How to Run

1. Clone the repository.
2. Ensure you have Java installed on your system.
3. Navigate to the project directory in the terminal.
4. Compile the Java files:

   ```bash
   javac com/pluralsight/*.java

   ```
5. Run the main application:
   ```bash
   java com.pluralsight.LedgerApp

   ```


## License

This project is licensed under the MIT License. See the LICENSE file for details.


