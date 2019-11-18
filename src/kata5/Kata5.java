package kata5;

public class Kata5 {

    public static void main(String[] args) {
        String URL = new String("jdbc:sqlite:/Users/Ariadna/NetBeansProjects/DB_SQLite/miErcoles.db");
        DataBase db = new DataBase(URL);
        db.open();
        
        // Lo unico que hace es solo sacarlo por pantalla los datos que tiene
        db.selectPeople();
        // Insertar datos a la tabla
        People people = new People("Fefo","Martin","Taller");
        db.insertPeople(people);
        
        System.out.println("* * * * *");
        
        db.selectPeople();
        
        
        db.close();
        
    }
    
}
