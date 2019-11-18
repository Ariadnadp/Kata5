package kata5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    private String URL;
    // Objeto conexion
    private Connection connection =null;
    
    public DataBase(String URL) {
        this.URL=URL;
    }

    public void open() {
        try {
            // Clase especifica del gestor de Driver, me da una conexion para la url especifica que le paso
            // Me sale error aqui pq hay q hacerlo en su try y catch correspondiente(this.URL)
            this.connection=DriverManager.getConnection(this.URL); 
            System.out.println("Base de datos abierta...");
        } 
        catch (SQLException exception) {
            System.out.println("Error Database:: open (SQLexception)" + exception.getMessage());
        }
        
        
    }

    public void close() {
        try {
            if(this.connection != null)
                this.connection.close();
            System.out.println("Base de datos cerrada.");
        }
        catch (SQLException exception){
            System.out.println("Error Database:: open (SQLexception)" + exception.getMessage());
        }
    }

    public void selectPeople() {
        String SQL = "SELECT * FROM PEOPLE";
        // EL driver nos ofrece una clase específica que nos permite acceder a los datos de una tabla
        // Me devuelve un RS y me devuelve todos los datos que tengo en la tabla 
        //y me posiciona en la primera posicion de la tabla
        // Me sale error entre los parentesis y entonces lo meto en un trycatch
        try{
            Statement statement = this.connection.createStatement();
            ResultSet resultset = statement.executeQuery(SQL);
            
            System.out.println("ID \t NAME \t APELLIDOS \t DEPARTAMENTO");
            while(resultset.next()){
                System.out.println(resultset.getInt("ID") + " \t " +
                                   resultset.getString("NAME") + " \t " +
                                   resultset.getString("APELLIDOS") + " \t " + 
                                   resultset.getString("DEPARTAMENTO"));
            }
        }
        catch (SQLException exception){
             System.out.println("Error Database:: open (SQLexception)" + exception.getMessage());
        }
        
    }

    public void insertPeople(People people) {
        String SQL = "INSERT INTO PEOPLE(ID,NAME,APELLIDOS,DEPARTAMENTO)VALUES(?,?,?)";
        
        try{
            PreparedStatement preparedstatement = this.connection.prepareStatement(SQL);
            preparedstatement.setString(2,people.getName());
            preparedstatement.setString(3,people.getApellidos());
            preparedstatement.setString(4,people.getDepartamento());
            preparedstatement.executeUpdate();
        }
        catch(SQLException exception){
            System.out.println("Error Database:: open (SQLexception)" + exception.getMessage());
        }
        
    }
    
}
