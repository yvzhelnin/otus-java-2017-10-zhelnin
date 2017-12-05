set jarfile=lesson4.jar
set vmOptions=-agentlib:jdwp=transport=dt_socket,address=14000,server=y,suspend=n -Xms512m -Xmx515m -XX:MaxMetaspaceSize=256m -verbose:gc -Dcom.sun.management.jmxremote.port=15000 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./dumps/

set serialGC=-XX:+UseSerialGC
set concMarkSweepGC=-XX:+UseConcMarkSweepGC -XX:ParallelCMSThreads=2 -XX:+CMSScavengeBeforeRemark -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70
set concMarkSweepGC2=-XX:+UseConcMarkSweepGC -XX:ParallelCMSThreads=4 -XX:+CMSParallelRemarkEnabled
set parNewGC=-XX:+UseParNewGC -XX:+ScavengeBeforeFullGC
set g1GC=–XX:+UseG1GC

echo %vmOptions%

(for %%i in (%serialGC% %concMarkSweepGC% %concMarkSweepGC2% %parNewGC% %g1GC%) do (
    echo %%i
    start java -jar %vmOptions% %%i %jarfile%
    timeout /t 180
    taskkill /im "java.exe" /f
))
