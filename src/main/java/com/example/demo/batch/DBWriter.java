package com.example.demo.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Movies;
import com.example.demo.repository.MoviesRepository;

@Component
public class DBWriter implements ItemWriter<Movies>{

		@Autowired
		private MoviesRepository userRepository;
		
		@Override
		public void write(List<? extends Movies> users) throws Exception{
			
			System.out.println("Data saved for users:---" +users);
			userRepository.saveAll(users);
		}
}
