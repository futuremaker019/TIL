## CLI

### 접속

```vim
mysql -u [사용자 ID] -p -h [host] -P [포트번호] -s [socket] -D [DB명]
```

<br>

### 전체 테이블 삭제

```sql
mysql> SET @tables = NULL;
mysql> SELECT GROUP_CONCAT(table_schema, '.', table_name) INTO @tables
  FROM information_schema.tables
  WHERE table_schema = 'DB이름 여기에 입력'; -- specify DB name here.

mysql> SET @tables = CONCAT('DROP TABLE ', @tables);
mysql> PREPARE stmt FROM @tables;
mysql> EXECUTE stmt;
mysql> DEALLOCATE PREPARE stmt;
```
