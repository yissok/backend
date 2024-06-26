#MYSQL_PASSWORD=mysqlr00t
#DB_NAME=andrea_test_db
#MYSQL_PASSWORD=$MYSQL_PASSWORD docker-compose up --build -d --remove-orphans --force-recreate
#docker exec -it mysql8 mysql -uroot -p$MYSQL_PASSWORD -h localhost --port 3306 --protocol=TCP -e "CREATE DATABASE IF NOT EXISTS $DB_NAME"



docker-compose up --build -d --remove-orphans --force-recreate
sleep 3
docker exec -it my_mongodb mongo --username mongouser --password mongor00t \
--eval "db = db.getSiblingDB('test_db'); \
db.books.insertMany([ \
    {title: 'Book One', author: 'Author One'}, \
    {title: 'Book Two', author: 'Author Two'} \
]);"
aws --endpoint-url=http://localhost:4566 s3 mb s3://123412341234-encrypted-note-trees
aws --endpoint-url="http://localhost:4566" s3 sync "src/test/java/com/example/demo/unit" s3://123412341234-encrypted-note-trees

#docker container run -d --name my_cli_mongo \
#-p 27017:27017 \
#-e MONGO_INITDB_ROOT_USERNAME=mongouser \
#-e MONGO_INITDB_ROOT_PASSWORD=mongor00t \
#mongo:4.4.6