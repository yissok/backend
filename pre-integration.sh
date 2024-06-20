#MYSQL_PASSWORD=mysqlr00t
#DB_NAME=andrea_test_db
#MYSQL_PASSWORD=$MYSQL_PASSWORD docker-compose up --build -d --remove-orphans --force-recreate
#docker exec -it mysql8 mysql -uroot -p$MYSQL_PASSWORD -h localhost --port 3306 --protocol=TCP -e "CREATE DATABASE IF NOT EXISTS $DB_NAME"



docker-compose up --build -d --remove-orphans --force-recreate
sleep 1
docker exec -it my_mongodb mongo --username mongouser --password mongor00t \
--eval "db = db.getSiblingDB('test_db'); \
db.books.insertMany([ \
    {title: 'Book One', author: 'Author One'}, \
    {title: 'Book Two', author: 'Author Two'} \
]);"

#docker container run -d --name my_cli_mongo \
#-p 27017:27017 \
#-e MONGO_INITDB_ROOT_USERNAME=mongouser \
#-e MONGO_INITDB_ROOT_PASSWORD=mongor00t \
#mongo:4.4.6