import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

//Time logging class
public class TikTok {
	
	//NOTE: Changed .tictoc to tictoc
	//Setting the save files
	private static String workingfile = System.getenv("HOME") + File.separator + 
			".tictoc" + File.separator + ".working";
	private static String keylistfile = System.getenv("HOME") + File.separator + 
			".tictoc" + File.separator + "keylist";
	
	//Executes the time keeper function
	public static void main(String[] args) throws IOException {
				
		//Getting the inputs from the command line
		if(args.length != 1 && args.length != 2) {
			System.out.println("Invalid number of arguments."); return;}
		String operation = args[0]; 
		String inputs = null;
		if(args.length == 2) {
			 inputs = args[1].toLowerCase();}
		
		//Choosing and executing the correct operation
		if(operation.equals("0")) {tik(inputs);}
		else if(operation.equals("1")) {tok(inputs);}
		else if(operation.equals("2")) {tak(inputs);}
		else {System.out.println("Argument not found.");}
	}
	
	//Clocks the user in
	public static void tik(String userkey) throws IOException {
		
		//Checks to see if the user is currently clocked in somewhere else
		BufferedReader tikcheck = new BufferedReader(new FileReader(new File(workingfile)));
		String linecheck;
		if((linecheck=tikcheck.readLine())!=null) {
			System.out.println("Cannot run operation. Currently working on " + linecheck.split(",")[0] + ".");
			tikcheck.close();
			return;
		}
		tikcheck.close();
		
		//Loading file and creating data structure
		File keylist = new File(keylistfile);
		BufferedReader reader = new BufferedReader(new FileReader(keylist));
		ArrayList<ArrayList<String>> keys = new ArrayList<ArrayList<String>>();
		
		//Loading in data to data structure
		String line;
		while((line=reader.readLine()) != null) {
			ArrayList<String> linekeys = new ArrayList<String>();
			String[] linesplit = line.split(",");
			for(int i=0; i<linesplit.length; i++) linekeys.add(linesplit[i].trim());
			keys.add(linekeys);
		}
		
		//If the user didn't enter a key, gets key from user
		String key = null;
		while(key == null) {
			if(userkey == null) {
				System.out.print("Enter key: ");
				userkey = User.getString().toLowerCase();}
			for(ArrayList<String> linekeys: keys)
				if(linekeys.contains(userkey))
					key = linekeys.get(0);
			userkey = null;
		}
		
		//Writing tik info to file
		System.out.println("Writing to " + key);
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(workingfile)));
		Date tik = new Date();
		writer.write(key + "," + tik.getTime());
		
		reader.close();
		writer.close();
	}
	
	//Clocks the user out
	public static void tok(String userinput) throws IOException {
		
		//Getting info from working file
		BufferedReader tokread = new BufferedReader(new FileReader(new File(workingfile)));
		String working = tokread.readLine();
		tokread.close();
		if(working == null) {System.out.println("No operation in progress."); return;}
		
		//Getting the notes from the user
		if(userinput == null) {
			System.out.print("Comments: ");
			userinput = User.getString();}
		
		//Creating the String to append
		String string = "";
		Date tok = new Date();
		String[] workingsplit = working.split(",");
		Date tik = new Date(Long.parseLong(workingsplit[1]));
		string += tik + " - " + tok + "\n";
		Long tiktok = tok.getTime() - tik.getTime();
		tiktok = tiktok/360000;
		string += String.valueOf(((double)tiktok)/10) + " hours: ";
		string+= userinput + "\n\n";
		
		//Appending string to file
		File file = new File(System.getenv("HOME") + File.separator + workingsplit[0]);
		FileWriter writer = new FileWriter(file, true);
		writer.write(string);
		writer.close();
		
		//Deleting the text in working.txt
		File wfile = new File(workingfile);
		FileWriter workingwriter = new FileWriter(wfile);
		workingwriter.write("");
		workingwriter.close();
	}
	
	//Allows the user to add comments to the file without clocking them out
	public static void tak(String userinput) throws IOException {
		
		//Getting info from working file
		BufferedReader takread = new BufferedReader(new FileReader(new File(workingfile)));
		String working = takread.readLine(); takread.close();
		if(working == null) {System.out.println("No operation in progress."); return;}
		String directory = working.split(",")[0];
		
		//Getting the comments for the current file
		tok(userinput);
		
		//Saving the current time to a file
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(workingfile)));
		Date tak = new Date();
		writer.write(directory + "," + tak.getTime());
		writer.close();
	}

}
