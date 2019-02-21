package com.ymm.web.controller;

import com.ymm.web.config.DemoConfig;
import com.ymm.web.model.Person;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

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

    @GetMapping("/person")
    public Person showPerson() {
        Person person = new Person();
        person.setAge(10);;
        person.setGender(false);
        person.setName("Sue");
        return person;
    }

    //https://www.rails365.net/articles/cong-kua-yu-dao-cors-yi
    /*
    GET方法为简单请求，进行跨域访问时，服务器会接收到请求并返回内容，只是浏览器进行拦截不显示
     */
    @GetMapping("/webInfo")
    public String webInfo(){
        return demoConfig.getIp() + ":" + demoConfig.getPort();
    }

    /*
    无参数的POST方法为简单请求，进行跨域访问时，服务器会接收到请求并返回内容，
    只是浏览器进行拦截不显示。如果服务器不允许跨域请求，此方法被调用后，仍然会进行逻辑处理。有风险漏洞。
     */
    @PostMapping("/webInfo")
    public String updateWebInfo() {
        System.out.println(demoConfig.getIp() + ":" + demoConfig.getPort());
        return "update succeed";
    }

    /*
    此POST方法接收application/x-www-form-urlencoded类型参数，且为简单请求。
    进行跨域访问时，服务器依然会接收到请求并返回内容，只是浏览器进行拦截不显示。
    如果服务器不允许跨域请求，此方法调用后，仍然会进行逻辑处理。有风险漏洞。
     */
    @PostMapping("/post-test1")
    public String postTest1(@RequestParam(value = "name") String name) {
        System.out.println("change name(1)");
        return "postTest1 succeed";
    }

    /*
    此POST方法接收application/json类型参数，由于contentType，此请求为复杂请求。
    进行跨域访问时，会由浏览器先发送一个OPTIONS请求，进行方法验证(是否支持此方法)。
    由于服务器不允许跨域请求，浏览器收到OPTIONS响应后，不会再进行后续请求(真正的POST请求)，所以服务器端不会进行逻辑处理。无风险漏洞。
     */
    @PostMapping("/post-test2")
    public String postTest2(@RequestBody Map<String, Object> map) {
        map.forEach((a, b) -> {
            System.out.println("key: " + a + "; value: " + b.toString());
        });
        return "postTest2 succeed";
    }

    /*
    @RequestParam映射application/x-www-form-urlencoded类型请求参数
    当Method为PUT时，需要配合filter(HttpPutFormContentFilter)，否则@RequestPraram值为null
     */
    @PutMapping("/test/{id}")
    public String test(@PathVariable(value = "id") int id, @RequestParam(value = "name") String name) {
        System.out.println("id: " + id );
        return "OK";
    }

    /*
    @RequestBody映射application/json类型请求参数
     */
    @PutMapping("/test2/{id}")
    public String test2(@PathVariable(value = "id") int id, @RequestBody Map<String, Object> map) {
        System.out.println("id: " + id);
        map.forEach((a, b)->{
            System.out.println("key: " + a + "; value: " + b.toString());
        });
        return "OK2";
    }

    /*
    设置跨域注解，允许跨域访问。POSTMAN为客户端软件不会进行同源检测(浏览器安全策略)，所以PM没有问题。
     */
    @CrossOrigin(origins = "*")
    @PutMapping("/test3/{id}")
    public String test3(@PathVariable(value = "id") int id) {
        System.out.println("id: " + id);
        return "update succeeded";
    }

}
