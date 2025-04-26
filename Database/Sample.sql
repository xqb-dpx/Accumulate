CREATE DATABASE database_name;
USE database_name;
----
ALTER DATABASE database_name
SET OFFLINE/ONLINE
MODIFY FILE (NAME = logical_name, SIZE = new_size);
----
DROP DATABASE database_name;
----
CREATE TABLE table_name (
[PK] = INT PRIMARY KEY,
[FK] = INT CHECK (condition) UNIQUE,
[Name] = NVARCHAR(limit),
[Phone] = INT NOT NULL,
[Birthday] = DATE DEFAULT GETDATE(),
[Score] = FLOAT,
CONSTRAINT condition FOREGN KEY (fk_entire) REFERENCES table(column),
----
ALTER TABLE table_name
ADD column_name data_type
DROP COLUMN column_name
MODIFY COLUMN column_name data_type
----
DROP TABLE tableName;
TRUNCATE TABLE table_name;
----
CREATE [UNIQUE] INDEX index_name[id]
ON table_name (column_name[id]);
----
DROP INDEX index_name;
----
UPDATE table_name
SET column1 = value1, columnN = valueN
WHERE condition;
----
INSERT INTO target_table (column1, columnN)
VALUES (value1, valueN);
----
SELECT [*][DISTINCT][[TOP] number [PERCEN] *] column1, columnN AS new_name
INTO new_table
FROM table_name
[[INNER JOIN] [RIGHT JOIN] [RIGHT OUTER JOIN] [LEFT JOIN] [LEFT OUTER JOIN] target_table [ON] condition]
WHERE condition [[LIKE] condition '%_'] [[AND/OR] condition] [IN selection] [[NOT][BETWEEN] column [AND] column]
GROUP BY column1, columnN [ASC/DESC]
HAVING condition
ORDER BY column1, columnN [ASC/DESC]
LIMIT number
OFFSET number
----
DELETE FROM table_name
WHERE condition;