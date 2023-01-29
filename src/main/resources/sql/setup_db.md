# Initial setup
1. Download latest binary from [Enterprise DB Postgres Binaries](https://www.enterprisedb.com/download-postgresql-binaries)
2. Extract to a directory of choice, for instance, `C:\portapps\PostgreSQL\14`
3. Initialize the DB for the first time by running the following command `initdb --username=postgres --pgdata=../data --encoding=utf8 --auth=trust`
4. Open terminal in the install directory `C:\portapps\PostgreSQL\14`, and run the command `pg_ctl --pgdata=../data start`

# Create user and database
1. Open terminal in the install directory `C:\portapps\PostgreSQL\14`
2. Execute the following commands:

~~~~sql
-- Initialize database
DROP DATABASE tbb_db;
CREATE DATABASE tbb_db;

-- Create DB user and privileges
CREATE USER tbb_owner with ENCRYPTED PASSWORD 'tbb_owner12345';
CREATE USER tbb_user with ENCRYPTED PASSWORD 'tbb_user12345';

GRANT ALL PRIVILEGES ON DATABASE tbb_db to tbb_owner;
GRANT ALL PRIVILEGES ON DATABASE tbb_db to tbb_user;
~~~~

# Subsequent start-ups
Save the following commands into an executable file for convenience (for instance, `launch_postgres.bat` in windows)
```
cd C:\portapps\PostgreSQL\14
# set PGPASSWORD=PASSWORD_IF_NEEDED
pg_ctl --pgdata=../data restart
psql.exe --username=postgres
cmd /k
```