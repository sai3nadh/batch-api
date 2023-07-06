package com.java.batchapi;



import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchJobConfig {
	
	
	 private final JobBuilderFactory jobBuilderFactory;
	    private final StepBuilderFactory stepBuilderFactory;
	    private DataSource dataSource = null;

	    @Autowired
	    public BatchJobConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, DataSource dataSource
	            ) {
	        this.jobBuilderFactory = jobBuilderFactory;
	        this.stepBuilderFactory = stepBuilderFactory;
	        this.dataSource = dataSource;
	    }

	    @Bean
	    public Job batchJob(Step batchStep) {
	        return jobBuilderFactory.get("batchJob")
	                .start(batchStep)
	                .build();
	    }

	    @Bean
	    public Step batchStep(ItemReader<Book> itemReader, ItemProcessor<Book, Book> itemProcessor,
	            ItemWriter<Book> itemWriter) {
	        return stepBuilderFactory.get("batchStep")
	                .<Book, Book>chunk(10)
	                .reader(itemReader)
	                .processor(itemProcessor)
	                .writer(itemWriter)
	                .build();
	    }

	    @Bean
	    public FlatFileItemReader<Book> itemReader() {
	        FlatFileItemReader<Book> reader = new FlatFileItemReader<>();
	        reader.setResource(new ClassPathResource("input.csv"));
	        reader.setLineMapper(lineMapper());
	        return reader;
	    }

	    @Bean
	    public LineMapper<Book> lineMapper() {
	        DefaultLineMapper<Book> lineMapper = new DefaultLineMapper<>();
	        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
	        tokenizer.setNames("name", "author");
	        lineMapper.setLineTokenizer(tokenizer);
	        lineMapper.setFieldSetMapper(new BookFieldSetMapper());
	        return lineMapper;
	    }

	    @Bean
	    public ItemProcessor<Book, Book> itemProcessor() {
	        return Book -> {
	            
	            return Book;
	        };
	    }

	    @Bean
	    public ItemWriter<Book> itemWriter() {
	        JdbcBatchItemWriter<Book> writer = new JdbcBatchItemWriter<>();
	        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
	        writer.setSql("INSERT INTO Book_batch (name, author) VALUES (:name, :author)");
	        writer.setDataSource(dataSource);
	        return writer;
	    }

	
}
