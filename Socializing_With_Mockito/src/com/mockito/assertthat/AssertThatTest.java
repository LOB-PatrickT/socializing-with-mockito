package com.mockito.assertthat;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AssertThatTest {
	
	@Test
	public void verify_correct_matcher() {
		int age = 30;
		assertThat(age, equalTo(30));
		assertThat(age, is(30));
	}
	
	@Test
	public void verify_incorrect_matcher() {
		int age = 30;
		assertThat(age, not(equalTo(33)));
		assertThat(age, is(not(33)));
	}
	
	@Test
	public void verify_multiple_values() throws Exception {
		
		double marks = 100.00;
		assertThat(marks, either(is(100.00)).or(is(90.0)));
		
		assertThat(marks, both(not(99.99)).and(not(60.00)));
		
		assertThat(marks, not(anyOf(is(0.00), is(200.00))));
		
		assertThat(marks, not(allOf(is(100.00), is(90.00), is(30.00))));
	}
}
