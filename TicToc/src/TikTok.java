import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class TikTok {
	
	private static String workingfile = "/Users/william/Commands/.working.txt";
	private static String keylistfile = "/Users/william/Commands/keylist.txt";
	
	public static void main(String[] args) throws IOException {
		
		if(args.length != 1) {System.out.println("Invalid number of arguments."); return;}
		String input = args[0]; 
		
		if(input.equals("0")) {tik();}
		else if(input.equals("1")) {tok();}
		else if(input.equals("2")) {tak();}
		else {System.out.println("Argument not found.");}
	}
	
	public static void tik() throws IOException {
		
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
		
		//Getting key from user
		String key = "";
		while(key.equals("")) {
			System.out.print("Enter key: ");
			String userkey = User.getString().toLowerCase();
			for(ArrayList<String> linekeys: keys)
				if(linekeys.contains(userkey))
					key = linekeys.get(0);
		}
		
		//Writing tik info to file
		System.out.println("Writing to " + key);
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(workingfile)));
		Date tik = new Date();
		writer.write(key + "," + tik.getTime());
		
		reader.close();
		writer.close();
	}
	
	public static void tok() throws IOException {
		
		//Getting info from working file
		BufferedReader tokread = new BufferedReader(new FileReader(new File(workingfile)));
		String working = tokread.readLine();
		tokread.close();
		if(working == null) {System.out.println("No operation in progress."); return;}
		
		//Getting the notes from the user
		System.out.print("Comments: ");
		String userinput = User.getString();
		
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
		File file = new File(workingsplit[0]);
		FileWriter writer = new FileWriter(file, true);
		writer.write(string);
		writer.close();
		
		//Deleting the text in working.txt
		File wfile = new File(workingfile);
		FileWriter workingwriter = new FileWriter(wfile);
		workingwriter.write("");
		workingwriter.close();
	}
	
	public static void tak() throws IOException {
		
		//Getting info from working file
		BufferedReader takread = new BufferedReader(new FileReader(new File(workingfile)));
		String working = takread.readLine(); takread.close();
		if(working == null) {System.out.println("No operation in progress."); return;}
		String directory = working.split(",")[0];
		
		//Getting the comments for the current file
		tok();
		
		//Saving the current time to a file
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(workingfile)));
		Date tak = new Date();
		writer.write(directory + "," + tak.getTime());
		writer.close();
	}

}
