@ECHO OFF
set JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8001" -Dspring-boot.run.profiles=local