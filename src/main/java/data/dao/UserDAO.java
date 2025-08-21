package data.dao;

import data.entity.User;
import data.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public void createUser ( User user ){

        String sql = "INSERT INTO users ( name , email )  VALUES  (? , ? )  ";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement( sql ) ){

            stmt.setString(1 , user.getName() );
            stmt.setString( 2 , user.getEmail()   );
            int rowInserted = stmt.executeUpdate();

            if ( rowInserted > 0 ){
                System.out.println(" user inserted successfully ");
            }

        }catch ( SQLException e ){
            System.out.println(  "error inserting user:   " + e.getMessage() );
            e.printStackTrace();
        }
    }



    public User getUser (int id   ){

        String sql = " SELECT * FROM users WHERE id = ? " ;
        User user = null ;

        // establish a connection
        try ( Connection conn = DBConnection.getConnection();
              PreparedStatement stmt = conn.prepareStatement(sql);      ){

            stmt.setInt(1 , id );

            try ( ResultSet rs = stmt.executeQuery()   ){
                if ( rs.next()  ) {

                    String name = rs.getString("name");
                    String email = rs.getString( "email"  );

                    user = new User();
                    user.setName(name );
                    user.setEmail( email);

                }
            }
        }catch  ( SQLException e) {

            System.out.println( "error retrieving user : " + e.getMessage()   );
            e.printStackTrace();

        }

        return user ;

    }

    public List<User> getAllUsers ( ){

        List<User> users = new ArrayList<>();
        String sql = "select * from  users";
        //  String sql = "SELECT * FROM users";

        try ( Connection conn = DBConnection.getConnection() ;
              PreparedStatement stmt = conn.prepareStatement( sql );
              ResultSet rs = stmt.executeQuery();


        ){
            while ( rs.next() ){

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");

                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setEmail(email);


                users.add(user);

            }

        } catch ( SQLException e  ){
            System.out.println(" error fetching users : " + e.getMessage()  );
            e.printStackTrace();

        }

        return users;
    }


    public boolean updateUser (User user   ){

        String sql = "UPDATE users  SET name = ? , email = ? where id = ? " ;
        boolean updated = false ;

        try ( Connection conn = DBConnection.getConnection() ;
              PreparedStatement stmt = conn.prepareStatement(sql)    ) {

            stmt.setString(1 , user.getName()  );
            stmt.setString(2 , user.getEmail());
            stmt.setInt( 3 , user.getId() );

            int rowAffected = stmt.executeUpdate();
            updated = rowAffected > 0 ;

        } catch (  SQLException e ){

            System.out.println( "error updating user : " + e.getMessage() );
            e.printStackTrace();

        }
        return updated;
    }


    public boolean deleteUser (int id ){
        // sql

        String sql = "DELETE FROM users WHERE id = ? ";
        boolean deleted = false ;

        try ( Connection conn = DBConnection.getConnection();
              PreparedStatement stmt   =  conn.prepareStatement(sql)     )   {

            stmt.setInt(1 , id );
            int rowAffected = stmt.executeUpdate();
            deleted = rowAffected >0 ;

        }  catch ( SQLException e   ){
            System.out.println(" error deleting the user :  " + e.getMessage()  );
            e.printStackTrace();
        }

        return deleted;

    }



}
