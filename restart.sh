#!/bin/bash
project_name=$1

if [ -n "${project_name}" ]; then

	pid=$(ps -ef | grep java | grep ${project_name} | awk '{print $2}')
	if [ -n "$pid" ]; then
		kill -9 ${pid}
		sleep 2
	fi

	


	start_project() {
		if [ -e /alibaba/spring-cloud-example/log/${project_name} ]; then
			cd /alibaba/spring-cloud-example/log/${project_name}/
			sudo rm -rf ${project_name}*.log
			echo "===============================》rm logs"
		else
			sudo mkdir /alibaba/spring-cloud-example/log/${project_name}
		fi

		sudo chmod 777 -R /alibaba/spring-cloud-example/log/${project_name}
		source /etc/profile
		exec nohup java -jar -Xmx512m -Xms512m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/alibaba/spring-cloud-examle/log/${project_name}/ -Djava.io.tmpdir=/alibaba/spring-cloud-example/tmp -javaagent:/alibaba/spring-cloud-example/tools/agent/skywalking-agent.jar=agent.service_name=${project_name}  -Dspring.profiles.active=dev /alibaba/spring-cloud-example/app/${project_name}*.jar >/alibaba/spring-cloud-example/${project_name}/${project_name}.log 2>&1 &

		pid=$(ps -ef | grep java | grep ${project_name} | awk '{print $2}')

		if [ -n "$pid" ]; then
			echo "===============================》project_name: ${project_name},  start success , pid: $pid "
		else
			echo "===============================》project_name: ${project_name},  start fail"
		fi
	}
	start_project

else
	echo "===============================》project_name is null, plase retry and write project_name"
fi
