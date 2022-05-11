package com.example.demo;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunctionController {

	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/func")
	public Polynomial functionize(@RequestParam(value = "arr", defaultValue = "") String arr) {
		double[][] arr1 = decode(arr);
		System.out.println("HEY");
		// double[][] arr = {{1,2},{2,3},{3,4}};
		Polynomial result = Functionizer.functionize(arr1);
		result.id = counter.incrementAndGet();
		return result;
	}

	public static double[][] decode(String str) {
        str=str.substring(2, str.length()-2);
        String[] strs = str.split("\\],\\[");
        double[][] dbl = new double[strs.length][2];
        for (int i = 0; i < strs.length; i++) {
			dbl[i] = Arrays.stream(strs[i].split(",")).mapToDouble(Double::parseDouble).toArray();
        }
		return dbl;
	}

}
