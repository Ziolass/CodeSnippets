cd subdir1
docker-compose build --no-cache --force-rm
docker-compose up -d
cd ..
docker-compose build --no-cache --force-rm
docker-compose up
cd subdir1
docker-compose down
cd ..
