# MoneyNote
A simple , general-purpose personal expense tracker in Java , built while learning. Inspired by Qianji: record my any income or expense , in any category , from any account , and see where the money gose.

## Features(planned)
- Add a record: date, type(income/expense/transfer), amount, category, account, note
- Categories are free text, not fixed: Food, Transport, Shopping, Entertainment, Study, and any category you add yourself
- Accounts: Alipay, Wechat Pay, Cash, Bank Card, Yu'e Bao, and any account you add yourself
- List records, and fliter by month or by category
- Monthly summary: total income, total expense, breakdown by category and by account
- Data stored in a CSV file for now(v0.1); a database comes in v0.2

## Versions
- **Steps 0(now):** Repository + README only.
- **v0.1:** Console app in Java - add/list/filter records, monthly summary by category and by account. Data stored in a CSV file.
- **v0.2:** SQLite + JDBC, replacing CSV storange; edit and delete records, plus budgets per category.
- **v0.3:** HTML/CSS page showing the monthly summary.

## Tech
Plain Java first(no Spring Boot yet), then SQL, then HTML/CSS - learning by building, one layer at a time.

## How to run
v0.1 is not ready yet. It will run in Eclipse as a plain Java console app; instructions will be added here when v0.1 lands.

## Project layout
```
src/Java source(v0.1: MoneyRecord, MoneyManager, MoneyNoteApp)
data/example.csv only - real personal data is never committed
sql/schema.sql(v0.2)
web/index.html(v0.3)
```

## Example use: my 50/30/20 budget
This is one way to use MoneyNote, not the whole product. My weekly allowance is 400 CNY, split 50/30/20:
- NEEDS(essentials): 200 CNY/week
- WANTS(fun/unexpected): 120 CNY/week
- SAVINGS: 80 CNY/week, toward a 1,400 CNY savings goal by 2027-01-01

In MoneyNote, that is just three categories budgets for them come in a later version. Any other budget, category, or account works the same way.

## Note
This is a learning project. Features are kept deliberately small so v0.1 can actually ship. Auto-recording from payment notifications (the way Qianji does it on Android) is out of scope for the Java console version.
