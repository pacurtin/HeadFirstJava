class Dog{

	String name;
	
	void bark() {
		System.out.println("Bark! My name is "+name);
	}
	
	//how can I create 2 Dog objects inside the main of the Dog class?
	public static void main(String[] args) {
		Dog rex = new Dog();
		Dog dan = new Dog();
		rex.name = "rex";
		dan.name = "dan";	
		rex.bark();
		dan.bark();
	}

}