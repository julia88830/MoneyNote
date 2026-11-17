# MoneyNote

A simple, general-purpose personal expense tracker in Java, built while learning. Inspired by Qianji: record any income or expense, in any category, from any account, and see where the money goes.

## Features

- Add a record: date, type (income / expense / transfer), category, account, amount, note
- View records: view all, filter by month or by category
- Monthly summary: total income, total expense, balance, totals by category and by account
- Data is saved to a CSV file, so records are still there after restart

## Project layout

MoneyNote/
README.md
.gitignore
src/main/java/MoneyRecord.java, MoneyManager.java, MoneyNoteApp.java
data/example.csv

## How to run

1. Clone this repository
2. Open it in Eclipse as a Java project (JDK 17 or newer)
3. Run `MoneyNoteApp` (the `main` method)
4. Choose from the menu:
   - `1` Add a record
   - `2` View records
   - `3` Monthly summary
   - `0` Exit

## Example data

`data/example.csv` has 6 example records. Your own records are saved in `data/records.csv`, which is not committed to the repository.

## Versions

- **v0.1 (now):** Console version: add / view / monthly summary, CSV storage.
- **v0.2:** Android app version, with an APK to download from Releases.
