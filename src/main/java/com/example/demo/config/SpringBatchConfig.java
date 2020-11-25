package com.example.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.example.demo.model.Movies;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

		@Bean
		public Job job(JobBuilderFactory jobBuilderFactory,
								StepBuilderFactory stepBuilderFactory,
								ItemReader<Movies> itemReader,
								ItemProcessor<Movies,Movies> itemProcessor,
								ItemWriter<Movies> itemWriter) {
				
				Step step = stepBuilderFactory.get("ETL-FILE-LOAD")
									.<Movies,Movies>chunk(1000)
									.reader(itemReader)
									.processor(itemProcessor)
									.writer(itemWriter)
									.build();
									
				return jobBuilderFactory.get("ETL_LOAD")
								.incrementer(new RunIdIncrementer())
								.start(step)
								.build();
		}
		
		@Bean
		public FlatFileItemReader<Movies> fileItemReader(){
			
			FlatFileItemReader<Movies> flatFileItemReader = new FlatFileItemReader<>();
			flatFileItemReader.setResource(new FileSystemResource("src/main/resources/movies.csv"));
			flatFileItemReader.setName("CSV_READER");
			flatFileItemReader.setLinesToSkip(1);
			flatFileItemReader.setLineMapper(lineMapper());
			
			return flatFileItemReader;
		}
		
		@Bean
		public LineMapper<Movies> lineMapper() {
			
			DefaultLineMapper<Movies> defaultLineMapper = new DefaultLineMapper<>();
			DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
			lineTokenizer.setDelimiter(",");
			lineTokenizer.setStrict(false);
			lineTokenizer.setNames(new String[] {"movieId","title","genres"});
			
			BeanWrapperFieldSetMapper<Movies> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
			fieldSetMapper.setTargetType(Movies.class);
			defaultLineMapper.setLineTokenizer(lineTokenizer);
	        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

			return defaultLineMapper;
		}
}

