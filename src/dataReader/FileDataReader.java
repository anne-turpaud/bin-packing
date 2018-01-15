package dataReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Objet récupérant les données depuis un fichier
 */
public class FileDataReader implements IDataReader {

	public String fileName;

	public FileDataReader(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public List<Integer> readAll() {
		try {
			String content = new String(Files.readAllBytes(Paths.get(this.fileName)));
			List<Integer> numbers = new ArrayList<>();
			content = content.trim();

			for(int i = 0; i < content.length(); i++) {
				char c = content.charAt(i);
				int number = Integer.parseInt(String.valueOf(c));
				numbers.add(number);
			}
			return numbers;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
