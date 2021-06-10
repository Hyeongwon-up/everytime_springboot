#!/bin/bash
#HOME=d:/data
DIR=$HOME/docker/db_maria_board

#echo $DIR

mkdir -p $DIR
mkdir -p $DIR/conf
mkdir -p $DIR/db
mkdir -p $DIR/init.script

cat <<-EOF > "$DIR/init.script/a_user.sql"
# 루트 유저 로컬 접속 허용
ALTER USER 'root'@'localhost' IDENTIFIED BY 'root';

# 디비 스페이스 생성 및 캐릭터셋 설정
CREATE DATABASE \`ojt_board\` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

#airi 계정 생성 및 닥커 접속, 외부 접속 허용
create USER 'tomato'@'%' IDENTIFIED BY 'airi1234';

# 디비 스페이스 공간 접근 허용
grant all privileges on ojt_board.* to tomato@'%' identified by 'airi1234';

flush privileges;
EOF

CMD="docker run -d -p 3306:3306 --name db_maria_board -v /tmp/mysql.sock:/tmp/mysql.sock -v $DIR/db:/var/lib/mysql -v $DIR/init.script:/docker-entrypoint-initdb.d -eMARIADB_ROOT_PASSWORD=root mariadb/server:10.4"

echo $CMD
eval $CMD

docker ps

echo "sleep 30 seconds until db starts..."
echo "==============================================="
echo "==IF YOU DELETE DOCKER IMAGES AND SETTINGS...=="
echo "docker stop db_maria_board"
echo "docker rm db_maria_board"
echo "rm -rf ~/docker/db_maria_board"
echo "==============================================="
echo "docker exec -it maria /bin/bash "
echo "mysql -u airi_selectonly -p air_dev1"
echo "show full columns from client;"
