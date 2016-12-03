class MainTest{

	public static void main(String[] args) {
		System.out.println("The test classes main has been run.");
		MainTwo main = new MainTwo();
		String[] sa = {"String"};
		main.main(sa);

		//just want to see what happens if I try to instantiate a class
		//that has a public static void main.
		//Seems to just ignore the main in MainTwo. Good to know.

		String[] sa = {"String"};
		main.main(sa);
		//the second main can be called like a regular method though.
	}

}