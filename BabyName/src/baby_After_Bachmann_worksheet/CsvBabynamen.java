package baby_After_Bachmann_worksheet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CsvBabynamen {
    
    private List<Babynamen> names = new ArrayList<>();
    
    public List<Babynamen> getNames() {
        return names;
    }
    

    
    
    //Constructor: File wird direkt geladen und ArrayList mit allen Namen erstellt
    public CsvBabynamen() {
    	try {
    		names = this.readPersonFile("src/babynamen_bereinigt.csv", ";");
    	}
    	catch(Exception ex) {
    		
    	}
    }
    
    public List<Babynamen> add(String name, String geschlecht, String ranglisteSchweiz, String ranglisteAllerNamen, String ranglisteWelt, String biblisch ) {
    	Babynamen bn = new Babynamen(name, geschlecht, ranglisteSchweiz, ranglisteAllerNamen, ranglisteWelt, biblisch);
    	names.add(bn);
    	return names;
    	
    }
       
    public String csvBabynamen(Babynamen name, String splitter) {

            StringBuilder buffer = new StringBuilder();
            buffer.append(name.getName()).append(splitter);
            buffer.append(name.getGeschlecht()).append(splitter);
            buffer.append(name.getRanglisteSchweiz()).append(splitter);
            buffer.append(name.getRanglisteAllerNamen()).append(splitter);
            buffer.append(name.getRanglisteWelt()).append(splitter);
            buffer.append(name.getBiblisch()).append(splitter);

            return buffer.toString();
    }
    public Babynamen csvBabynamen(String line, String splitter) {
            String [] result = line.split(";|\r|\\.");

            return new Babynamen(result[0], // Name
                            result[1], // Geschlecht
                            result[2],  // RanglisteSchweiz
                            result[3],  // RanglisteAllerNamen
                            result[4],  // RanglisteWelt
                            result[5]); // Biblisch

    }

    public List<Babynamen> readPersonFile (String fileName, String splitter) throws IOException {

            String line;
            names = new ArrayList<>();

            try  
            {
            	BufferedReader reader = new BufferedReader(new InputStreamReader(
                        new FileInputStream(new File(fileName)), 
                        StandardCharsets.UTF_8));
                

                    while ((line = reader.readLine()) != null)  {
                            names.add(csvBabynamen(line, splitter));
                    }
                    
                 reader.close();
            }catch(Exception ex) {
            	System.out.println(ex.getMessage());
            }
            return names;
            
    }
    

    /* //Datein speicher in neuem csv file
    public void writeNameFile (Collection<Babynamen> names, String fileName, String splitter) 
                    throws IOException {

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))){
            for  (Babynamen name : names) {
                String line = babynamesToCsv(name, splitter);
                writer.println(line);
            }

        }
    }
    
    private String babynamesToCsv(Babynamen name, String splitter) {
            String nameLine = name.getName() + ";" + name.getGeschlecht() + ";name.getRangliste() + ";name.getBiblisch() + ";
            return nameLine;
    }
     */
    public List<Babynamen> search(Suchparameter params) {

        return names
            .stream()
            .filter(p-> p.getName().startsWith(params.getBeginntMit()) || params.getBeginntMit().equals(""))
            .filter(p-> p.getGeschlecht().equals(params.getGeschlecht()) || params.getGeschlecht().equals(""))
//
//            .filter(p-> p.getRanglisteSchweiz().equals(params.getRanglisteSchweiz()) || params.getRanglisteSchweiz().equals(""))
//            .filter(p-> p.getRanglisteAllerNamen().equals(params.getRanglisteAllerNamen()) || params.getRanglisteAllerNamen().equals(""))
//            .filter(p-> p.getRanglisteWelt().equals(params.getRanglisteWelt()) || params.getRanglisteWelt().equals(""))
//            .filter(p-> p.getBiblisch().equals(params.getBiblisch()) || params.getBiblisch().equals(""))

            
            
            
            //  .filter(p-> (p.getRanglisteSchweiz() > 0 && p.getRanglisteSchweiz() <= 200) || !params.getRanglisteSchweiz())
          //  .filter(p-> (p.getRanglisteAllerNamen() > 0 && p.getRanglisteAllerNamen() <= 100) || !params.getRanglisteAllerNamen())
         //   .filter(p-> (p.getRanglisteWelt() > 0 && p.getRanglisteWelt() <= 100) || !params.getRanglisteWelt())
         //   .filter(p-> p.getBiblisch().length() > 5 || !params.getBiblisch())
        //    .filter(p-> !p.getBiblisch().equals("Keine") || !params.getBiblisch())
            .collect(Collectors.toList());
        
        //return filteredNames;
    }
	
}
