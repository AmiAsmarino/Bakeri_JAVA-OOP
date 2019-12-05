

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JunitTest {

	@Test
	void test() {
		Animal nameTest = new Animal();
		String sn = "Snoop";
		assertEquals(sn,nameTest.dogname());
		assertFalse(nameTest.tORF());
		
		LearnJava SqrTest = new LearnJava();
		int res = 36;
		assertEquals(res,SqrTest.square(6));
		assertEquals("Good!",SqrTest.addExcl("Good"));
		
	}

}
