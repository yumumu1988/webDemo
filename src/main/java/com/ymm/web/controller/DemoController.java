package com.ymm.web.controller;

import com.ymm.web.config.DemoConfig;
import com.ymm.web.model.Person;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private DemoConfig demoConfig;

    @GetMapping("/test1")
    public String test1(){
        return "Hello World";
    }

    @PostMapping("/test1/{name}")
    public String postTest(@PathVariable("name") String name, @RequestParam("age") Integer age, @RequestParam("gender") Boolean gender) {
        String str = "Welcome " + name +", " + (gender ? "he " : "she ") + "is " + age + " years old";
        return str;
    }

    @GetMapping("/webInfo")
    public String webInfo(){
        return demoConfig.getIp() + ":" + demoConfig.getPort();
    }

    @GetMapping("/person")
    public Person showPerson() {
        Person person = new Person();
        person.setAge(10);;
        person.setGender(false);
        person.setName("Sue");
        return person;
    }

}
