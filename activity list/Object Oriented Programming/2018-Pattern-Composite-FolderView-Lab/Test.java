
public class Test {
	public static boolean filter(String name){
		String[] splittedNames = name.split("\\.");
		if(splittedNames.length<=1) return false; 
		String extension = splittedNames[splittedNames.length-1];
		return extension.equals("html");
	}
	public static void main(String[] args) {
		String name = "help.ps";
		System.out.println(filter(name));
	}
}
