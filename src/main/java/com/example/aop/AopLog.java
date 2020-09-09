package com.example.aop;



import com.example.enity.Log;
import com.example.mapping.LogMapping;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;


import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Date;


/**
 * @Author: zxl
 * @Time: 2020/06/18 15:59:17
 * @system: ASUS
 **/
@Aspect
@Component
public class AopLog {
    @Autowired
    LogMapping logMapping;

    @Pointcut("execution(* com.example.api..*.*(..))")
    public  void log(){

    }
    @Pointcut("execution(* com.example.api.LogApi.*(..))")
    public void exclude(){

    }
    @Before("log() && @annotation(apiOperation) &&!exclude()")
    public void infoApi(JoinPoint joinPoint,ApiOperation apiOperation) {
        Logger logger= LogManager.getLogger(joinPoint.getTarget().getClass().getName());

        Log log=new Log();
        log.setLAction(apiOperation.value());
        log.setLClass(joinPoint.getTarget().getClass().getName());
        log.setLLevel("INFO");
        log.setLMethod(joinPoint.getSignature().getName());
        log.setLTime(new Date().getTime()/1000);
        log.setLType("Api");
        logMapping.insertLog(log);
        logger.info(String.format("%s   调用方法[%s]",apiOperation.value(),joinPoint.getSignature().getName()));

    }
}
