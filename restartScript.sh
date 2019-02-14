mvn clean install -D skipTests
today=$(date '+%Y%m%d%H%M%S')
filename="push-0.0.1-SNAPSHOT-$today.jar"
cp /home/ubuntu/lte_push/target/push-0.0.1-SNAPSHOT.jar  "/home/ubuntu/pushJar/$filename"
unlink /home/ubuntu/pushJar/push-0.0.1-SNAPSHOT.jar
ln -s "/home/ubuntu/pushJar/$filename" /home/ubuntu/pushJar/push-0.0.1-SNAPSHOT.jar
kill -9 $(lsof -t -i:8080)
cd "/home/ubuntu/pushJar/"
nohup java -jar /home/ubuntu/pushJar/push-0.0.1-SNAPSHOT.jar > pushnohuplog.out &
tail -f /home/ubuntu/pushJar/pushnohuplog.out