## Finance Manager
## Description


## Features:

## Home Screen

- Add Deposit:
Records transactions with a positive value


- Make Payment:
Records transactions with a negative value


- Ledger:
Gives access to display deposit history, payment history, and reports.


- Exit: 
Closes the program


![Screenshot 2025-10-16 at 9.51.33 PM.png](Screenshot%202025-10-16%20at%209.51.33%E2%80%AFPM.png)
## Ledger Screen

- All Transactions:
    Displays both negative and positive transactions.
    

- Deposits Only:
    Displays positive transactions.
    

- Payments Only:
    Displays negative transactions.


- Reports:
    Gives access to the reporting feature.


- Home:
    Returns the user to the home screen.

![Screenshot 2025-10-17 at 8.03.12 AM.png](Screenshot%202025-10-17%20at%208.03.12%E2%80%AFAM.png)
## Reports screens

- Month To Date:
    Displays transactions from the start of the current month to current date.


- Previous Month:
  Displays transactions from the previous month.


- Year To Date:
    Displays the earliest transaction in the year to the current date.


- Previous Year:
    Displays transactions from the previous year.


- Search By Vendor:
    Prompts the user for vendor name then displays transactions to or from said vendor.


- Back:
  Returns the user to ledger screen.

![Screenshot 2025-10-17 at 8.03.59 AM.png](Screenshot%202025-10-17%20at%208.03.59%E2%80%AFAM.png)
## Data Storage
All transactions are stored in a CSV file in this format.
![Screenshot 2025-10-17 at 8.46.47 AM.png](Screenshot%202025-10-17%20at%208.46.47%E2%80%AFAM.png)
## Classes
    
- Transactions:
    Handles CSV formating, and provides date/time utilities.


- FileManager:
    Manages I/O Opparations, and handle transaction saving and loading.


- Main:
    Program controler, hub for user interfaice and navigation, and logic for transaction processing. 

## Error handling

This program accounts for errors such as invalid numerical input, file I/O, missing required fields, and invalid menu selections.

## Images
