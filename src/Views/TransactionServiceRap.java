package Views;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransactionServiceRap {
    private static final String COMMA_DELIMITER = ";";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String ERREUR = "ERREUR";
    private static final String VALIDER = "OK";

    String pathToCsv="C:\\Users\\it.dev\\Documents\\new\\Cotisations.csv";
    String path ="C:\\Users\\it.dev\\Documents\\new\\rapprochement.csv";
    //CSV file header
    private static final String FILE_HEADER = "Numero compte;NomClient;Date ;Vl a applique;Montant cotisé;Montant Views.Transaction ; Ecart ;appreciation";
    public boolean generateCsvFile(String p1 , String p2){

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

       String dateDuJour = now.format(formatter);

        String pathcree ="C:\\Users\\it.dev\\Documents\\new\\new-rapprochement-"+dateDuJour +".csv";
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(pathcree);

            //Write the CSV file header
            fileWriter.append(FILE_HEADER);

            //Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);
            List<Transaction> cotisations = processInputFileCsvcoti(p1);
            List<Transaction> raprochements = processInputFileCsvrap(p2);


            for (Transaction coti : cotisations) {

                for(Transaction rap : raprochements )
                {
                    System.out.println(rap.getNumCompte() +" "+coti.getNumCompte());
                    if( rap.getNumCompte() != null && coti.getNumCompte() != null && rap.getNumCompte() != "" && coti.getNumCompte() != "")
                    {

                        if(   Integer.parseInt(rap.getNumCompte()) == Integer.parseInt(coti.getNumCompte()) )
                        {


                            fileWriter.append(String.valueOf(coti.getNumCompte()));
                            fileWriter.append(COMMA_DELIMITER);

                            fileWriter.append(String.valueOf(coti.getNomClient()));
                            fileWriter.append(COMMA_DELIMITER);

                            fileWriter.append(String.valueOf(rap.getDateTransaction()));
                            fileWriter.append(COMMA_DELIMITER);

                            fileWriter.append(String.valueOf(rap.getValUnitaire()));
                            fileWriter.append(COMMA_DELIMITER);

                            fileWriter.append(String.valueOf(coti.getMontant()));
                            fileWriter.append(COMMA_DELIMITER);

                            fileWriter.append(String.valueOf(rap.getMontant()));
                            fileWriter.append(COMMA_DELIMITER);

                            fileWriter.append(String.valueOf(coti.getMontant() - (rap.getMontant() + 500 )));
                            fileWriter.append(COMMA_DELIMITER);

                            if ((rap.getMontant()+500) == coti.getMontant() || coti.getMontant() - (rap.getMontant()+500) <= 5 ){
                                fileWriter.append(VALIDER);

                            }
                            else {
                                fileWriter.append(ERREUR);
                            }

                            fileWriter.append(COMMA_DELIMITER);
                        }
                    }



                }

                fileWriter.append(NEW_LINE_SEPARATOR);

            }


        }
        catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        }
        finally {

            try {

                fileWriter.flush();
                fileWriter.close();

            }
            catch (IOException e) {

                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();

            }

        }

        return true;
    }

    private Function<String, Transaction> mapToItemcoti = (line)->{
        String[] p = line.split(";");// a CSV has comma separated lines
        Transaction item = new Transaction();
         if(p.length > 6 ) {
             item.setNomClient(p[1]+" "+p[0]);
             item.setNumCompte(p[3].trim());
             //item.setValUnitaire(Double.parseDouble(p[5].trim()));
             item.setMontant(Double.parseDouble(p[6].replace(",",".").trim()));
         }



        return item;
    };

    public List<Transaction> processInputFileCsvcoti(String inputFilePath) {
        List<Transaction> inputList = new ArrayList<>();
        try
        {

            File inputF = new File(inputFilePath);
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

            inputList = br.lines().skip(1).map(mapToItemcoti).collect(Collectors.toList());
            br.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("file not found");
        }
        catch(IOException e){

        }
        return inputList ;
    }

    private Function<String, Transaction> mapToItemrap = (line)-> {
        String[] p = line.split(";");
        // a CSV has comma separated lines
        Transaction item = new Transaction();
         if(p.length > 6){
             item.setDateTransaction(p[1]);
             item.setNumCompte(p[2].trim());
             item.setValUnitaire(Double.parseDouble(p[4].replace(",",".")));
             item.setMontant(Double.parseDouble(p[5].split(",")[0].trim()));
         }

        return  item;
    };

    public List<Transaction> processInputFileCsvrap(String inputFilePath) {
        List<Transaction> inputList = new ArrayList<>();
        try{
            File inputF = new File(inputFilePath);
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
            // skip the header of the csv
            inputList = br.lines().skip(1).map(mapToItemrap).collect(Collectors.toList());
            br.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("file not found");
        }
        catch(IOException e){

        }
        return inputList ;
    }
}
