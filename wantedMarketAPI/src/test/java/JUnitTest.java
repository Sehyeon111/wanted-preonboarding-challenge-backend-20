import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JUnitTest  {
	@DisplayName("1 + 3는 4이다")
	@Test
	public void juniTest() {
		int a = 1;
		int b = 3;
		int sum = 3;
		
		Assertions.assertEquals(sum, a+b);;
	}
}