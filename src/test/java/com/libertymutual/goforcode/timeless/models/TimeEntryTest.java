package com.libertymutual.goforcode.timeless.models;

	import static org.assertj.core.api.Assertions.*;
	import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
	import org.junit.Test;
	
public class TimeEntryTest {
	
		private TimeEntryItem method;
		private ArrayList<Double> testTimeEntry;
		private String date;
	    private double monday;
	    private double tuesday;
	    private double wednesday;
	    private double thursday;
	    private double friday;
	    private double total;
//	   TimeEntryItem item = ("yyyy-mm-dd","5.0","5.0","5.0","5.0","5.0","25.0","U"};
	    List<TimeEntryItem> timeEntryItems;
	    
		
	    @Before
		public void setUp() {
			//Create TimrEntry and set date and hours.
			method = new TimeEntryItem();
			method.setDate("12/31/2017");
			method.setMonday(1.0);
			method.setTuesday(2.0);
			method.setWednesday(3.0);
			method.setThursday(4.0);
			method.setFriday(5.0);
			method.setTotal(15.0);
		}
	    
	    @Test
		public void test_getMonday() {
	    //Arrange
	    	//done in @Before
	    //ACT
			monday = method.getMonday();
		//ASSERT
			assertThat(monday).isEqualTo(1.0);		
		}
	    
		public void test_getTueday() {
		    //Arrange
		    	//done in @Before
		    //ACT
				tuesday = method.getMonday();
			//ASSERT
				assertThat(tuesday).isEqualTo(2.0);		
			}
		
		public void test_getWednesday() {
		    //Arrange
		    	//done in @Before
		    //ACT
				monday = method.getWednesday();
			//ASSERT
				assertThat(wednesday).isEqualTo(3.0);		
			}
		
		public void test_getThursday() {
		    //Arrange
		    	//done in @Before
		    //ACT
				thursday = method.getMonday();
			//ASSERT
				assertThat(thursday).isEqualTo(4.0);		
			}
		public void test_getFriday() {
		    //Arrange
		    	//done in @Before
		    //ACT
				friday = method.getFriday();
			//ASSERT
				assertThat(friday).isEqualTo(5.0);		
			}

	    @Test
	    public void test_getDate() {
	    	date = method.getDate();
	    	assertThat(date).isEqualTo("31/12/2017");
	    }
	    
	    @Test
	    public void test_getTotal() {
	    	assertThat(total).isEqualTo(15);
	    }
	    

	}
