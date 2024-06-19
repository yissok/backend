#docker compose start mysql container
#runmysql command to start mysql at port 3306 localhost
MYSQL_PASSWORD=mysqlr00t
DB_NAME=andrea_test_db
PWD=$(pwd)
MYSQL_PASSWORD=$MYSQL_PASSWORD docker-compose up --build -d --remove-orphans --force-recreate
docker exec -it mysql8 mysql -uroot -p$MYSQL_PASSWORD -h localhost --port 3306 --protocol=TCP -e "CREATE DATABASE IF NOT EXISTS $DB_NAME"