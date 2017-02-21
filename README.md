# spring-security

This project implements Spring Security with Cassandra installed locally.
It also has a small service for *greetings*.

## Requirements
- Java 8
- Gradle 3
- Cassandra 3 locally in a single node

## Steps

### 1. Create the keyspace
```
cqlsh> CREATE KEYSPACE taleme_ks WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 1};
cqlsh> USE taleme_ks;
```

### 2. Run DDL
taleme_ddl.sql

### 3. Run DML
taleme_dml.sql


[My site](http://topi.cafeconleche.xyz)
