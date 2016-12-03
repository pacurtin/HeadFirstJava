class StaticLocal{

	public void doMethod(){
		static String var = "possible";	//doesnt compile
		System.out.println("A local static variable is " + var);
	}
}