package com.mockito.essentials;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class BasicReturnValuesTest {
	
	@Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Captor
    private ArgumentCaptor<List<String>> captor;

	@Test
	public void testReturnValue() {
		BasicReturnValues basicReturnValues = mock(BasicReturnValues.class);

		when(basicReturnValues.getValueIfFalse(false)).thenReturn("FALSE");
		assertTrue("FALSE".equals(basicReturnValues.getValueIfFalse(false)));
	}

	@Test
	public void testMoreThanOneReturnValue() {
		Iterator<String> i = mock(Iterator.class);

		when(i.next()).thenReturn("Mockito").thenReturn("rocks");
		String result = i.next() + " " + i.next();

		assertEquals("Mockito rocks", result);
	}

	@Test
	public void testReturnValueInDependentOnMethodParameter() {
		Comparable<Integer> c = mock(Comparable.class);
		when(c.compareTo(anyInt())).thenReturn(-1);
		// assert
		assertEquals(-1, c.compareTo(5));
	}

	@Test
	public void testReturnValueInDependentOnMethodParameter2() {
		Comparable<BasicReturnValues> c = mock(BasicReturnValues.class);
		when(c.compareTo(isA(BasicReturnValues.class))).thenReturn(0);
		// assert
		assertEquals(0, c.compareTo(new BasicReturnValues()));
	}

	@Test
	public void testThrowException() throws Exception {
		BasicReturnValues basicReturnValues = mock(BasicReturnValues.class);
		when(basicReturnValues.throwException(true)).thenThrow(new IllegalStateException());
	}

	@Test
	public void testLinkedListSpyWrong() {
		// Lets mock a LinkedList
		List<String> list = new LinkedList<>();
		List<String> spy = spy(list);

		// this does not work
		// real method is called so spy.get(0)
		// throws IndexOutOfBoundsException (list is still empty)
		when(spy.get(0)).thenReturn("foo");

		assertEquals("foo", spy.get(0));
	}

	@Test
	public void testLinkedListSpyCorrect() {
		// Lets mock a LinkedList
		List<String> list = new LinkedList<>();
		List<String> spy = spy(list);

		// You have to use doReturn() for stubbing
		doReturn("foo").when(spy).get(0);

		assertEquals("foo", spy.get(0));
	}
	
	@Test
    public final void shouldContainCertainListItem() {
        List<String> asList = Arrays.asList("someElement_test", "someElement");
        final List<String> mockedList = mock(List.class);
        mockedList.addAll(asList);

        verify(mockedList).addAll(captor.capture());
        final List<String> capturedArgument = captor.getValue();
        assertThat(capturedArgument, hasItem("someElement"));
    }

}
