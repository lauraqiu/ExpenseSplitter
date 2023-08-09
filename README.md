# My Personal Project

## Expense Splitter

This application will help users to **calculate their expenses and split it among other people**. Users are able to
input the expense and the people involved and the application will calculate the amount that each person would 
need to contribute.

The expense splitter can be used mainly by **friends and roommates**, who commonly share expenses, such as household
supplies, utilities, or a restaurant bill. It can also be used by groups who are travelling on trips together,
where expenses are commonly shared. 

This project is of interest to me because I commonly find myself in situations where I split an expense with others 
and this application would be a good way to keep track of the shared expenses.

## User Stories

- As a user, I want to be able to create an expense and calculate the amount each person owes per expense.
- As a user, I want to be able to add a new expense to a list of expenses to keep track of my expenses
- As a user, I want to be able to add multiple expenses to a list of expenses
- As a user, I want to be able to delete an expense in the case it was entered incorrectly, or it has been resolved
- As a user, I want to be able to view the list of expenses to see the details of each expense
- As a user, I want to be able to pin an important expense
- As a user, I want to be prompted with the option to load data from file when the application starts 
- As a user, I want to be prompted with the option to save data to file when the application ends

# Instructions for Grader

- you can generate the first required action related to adding expenses to an expense list by entering the expense details in the text fields and clicking the Add Expense button, which adds an expense
- you can generate the second required action related to adding expenses to an expense list by clicking view expenses to view the list of expenses and selecting an expense, which gives the user the option to delete it by clicking the Delete button
- you can locate the visual component as the splash screen that is shown before the app loads
- you can save the state of the app by clicking the Quit button, which will load a popup that asks the user if they would like to save their file
- you can reload the state of my app through the popup that appears after the splash screen that asks if you would like to load a previous file

## Phase 4: Task 2
- Loaded from ./data/expenseList.json
- Saved to ./data/expenseList.json
- New expense added to expense list
- Expense deleted to expense list
- New expense added to expense list

## Phase 4: Task 3
I would refactor the ExpenseSplitterGUI class because currently there are many methods in the class, as a result, the 
class handles multiple responsibilities. As well, it has many fields. This makes it confusing for other users to understand
and makes it more difficult to find where a bug may be occurring. It would be better to adhere to the Single Responsibility 
Principle and break up the class into separate classes. To do this, I would move the functionalities and related fields 
into a new class. As a result, each class would handle a separate responsibility, such as setting up the GUI and handling the 
add and delete and view expenses functions.

As well, I would refactor the ExpenseSplitterApp as well because it also has clusters of methods that perform different functionalities.
Similarly, this reduces cohesion and makes the code harder to understand and debug as well. I would move the functionalities 
and related fields into new classes. For example, I could have a separate class that handles the console interactions with the user,
a class that handles the splash screen, and a class that handles the Json data. 