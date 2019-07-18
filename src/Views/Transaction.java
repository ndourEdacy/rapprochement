package Views;


public class Transaction {

    private int numeroTransaction;
    private String numCompte;
    private String nomClient;
    private String dateTransaction;
    private double valUnitaire;
    private double montant;


    public Transaction() {

    }
public static  void  main(String[] args){
    TransactionServiceRap transactionServiceRap = new TransactionServiceRap();
    transactionServiceRap.generateCsvFile("","");
}

    public int getNumeroTransaction() {
        return numeroTransaction;
    }

    public void setNumeroTransaction(int numeroTransaction) {
        this.numeroTransaction = numeroTransaction;
    }

    public String getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(String numCompte) {
        this.numCompte = numCompte;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(String dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public double getValUnitaire() {
        return valUnitaire;
    }

    public void setValUnitaire(double valUnitaire) {
        this.valUnitaire = valUnitaire;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}
