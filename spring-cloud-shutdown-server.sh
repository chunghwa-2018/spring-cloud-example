#!/bin/bash
# author: chunghwa
# github: https://github.com/chunghwa-2018/

server_array=('8081' '8082' '8083' '8084' '8085' '8086')

for loop in "${server_array[@]}"
do
    http post http://localhost:$loop/actuator/shutdown
done


