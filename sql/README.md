# SQL with Postgres

## Setup

### Requirements and Initialization for PG Admin and PostgreSQL

To set up your environment, ensure the following:

- **Docker Desktop** is installed on your machine.
- To start the PG Admin and PostgreSQL containers, use the command:
  ```bash
  docker-compose up -d
  ```
  PG ADMIN will be accessible at `localhost:8888` by default.

For detailed steps on adding a PostgreSQL server to PG ADMIN, refer to [this guide](https://medium.com/@vishal.sharma./run-postgresql-and-pgadmin-using-docker-compose-34120618bcf9).

### Accessing PSQL in the Docker Container

Once the PostgreSQL container is running, access it using the command:
```bash
docker exec -it <container_id> psql <psql_properties>
```
- To log in as the default `postgres` user, type:
  ```bash
  docker exec -it <container_id> psql -U postgres
  ```

## Mockaroo

An excellent resource for generating mock data is [Mockaroo](https://www.mockaroo.com/). Data obtained can be incorporated into your database through migrations, scripts, or other methods.

---

### Unified Command Menu for Basic PSQL Operations

#### Accessing PSQL
- Open the psql terminal:
  ```bash
  psql
  ```
- Exit psql:
  ```bash
  \q
  ```

#### Shell Commands in PSQL
- Clear the screen:
  ```bash
  \! cls
  \! clear
  ```
  The `\!` operator executes shell commands directly from the psql terminal.

#### Getting Help
- General help:
  ```bash
  \h
  ```
- Help with psql commands in Windows shell:
  ```bash
  psql --help
  ```

#### Connecting to Databases
- Open the psql terminal:
  ```bash
  psql -U <username>
  ```
  Replace `<username>` with your PostgreSQL username.

- Connect to a specific database:
  ```bash
  psql -h <host> -U <user> -p <port> <db_name>
  ```
  Example:
  ```bash
  psql -h localhost -U postgres -p 5432 test_database
  ```
  This connects directly to the specified database.

#### Database Navigation
- Switch between databases:
  ```bash
  \c <db_name>
  ```
- List all databases:
  ```bash
  \l
  ```

#### Working with Tables
- List all tables and their relationships:
  ```bash
  \dt
  ```
- Simplified table list:
  ```bash
  \d
  ```
- View table details:
  ```bash
  \d <table_name>
  ```

#### Executing SQL Scripts
- Run SQL scripts from a file:
  ```bash
  \i '<absolute_path>'
  ```
  Executes all the SQL commands in the specified file.

#### Display Options
- Toggle metadata display (e.g., constraints):
  ```bash
  \t
  ```
- Enable expanded view for wide tables:
  ```bash
  \x
  ```

#### Viewing Functions
- List all functions in the current database:
  ```bash
  \df
  ```
  Useful for inspecting database functions when no external extensions are installed.

#### Other Commands
- Display query execution time:
  ```bash
  \timing
  ```


## SQL Reference

### SQL Basics

> **Note:** While SQL commands are case-insensitive, it's recommended to write commands in **UPPERCASE** for clarity. Remember to end every SQL statement with a semicolon (`;`).

### Comments in SQL

- **Single-line comments:**
  ```sql
  -- This is a single-line comment
  ```
- **Block comments:**
  ```sql
  /*
      Comment line 1
      Comment line 2
  */
  ```

### Database Operations

- **Create a database:**
  ```sql
  CREATE DATABASE <db_name>;
  ```
- **Delete a database:**
  ```sql
  DROP DATABASE <db_name>;
  ```
## Table Operations

### Creating Tables

- **Basic Table Creation:**
  ```sql
  CREATE TABLE table_name (
      id BIGSERIAL PRIMARY KEY NOT NULL,
      name VARCHAR(100) NOT NULL,
      date_of_birth DATE NOT NULL
  );
  ```
  > **Note:** Each column is defined with its name, data type, and constraints. Columns are separated by commas. For a list of available data types, refer to the [PostgreSQL documentation](https://www.postgresql.org/docs/current/datatype.html).

- **Create a Table If It Doesn't Exist:**
  ```sql
  CREATE TABLE IF NOT EXISTS table_name (
      -- Define columns here
  );
  ```

### Adding Comments

- **Comment on a Table:**
  ```sql
  COMMENT ON TABLE table_name IS 'Table that stores information about entity x.';
  ```
- **Comment on a Column:**
  ```sql
  COMMENT ON COLUMN table_name.column_name IS 'Represents the property x of the table.';
  ```

---

## Data Manipulation

### Inserting Data

- **Insert Values into a Table:**
  ```sql
  INSERT INTO table_name (column_a, column_b)
  VALUES
      ('Value 00', 'Value 01'),
      ('Value 10', 'Value 11');
  ```
  > **Note:** For date fields, prefix the value with `DATE`:
  ```sql
  INSERT INTO users (date_of_birth)
  VALUES
      (DATE '1999-12-30'),
      (DATE '2007-03-15');
  ```

### Selecting Data

- **Basic SELECT Statements:**
  ```sql
  SELECT * FROM users; -- Returns all columns
  SELECT first_name, last_name FROM users; -- Returns specific columns
  ```
- **Ordering Results:**
  ```sql
  SELECT * FROM users ORDER BY country; -- Default is ASC
  SELECT * FROM users ORDER BY country DESC; -- Descending order
  SELECT * FROM users ORDER BY country, id;
  ```
- **Limiting Results:**
  ```sql
  SELECT * FROM users LIMIT 10;
  ```
- **Paginated Search:**
  ```sql
  SELECT * FROM users ORDER BY id ASC LIMIT 10 OFFSET 90; -- Rows 91 to 100
  ```
- **Distinct Values:**
  ```sql
  SELECT DISTINCT country FROM users ORDER BY country;
  ```

### Filtering Data

- **Using the WHERE Clause:**
  ```sql
  SELECT * FROM users WHERE age >= 20 AND age <= 40 ORDER BY age;
  ```
- **Comparison Operators:**
  ```sql
  SELECT 1 >= 1; -- true (t)
  SELECT 1 <> 2; -- true (<> means not equal)
  ```
- **IN Operator:**
  ```sql
  SELECT * FROM users WHERE age IN (20, 30, 40);
  ```
- **BETWEEN Operator:**
  ```sql
  SELECT * FROM users WHERE date_of_birth BETWEEN '2000-01-10' AND '2010-12-30';
  ```
- **LIKE and ILIKE Operators:**
  ```sql
  SELECT * FROM users WHERE country_of_birth LIKE '%ta%'; -- Contains 'ta'
  SELECT * FROM users WHERE country_of_birth ILIKE 'p%'; -- Starts with 'p' or 'P'
  ```
  > **Efficiency Note:** Use `=` for exact matches without wildcards for better performance.

### Aggregate Functions and Grouping

- **Common Aggregate Functions:**
  - `COUNT(*)`
  - `MAX(expression)`
  - `MIN(expression)`
  - `AVG(expression)`
  - `SUM(expression)`
  - `ROUND(expression, decimal_places)`

- **Examples:**
  ```sql
  SELECT MAX(price) FROM cars;
  SELECT ROUND(MAX(price), 2) FROM cars;
  SELECT make, MIN(price), MAX(price), AVG(price)
  FROM cars
  GROUP BY make
  ORDER BY make;
  ```

- **GROUP BY Clause:**
  ```sql
  SELECT country_of_birth, COUNT(*)
  FROM users
  GROUP BY country_of_birth
  ORDER BY COUNT(*) DESC;
  ```

- **HAVING Clause:**
  The HAVING clause applies filters to aggregated results. For filtering a specific aggregated function, consider using the FILTER operator.
  ```sql
  SELECT country_of_birth, COUNT(*)
  FROM users
  GROUP BY country_of_birth
  HAVING country_of_birth ILIKE 'B%'
  ORDER BY COUNT(*) DESC;
  ```
  > **Note:** All non-aggregated columns in the `SELECT` statement must be included in the `GROUP BY` clause.


## Aliases and Calculations

### Using Aliases
Aliases allow you to create temporary names for columns or calculations within your queries, making them easier to read and interpret.
```sql
SELECT 
    id, make, model, price, 
    price * 0.1 AS discount_value, 
    price - (price * 0.1) AS final_price 
FROM cars;
```

### Mathematical Operations
SQL supports various mathematical operations:
```sql
SELECT 1 + 10;     -- 11
SELECT 10 % 3;     -- 1 (modulus)
SELECT 2 ^ 10;     -- 1024 (exponentiation)
SELECT 5!;         -- 120 (factorial)
```

## Handling NULL Values

### COALESCE Function
The `COALESCE` function provides a fallback value when encountering `NULL`.
```sql
SELECT id, first_name, COALESCE(email, 'NOT PROVIDED') AS email 
FROM users;
```

### Avoiding Division by Zero
Use `NULLIF` to avoid division errors:
```sql
SELECT COALESCE(10 / NULLIF(divisor, 0), 0) 
FROM table_name;
```
> **NULLIF(x, y)** returns `NULL` if `x = y`, otherwise returns `x`.

## Date and Time Functions

### Current Timestamp
Retrieve the current date and time:
```sql
SELECT NOW();
```

### Date Arithmetic
Perform operations on dates and times:
```sql
SELECT NOW() + INTERVAL '10 DAYS'; -- Adds 10 days
SELECT NOW() - INTERVAL '1000 YEARS'; -- Subtracts 1000 years
```

### Casting Timestamps
Convert timestamps to specific formats:
```sql
SELECT NOW()::DATE; -- 'YYYY-MM-DD'
SELECT NOW()::TIME; -- 'HH:MM:SS'
```

### Extracting Date Parts
Retrieve specific components of a date:
```sql
SELECT EXTRACT(DOW FROM NOW());   -- Day of the week
SELECT EXTRACT(MONTH FROM NOW()); -- Month
```

### Calculating Age
Calculate age based on a specific date:
```sql
SELECT AGE(NOW(), date_of_birth) AS current_age 
FROM users 
ORDER BY current_age;
```

## Modifying Data

### Updating Data

#### Update Specific Columns
```sql
UPDATE users 
SET first_name = 'New Name', email = 'newemail@example.com' 
WHERE id = 1000;
```

#### Update All Rows
```sql
UPDATE users SET first_name = 'Test';
```

### Deleting Data

#### Delete Specific Rows
```sql
DELETE FROM users WHERE id = 1000;
```

#### Delete All Rows from a Table
```sql
DELETE FROM users;
```

### Handling Conflicts
Use `ON CONFLICT` to handle duplicate entries:
```sql
INSERT INTO users (id, email) 
VALUES (1, 'test@gmail.com') 
ON CONFLICT (id) DO NOTHING;
```
> **Note:** `ON CONFLICT` requires a uniqueness constraint on the field.

## Constraints and Data Integrity

### Managing Constraints

#### Removing Constraints
```sql
ALTER TABLE users DROP CONSTRAINT constraint_name;
```

#### Adding Constraints
```sql
ALTER TABLE users ADD CONSTRAINT unique_username UNIQUE(username);
ALTER TABLE users ADD CONSTRAINT users_pkey PRIMARY KEY(id);
```

#### Defining ENUM-Like Constraints
```sql
ALTER TABLE users ADD CONSTRAINT gender_check 
CHECK(gender IN ('FEMALE', 'MALE', 'OTHER'));
```

### Working with NULL

#### Proper NULL Comparison
```sql
SELECT email 
FROM users 
WHERE email IS NULL;
```
> Comparing with `= NULL` returns `NULL`, not a Boolean value.

## Relationships and Joins

### Foreign Keys

#### One-to-Many Relationship
```sql
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    username VARCHAR(150) NOT NULL UNIQUE
);

CREATE TABLE cars (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    model VARCHAR(50) NOT NULL,
    make VARCHAR(50) NOT NULL,
    user_id BIGINT NOT NULL REFERENCES users(id)
);
```

#### One-to-One Relationship
```sql
CREATE TABLE cars (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    model VARCHAR(50) NOT NULL,
    make VARCHAR(50) NOT NULL,
    user_id BIGINT NOT NULL UNIQUE REFERENCES users(id)
);
```
> The `UNIQUE` constraint on `user_id` enforces a one-to-one relationship.

### Joins

#### Inner JOIN
```sql
SELECT * 
FROM users 
JOIN cars ON users.id = cars.user_id;
```

#### Selecting Specific Columns with INNER JOIN
The inner join returns only rows with matches between tables.
```sql
SELECT 
    users.id, users.first_name, users.last_name, 
    cars.id AS car_id, cars.model, cars.make, cars.price 
FROM users 
INNER JOIN cars ON users.id = cars.user_id;
```

#### Left JOIN
Left join includes unmatched rows from the left table with `NULL` values for columns from the right table.
```sql
SELECT * 
FROM users 
LEFT JOIN cars ON users.id = cars.user_id;
```

#### Right JOIN
Right join includes unmatched rows from the right table with `NULL` values for columns from the left table.
```sql
SELECT * 
FROM users 
RIGHT JOIN cars ON users.id = cars.user_id;
```

## Data Export and Extensions

### Exporting Data
Export data to a CSV file:
```sql
\copy (SELECT * FROM users ORDER BY first_name) TO './results.csv' DELIMITER ',' CSV HEADER;
```
> The `\copy` command exports data to the specified path with the given delimiter and includes a header row.

### External Extensions

#### List Available Extensions
```sql
SELECT * FROM pg_available_extensions;
```

#### Using UUIDs

##### Enable UUID Extension
```sql
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
```

##### Generate a UUID
```sql
SELECT uuid_generate_v4(); -- Generates a random UUID
```
> **Note:** UUID functions become available after enabling the extension. See more about UUIDs [here](https://en.wikipedia.org/wiki/Universally_unique_identifier).

### Calculating the Length of a VARCHAR
Determine the length of a string using the `LENGTH` function:
```sql
SELECT * FROM cars WHERE LENGTH(model) >= 15; 
```

### Adding a Foreign Key Constraint to a Table
Add a foreign key referencing another table:
```sql
ALTER TABLE table_name ADD CONSTRAINT constraint_name FOREIGN KEY (referenced_column) REFERENCES referenced_table(referenced_column);
```

## Filtered Search
Perform filtered searches using conditional logic to handle optional filters. For example:

```sql
SELECT * FROM cars 
WHERE 
  (model ILIKE '%Pontiac%' OR model IS NULL) AND
  (make ILIKE '%Grand Prix%' OR make IS NULL)
ORDER BY model, make ASC;
```

- This query demonstrates how to filter results based on specific criteria (`model` and `make`) while accommodating null values.
- It can be adapted for use in database-driven applications with ORMs or APIs.

---

## Removing Duplicates in Paginated Queries

When working with databases that may contain duplicate records for the same ID (e.g., due to design decisions such as avoiding intermediate tables), you can perform paginated queries while ignoring duplicates by using the following SQL clause:

```sql
SELECT DISTINCT ON (product_id) product_id, name, value 
FROM product 
ORDER BY product_id ASC 
OFFSET 0 LIMIT 10;
```

### Explanation:
- **`DISTINCT ON (product_id)`** ensures that only the first record for each unique `product_id` is included in the result set.
- **`ORDER BY product_id ASC`** specifies the sorting order for selecting distinct values.
- **`OFFSET 0 LIMIT 10`** enables pagination by retrieving a specific range of rows (e.g., the first 10 results).

This approach is useful for maintaining data consistency in paginated responses while eliminating duplicates based on the `product_id`.

---

### Notes
- The `DISTINCT ON` clause is specific to **PostgreSQL**. For other databases, alternative approaches may be needed, such as using subqueries or window functions.
- These techniques can be seamlessly integrated into APIs or ORMs for efficient data retrieval in web applications.

# Performance

## Planning Time
Planning time refers to the time the PostgreSQL optimizer spends processing a query before executing it. The main steps are:

- Validates the SQL syntax and converts it into an internal data structure.
- Verifies the existence of tables and data types.
- The optimizer evaluates different execution plans such as Sequential Scan, Index Scan, Hash Join, and Nested Loop. These decisions are based on statistics generated by the `ANALYZE` command.
- Based on the estimated cost (an abstract value, not actual time), the optimizer determines the most appropriate plan for executing the query.

Factors affecting planning time include complex joins, a large number of columns, and outdated statistics.

## Execution Time
Execution time is the actual time taken to execute the query, including fetching values, applying calculations, sorting, grouping, and other operations. For very small and simple queries, planning time can be comparable to or even greater than execution time. Generally, however, execution time is longer than planning time.

## Analyze
The `ANALYZE` command updates table statistics, which are essential for the optimizer to make informed decisions:

Update statistics for an entire table:
```sql
ANALYZE table_name;
```

Update statistics for a specific column:
```sql
ANALYZE table_name(column_name);
```

The command gathers metrics such as row counts, column distributions, and common values. These statistics are stored in PostgreSQL's statistics catalog (`pg_statistic`). Outdated statistics, caused by significant data updates or deletions, can result in suboptimal execution plans, negatively impacting query performance.

## Vacuum
The `VACUUM` command marks dead rows left by DELETE or UPDATE operations as reusable, reclaiming database space and preventing table bloat:
```sql
VACUUM;
```

### Autovacuum
The `autovacuum` process automatically removes dead rows and generates new statistics. It is usually triggered after DELETE or INSERT operations. To check its status:
```sql
SHOW autovacuum;
```

To enable or disable `autovacuum` globally:
```sql
SET autovacuum = 'on';
SET autovacuum = 'off';
```

## Bloats
Bloat occurs when PostgreSQL accumulates unused space due to DELETE and UPDATE operations. Dead rows are marked as unusable but are not immediately removed. Instead, new rows are created for updates, leaving behind unused space. This behavior is part of PostgreSQL's MVCC (Multiversion Concurrency Control) system, which allows concurrent transactions without locking.

Excessive bloat can degrade query performance and consume unnecessary disk space. To address this issue, use the `VACUUM` command. For severe cases, use `VACUUM FULL` to clean the entire table:
```sql
VACUUM FULL table_name;
```

The `VACUUM FULL` command eliminates unused space but locks the table during the process, making it suitable for extreme bloat scenarios.

## Explain Command
The `EXPLAIN` command provides information about query execution plans. Example:
```sql
EXPLAIN ANALYZE SELECT * FROM file;
```
Output:
```sql
                                          QUERY PLAN
-----------------------------------------------------------------------------------------------
 Seq Scan on file  (cost=0.00..1.03 rows=3 width=62) (actual time=0.361..0.444 rows=3 loops=1)
 Planning Time: 4.615 ms
 Execution Time: 1.848 ms
(3 rows)

Time: 16.214 ms
```

In the example above, the CPU time to execute the query is the sum of Planning Time and Execution Time, totaling 6.463 ms. The remaining time is spent on communication between the database and the API (network time).
