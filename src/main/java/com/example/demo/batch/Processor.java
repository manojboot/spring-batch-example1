package com.example.demo.batch;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.demo.model.Movies;

@Component
public class Processor implements ItemProcessor<Movies,Movies> {
	
		private static final Map<String,String> DEPT_NAMES = new HashMap<>();
		
	
		
		public Movies process(Movies user) throws Exception{
			String deptCode = user.getGenres();
	        String dept = DEPT_NAMES.get(deptCode);
	        user.setDate(new Date());
	        System.out.println(String.format("Converted from [%s] to [%s]", deptCode, dept));
	        return user;
		}

}
