#!/bin/bash
# author: chunghwa
# github: https://github.com/chunghwa-2018/

# server_array=('8081', '8082', '8083', '8084', '8085', '8086')

http post http://localhost:8081/actuator/shutdown
http post http://localhost:8082/actuator/shutdown
http post http://localhost:8083/actuator/shutdown
http post http://localhost:8084/actuator/shutdown
http post http://localhost:8005/actuator/shutdown
http post http://localhost:8006/actuator/shutdown

