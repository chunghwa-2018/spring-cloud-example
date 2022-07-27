package com.ch.spring.boot.eureka.service.impl;

import com.ch.spring.boot.eureka.constant.StudentServiceConstant;
import com.ch.spring.boot.eureka.model.Student;
import com.ch.spring.boot.eureka.service.StudentsService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * <p>学生 service 接口实现类</p>
 *
 * @ClassName StudentsServiceImpl
 * @Description 学生 service 接口实现类
 * @Author zhaohongliang
 * @Date 2022-07-18 18:42
 * @Version 1.0
 */
@Service
public class StudentsServiceImpl implements StudentsService {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(StudentsServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    // ribbon 客户端负载均衡
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    // 元数据对象
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * hello
     * loadBalancerClient 客户端实现负载均衡
     *
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "helloFallback")
    public String hello() {

        ServiceInstance instance = loadBalancerClient.choose(StudentServiceConstant.STUDENT_SERVICE);
        if (null == instance) {
            return null;
        }

        // TODO 生成请求 url 的几种方式
        // String url = String.format("http://%s:%s/hello", instance.getHost(), instance.getPort());
        String url = String.format("http://%s/hello", instance.getServiceId());
        // String url = "http://STUDENT-SERVICE/hello";

        logger.info("instanceId:{}", instance.getInstanceId());
        logger.info("serviceId:{}", instance.getServiceId());
        logger.info("host:{}", instance.getHost());
        logger.info("port:{}", instance.getPort());
        logger.info("uri:{}", instance.getUri());
        logger.info("url:{}", url);

        return restTemplate.getForEntity(url, String.class).getBody();
    }

    /**
     * welcome
     * discoveryClient 元数据对象方式实现负载均衡
     *
     * @param name
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "welcomeFallback")
    public String welcome(String name) {
        // 获取服务列表
        List<String> serviceIds = discoveryClient.getServices();
        if (CollectionUtils.isEmpty(serviceIds)) {
            return null;
        }

        // 根据服务名称获取
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(StudentServiceConstant.STUDENT_SERVICE);
        if (CollectionUtils.isEmpty(serviceInstances)) {
            return null;
        }

        // TODO 使用 discoveryClient 元数据对象方式，需要自己实现负载均衡算法，此处只演示用法，所以 get(0)
        ServiceInstance instance = serviceInstances.get(0);
        // TODO 使用 @LoadBalanced，会导致 http:host:port 方式不可用，因为 @LoadBalanced 注解是根据 serviceId 来实现的，
        //      由于启动类添加了 @LoadBalanced，所以暂时使用 serviceId 方式
        // String url = String.format("http://%s:%s/welcome?name={1}", instance.getHost(), instance.getPort());
        String url = String.format("http://%s/welcome?name={1}", instance.getServiceId());
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class, name);

        // ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, name);
        //  return responseEntity.getBody();

        return responseEntity.getBody();

    }

    /**
     * welcome
     * {@link com.ch.spring.boot.eureka.EurekaConsumerApplication @LoadBalanced 注解实现负载均衡}
     *
     * @param id
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "welcomeFallback")
    public Student welcome(Long id) {
        String url = String.format("http://%s/welcome/{id}", StudentServiceConstant.STUDENT_SERVICE);
        ResponseEntity<Student> responseEntity = restTemplate.getForEntity(url, Student.class, id);
        return responseEntity.getBody();
    }

    /**
     * welcome1
     *
     * @param student
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "welcome1Fallback")
    public Student welcome1(Student student) {
        String url = String.format("http://%s/welcome1?remark={remark}", StudentServiceConstant.STUDENT_SERVICE);
        student = restTemplate.postForObject(url, student, Student.class, "秃噜扣碎否");
        return student;
    }

    /**
     * 熔断请求的错误返回方法
     *
     * @return
     */
    private String helloFallback() {
        return "error";
    }

    /**
     * 熔断请求的错误返回方法
     *
     * @param name
     * @return
     */
    private String welcomeFallback(String name) {
        return "name is error";
    }

    /**
     * 熔断请求的错误返回方法
     *
     * @param id
     * @return
     */
    private Student welcomeFallback(Long id) {
        return new Student(0L, "未知", "8888-88-88", 0);
    }

    /**
     * 熔断请求的错误返回方法
     *
     * @param student
     * @return
     */
    private Student welcome1Fallback(Student student) {
        return new Student(0L, "未知", "9999-99-99", 0);
    }
}
