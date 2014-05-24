package org.tests.utils;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;


public class UtilsTest{


	@Before
	public void init(){

	}
	@Test
	public void testCompareValues(){
		ArrayList<String> sizesList = new ArrayList<String>(Arrays.asList("tiny", "little", "small", "medium", "big","huge"));

		assert Utils.compareValues("medium", "small",  sizesList) == 1;
		assert Utils.compareValues("medium", "little",  sizesList) == 1;
	}
}