package input.password;

import java.io.Console;

public class PasswordSimple {
	public static void main(String[] args) throws Exception {
		Console console = System.console();
	    String username = console.readLine("Username: ");
	    String password = new String(console.readPassword("Password: "));
	    System.out.println(username+"/"+password);
       
    }
}