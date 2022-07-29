#!/bin/bash
# author: chunghwa
# github: https://github.com/chunghwa-2018/

# mvn clean install -Dmaven.test.skip=true

spring_cloud_example_home=~/GitHub/spring-cloud-example
eureka_server_home=${spring_cloud_example_home}/spring-cloud-eureka-server
eureka_provider_home=${spring_cloud_example_home}/spring-cloud-eureka-provider
eureka_consumer_home=${spring_cloud_example_home}/spring-cloud-eureka-consumer


eureka_server_jar=spring-cloud-eureka-server-0.0.1-SNAPSHOT.jar
eureka_provider_jar=spring-cloud-eureka-provider-0.0.1-SNAPSHOT.jar
eureka_consumer_jar=spring-cloud-eureka-consumer-0.0.1-SNAPSHOT.jar


# eureka-server
cd ${eureka_server_home}/target

echo ${eureka_server_jar} is starting...

nohup java -jar ${eureka_server_jar} --spring.profiles.active=server-1 >> /dev/null 2>&1 &
nohup java -jar ${eureka_server_jar} --spring.profiles.active=server-2 >> /dev/null 2>&1 &


# eureka-provider
cd ${eureka_provider_home}/target

echo ${eureka_provider_jar} is starting...

nohup java -jar ${eureka_provider_jar} --spring.profiles.active=provider-1 >> /dev/null 2>&1 &
nohup java -jar ${eureka_provider_jar} --spring.profiles.active=provider-2 >> /dev/null 2>&1 &


# eureka-consumer
cd ${eureka_consumer_home}/target

echo ${eureka_consumer_jar} is starting...

nohup java -jar ${eureka_consumer_jar} --spring.profiles.active=consumer-1 >> /dev/null 2>&1 &
nohup java -jar ${eureka_consumer_jar} --spring.profiles.active=consumer-2 >> /dev/null 2>&1 &





