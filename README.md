# Assignment 1 - Group Expenses

This project was made for assignment 1 in the course Mobile and Wearable Programming - IMT3673

## Functionality

This app can be used to settle debts between friends. The app consists of 3 screens (fragments).

### 1. The Main View

There are several things in the main view:

* The total amount of expenses that have been made

* The average amount that everyone has to pay

* The table of expenses, listing each person and how much they have spent

* A button to add new expenses

* A button to settle the debts

![Main View](https://i.ibb.co/Wp6n18t/Main.jpg)

### 2. The Data Entry View

In this view, one can add a new expense, either to an existing person or a new person joining the group. Three fields must be filled to make a new entry:

* Name

* Amount

* Description

![Data Entry](https://i.ibb.co/jHZH3Lz/Data-entry.jpg)

### 3. The Settlements View

This view will display the needed transactions between specific members of the group to achieve balance of personal expenses.

![Settlements](https://i.ibb.co/h9RnrJb/Settlements.jpg)

## What could have been done better

The design of the app leaves a lot to be desired. Everything used is in its default format, with no form or color changes. This design could be improved upon by picking a different palette of colors to dodge the oversaturated android theme. The form could also be improved by using Material Design principles instead of the basic android provided form.

In the data entry form one will find some very annoying errors. These make up for some very bad User Experience, as they show up at what one may consider inappropriate times.

In the settlements view, a lot could be improved by using another way to show the data instead of a simple table view.

## Complaints on the assignment

Overall, the assignment had a very clear definition of what must be done and where it must be done and the tests provided a lot of support in knowing when a feature was implemented successfully. Apart from that, there are some downfalls.

1. The specs require usage of only one error message in the data entry view. This makes up for bad UX, as the user is always informed of the problem from the same part of the screen. Apart from hindering the UX, this makes it even harder for the developer to decide when and what errors to show. Showing personalized errors for each entry in the form, underneath its position, would improve readability.

2. The specs require that the "Add" button in the data entry view must be disabled if an improper name has been used for the "Name" field in the form. This makes sense at first, but it makes the "sanitizeName" function practically useless. The aforementioned function is paid a lot of attention in the spec, only to end up being fairly useless in the finished version of the app.