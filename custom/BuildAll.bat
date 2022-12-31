@ECHO OFF
set MAVEN_OPTS=-Xmx2g -Xss1g -XX:MetaspaceSize=512M -XX:MaxMetaspaceSize=1024M

cd prototypes
call mvn clean install
cd ..

cd modules
call mvn clean install
cd ..

cd app
call mvn clean install
cd ..