package pkg3enraya;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * Clase encargada de la gestión de la base de datos MySQL.
 * Se ocupa de establecer la conexión con el servidor local, ejecutar
 * consultas SQL y recuperar los datos del ranking.
 * * @author Alicia
 * @version 1.0 (Sprint 1)
 */
public class DB_Manager {

    /**
     * Constructor de la clase DB_Manager.
     * Inicializa la conexión con la base de datos 'ranking', ejecuta una 
     * consulta SELECT sobre la tabla 'sesion' y muestra los resultados por consola.
     */
    public DB_Manager() {
        /** Objeto para gestionar la conexión física con la BD */
        Connection conn;
        /** Objeto para enviar sentencias SQL a la base de datos */
        Statement stmt;
        /** Objeto que almacena los datos devueltos por una consulta SELECT */
        ResultSet rs;
        
        // Carga del Driver JDBC para MySQL.
        try {
            System.out.println("Conectando...");
            // Uso de newInstance() para compatibilidad con implementaciones de Java antiguas
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            System.out.println("Error al cargar el Driver: " + ex.getMessage());
        }

        try {
            // Establecimiento de la conexión con parámetros de seguridad desactivados para desarrollo local
            conn = DriverManager.getConnection("jdbc:mysql://localhost/ranking?autoReconnect=true&useSSL=false&user=root&password=123456");

            // Crear el objeto Statement para ejecutar consultas.
            stmt = conn.createStatement();  

            // Ejecución de la consulta para obtener el historial.
            rs = stmt.executeQuery("SELECT * FROM sesion;");
            
            // Obtención de metadatos para conocer la estructura de las columnas (nombres y conteo)
            ResultSetMetaData rsmd = rs.getMetaData();
            
            System.out.println("SELECT * FROM sesion;");
            System.out.println("");
            
            int columnsNumber = rsmd.getColumnCount();
            
            // Recorrido del ResultSet fila por fila
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) {
                        System.out.print(",  ");
                    }
                    // Extracción dinámica del valor de cada columna por su índice
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }

            System.out.println(rs);
            
        } catch (SQLException ex) {
            // Gestión detallada de errores SQL
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    } 
} 
