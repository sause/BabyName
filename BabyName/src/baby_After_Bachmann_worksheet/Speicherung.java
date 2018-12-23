package baby_After_Bachmann_worksheet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * IOStrategy to load and save decision to a file using strings
 */
// public class Speicherung implements IOStrategy {

	public class Speicherung {
	
	public List<String> load(String fileName) throws IOException,
	ClassNotFoundException {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			List<String> decisions = new ArrayList<>();

			String decision = reader.readLine();
			while (decision != null) {
				decisions.add(decision);
				decision = reader.readLine();
			}
			return decisions;
		}
	}

	
	public void save(String fileName, List<String> decisions) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

			for (String string : decisions) {
				writer.write(string);
				writer.newLine();
			}
		}
	}

}