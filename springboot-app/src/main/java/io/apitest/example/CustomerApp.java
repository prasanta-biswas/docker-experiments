package io.apitest.example;

import io.apitest.example.enums.CustomerStatus;
import io.apitest.example.enums.FieldType;
import io.apitest.example.interfaces.FieldService;
import io.apitest.example.interfaces.SubViewService;
import io.apitest.example.interfaces.ViewService;
import io.apitest.example.model.*;
import io.apitest.example.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prasantabiswas on 27/06/18.
 */

@SpringBootApplication
public class CustomerApp {

	@Autowired
	private FieldService fieldService;

	@Autowired
	private ViewService viewService;

	@Autowired
	private SubViewService subViewService;

	private static final Logger logger = LoggerFactory.getLogger(CustomerApp.class);

	public static void main(String[] args) {
		SpringApplication.run(CustomerApp.class, args);
	}

	@Bean
	public CommandLineRunner setupCustomer(CustomerRepository customerRepository) {
		return (args) -> {
			customerRepository.save(new Customer("User 1","Kolkata, West Bengal",false, CustomerStatus.INACTIVE,1,1));
			customerRepository.save(new Customer("User 2","Bangalore, Karnataka",false, CustomerStatus.ACTIVE,2,2));
			customerRepository.save(new Customer("User 3","Mumbai, Maharastra",true, CustomerStatus.BLOCKED,3,3));
			customerRepository.save(new Customer("User 4","Chennai, Tamilnadu",false, CustomerStatus.ACTIVE,4,4));
			logger.info("The sample customer data has been generated");
		};
	}

	@Bean
	public CommandLineRunner setupField(FieldRepository fieldRepository) {
		return (args) -> {
			fieldRepository.save(new Field("Name1",FieldType.STRING));
			fieldRepository.save(new Field("Address1",FieldType.STRING));
			fieldRepository.save(new Field("Is Onboarded1",FieldType.BOOLEAN));
			fieldRepository.save(new Field("Status1",FieldType.INTEGER));
			fieldRepository.save(new Field("Name2",FieldType.STRING));
			fieldRepository.save(new Field("Address2",FieldType.STRING));
			fieldRepository.save(new Field("Is Onboarded2",FieldType.BOOLEAN));
			fieldRepository.save(new Field("Name3",FieldType.STRING));
			fieldRepository.save(new Field("Address3",FieldType.STRING));
			fieldRepository.save(new Field("Status3",FieldType.INTEGER));
			fieldRepository.save(new Field("Name4",FieldType.STRING));
			fieldRepository.save(new Field("Address4",FieldType.STRING));
			fieldRepository.save(new Field("Is Onboarded4",FieldType.STRING));
			fieldRepository.save(new Field("Status14",FieldType.STRING));
			fieldRepository.save(new Field("Name5",FieldType.STRING));
			fieldRepository.save(new Field("Address5",FieldType.STRING));
			fieldRepository.save(new Field("Status5",FieldType.STRING));
			logger.info("The sample field data have been generated");
		};
	}

	@Bean
	public CommandLineRunner setupView(ViewRepository viewRepository) {
		return (args) -> {
			List<Field> fieldSet1 = new ArrayList<>();
			fieldSet1.add(fieldService.getFieldById(1));
			fieldSet1.add(fieldService.getFieldById(2));
			fieldSet1.add(fieldService.getFieldById(3));
			fieldSet1.add(fieldService.getFieldById(4));

			List<Field> fieldSet2 = new ArrayList<>();
			fieldSet2.add(fieldService.getFieldById(5));
			fieldSet2.add(fieldService.getFieldById(6));
			fieldSet2.add(fieldService.getFieldById(7));

			List<Field> fieldSet3 = new ArrayList<>();
			fieldSet3.add(fieldService.getFieldById(8));
			fieldSet3.add(fieldService.getFieldById(9));
			fieldSet3.add(fieldService.getFieldById(10));

			viewRepository.save(new View("Kolkata View","It is used for Kolkata customers",fieldSet1));
			viewRepository.save(new View("Bangalore View","It is used for Bangalore customers",fieldSet2));
			viewRepository.save(new View("Mumbai View","It is used for Mumbai customers",fieldSet1));
			viewRepository.save(new View("Chennai View","It is used for Chennai customers",fieldSet3));
			logger.info("The sample view data have been generated");
		};
	}

	@Bean
	public CommandLineRunner setupSubView(SubViewRepository subViewRepository) {
		return (args) -> {
			List<Field> fieldSet1 = new ArrayList<>();
			fieldSet1.add(fieldService.getFieldById(11));
			fieldSet1.add(fieldService.getFieldById(12));
			fieldSet1.add(fieldService.getFieldById(13));
			fieldSet1.add(fieldService.getFieldById(14));

			List<Field> fieldSet3 = new ArrayList<>();
			fieldSet3.add(fieldService.getFieldById(15));
			fieldSet3.add(fieldService.getFieldById(18));
			fieldSet3.add(fieldService.getFieldById(17));

			List<View> views1 = new ArrayList<>();
			List<View> views2 = new ArrayList<>();
			views1.add(viewService.getViewById(1));
			views2.add(viewService.getViewById(2));

			SubView subView1 = new SubView( "Kolkata SubView", "It is used for Kolkata customers", fieldSet3,views1);
			SubView subView2 = new SubView( "Bangalore SubView", "It is used for Bangalore customers", fieldSet1,views2);

			List<SubView> subViews1 = new ArrayList<>();
			List<SubView> subViews2 = new ArrayList<>();

			subViews1.add(subView1);
			viewService.getViewById(1).setSubViews(subViews1);

			subViews2.add(subView2);
			viewService.getViewById(2).setSubViews(subViews2);

			subViewRepository.save(subView1);
			subViewRepository.save(subView2);
			logger.info("The sample sub view data have been generated");
		};
	}

	@Bean
	public CommandLineRunner setupWorkflow(WorkflowRepository workflowRepository) {
		return (args) -> {
			workflowRepository.save(new Workflow("Kolkata Workflow","It is used for Kolkata customers"));
			workflowRepository.save(new Workflow("Bangalore Workflow","It is used for Bangalore customers"));
			workflowRepository.save(new Workflow("Mumbai Workflow","It is used for Mumbai customers"));
			workflowRepository.save(new Workflow("Chennai Workflow","It is used for Chennai customers"));
			logger.info("The sample workflow data have been generated");
		};
	}
}
