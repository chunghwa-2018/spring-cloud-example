#!/bin/bash
# author: chunghwa
# github: https://github.com/chunghwa-2018/

server_array=('8081' '8082' '8083' '8084' '8085' '8086')
 
for loop in "${server_array[@]}"
do
    http post http://localhost:$loop/actuator/shutdown
done


# APP_PID_TEMP=$(jps -l | grep spring-cloud-)
# eval "$(echo "$APP_PID_TEMP" | awk '{printf("pid=%s;pidname=%s",$1,$2)}')"
# echo "The load project is $pidname, pid is:$pid"
# APP_ID=$pid
# echo $APP_ID


for k in `jps -l | grep spring | awk '{print($1,$2)}'`
do
    echo $k
done

# APP_PID_TEMP2=`jps -l | grep spring | awk '{ print}'`

