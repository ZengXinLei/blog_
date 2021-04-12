package com.example.blog;


import com.example.test.Test;
import com.example.util.GetClasses;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;

@SpringBootApplication
@EnableSwagger2

@MapperScan("com.example.api")
@MapperScan("com.example.mapping")
@ComponentScan("com.example.api")
@ComponentScan("com.example.mapping")
@ComponentScan("com.example.aop")
public class BlogApplication {

    public static void main(String[] args) {
//        start();

        SpringApplication.run(BlogApplication.class, args);

    }


//    public static void start(){
//        String path=ClassLoader.getSystemResource("").getPath();
//        ArrayList<Class<?>> classes=new ArrayList<>();
//        File file[]=new File(path).listFiles();
//
//        for (File f :
//                file) {
//            if(!f.isDirectory()){
//                if(f.getName().split("\\.").length<=1){
//                    continue;
//                }else if(f.getName().split("\\.")[1].equals("class")){
//                    try {
//                        classes.add(Class.forName(f.getName().split("\\.")[0]));
//                    } catch (ClassNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }else {
//                //递归遍历
//                directoryTraversal(path,f.getName(),classes);
//            }
//        }
//
//        for (Class<?> c:
//             classes) {
//            Method[] methods = c.getDeclaredMethods();
//            for (Method m :
//                    methods) {
//                if(m.isAnnotationPresent(GetClasses.class)){
//                    System.out.println(m.getName());
//                }
//            }
//        }
//
//    }
//    public static void directoryTraversal(String filePath,String path, ArrayList<Class<?>> className){
//
//        File file[]=new File(filePath+"\\"+path).listFiles();
//        for (File f:
//                file){
//            if(f.isDirectory()){
//                directoryTraversal(filePath,path+"\\"+f.getName(),className);
//            }else {
//                if(f.getName().split("\\.").length<=1){
//                    continue;
//                }else if(f.getName().split("\\.")[1].equals("class")){
//                    try {
//                        className.add(Class.forName(path.replaceAll("(\\\\)",".")+"."+f.getName().split("\\.")[0]));
//                    } catch (ClassNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        }
//    }
}
