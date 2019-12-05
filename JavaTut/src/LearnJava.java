
public class LearnJava {

	public static void main(String[] args) {
		
		 int x= 3, y=4,z=9;
		 System.out.println("When TRUE return the first value: " +( (x < y) ? z : y));
		 System.out.println("When FALSE return the second value: " +( (x > y) ? z : y));
		 
		
		String name= "Susan";
		System.out.println(name.toLowerCase());
		addExcl("Add exclamation in the end");
		addExcl("");
		
		int result = square(7);
		System.out.println(result);
		System.out.println(square(12));
		
		// create an animal object
		Animal c = new Animal();
		System.out.format("\nCurrent Date time: %tc%n\n", c.CurrentTime());
		// call method and return animal object
		Animal B = animalObject();
		B.methodFromLearnJava();
		System.out.println(B.dogname());
		
		System.out.println(Animal.iAmaDog());
		
		
	}
	public static String addExcl(String s) {
		System.out.println(s + "!");
		return s + "!";
	}
	 
	public static int square(int x) {
		return x*x;
	}
	
	public static Animal animalObject() {
		Animal a = new Animal();
		return a;
	}
	

}
