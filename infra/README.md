```bash
# Start mysql container
docker compose up -d

# Access mysql container
docker exec -it mysql_db mysql -u root -p

# Execute sql script

## Create DB
create database mydb;
use mydb;

## Create table
CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    age INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

docker-compose.yml
```yaml
version: '3.8'

services:
  mysql:
    image: mysql:8.0.35
    container_name: mysql_db
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: passw0rd1!
    ports:
      - 3306:3306
    volumes:
      - mysql_data:/var/lib/mysql
    command:
      - --character-set-server=utf8mb4
      - --collation-server=utf8mb4_unicode_ci
    networks:
      - mysql_network

volumes:
  mysql_data:
    name: mysql_data

networks:
  mysql_network:
    name: mysql_network
```