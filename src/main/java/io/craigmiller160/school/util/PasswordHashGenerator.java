package io.craigmiller160.school.util;

import java.util.Scanner;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashGenerator {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		boolean run = false;
		
		do{
			System.out.println("Enter password to encrypt");
			
			System.out.print("Password: ");
			String rawPassword = sc.nextLine();
			
			String hashPassword = encoder.encode(rawPassword);
			System.out.println("Hash: " + hashPassword + "\n");
			
			boolean runAnother = true;
			
			do{
				System.out.println("Another?");
				System.out.print("Y/N: ");
				String again = sc.nextLine();
				
				if(again.equalsIgnoreCase("Y")){
					System.out.println("\n");
					run = true;
					runAnother = false;
				}
				else if(again.equalsIgnoreCase("N")){
					System.out.println("Goodbye");
					run = false;
					runAnother = false;
				}
				else{
					System.err.println("\nInvalid input!\n");
				}
			}while(runAnother);
			
		}while(run);
		
		sc.close();
	}

}
