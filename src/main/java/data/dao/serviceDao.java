package data.dao;

import data.entity.Service;
import data.entity.User;
import data.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class serviceDao implements Dao<Service ,  Integer > {


    // read all the data   from data base

    private static final String getAll  =  "select service_id , name , price  from services" ;
    private static final String getById =  "select service_id , name , price from services where service_id = ? ";
    private static final String create  =  "insert into services ( name , price , service_id ) values   (? ,? , ? )  ";

    private static final String delete =   "delete from  services  where  service_id = ? ";
    private static final String update =   "update services set name = ? ,  price = ?  where service_id = ? ";

    @Override
    public List<Service> getAll() {
        // driver , connection  , prepared statement   ,  execute query   ,  result set


        List<Service> services  = new ArrayList<>();


        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(getAll );
                 ResultSet rs = stmt.executeQuery() )
             {

                 services  = this.processResultSet( rs ) ;

             }catch ( SQLException e ){

            System.out.println(" error fetching users : " + e.getMessage()  );
            e.printStackTrace();

            }

        return services;

    }




    @Override
    public Service  create(Service entity) {

        // add service to db

        // sql  to create

        Service service = null ;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(create)

        ) {
            stmt.setString(1, entity.getName() ); //"planning");
            stmt.setBigDecimal(2, entity.getPrice() );  // 100.5);
            stmt.setInt(3 , entity.getId() );

            stmt.executeQuery();


        } catch (SQLException e) {

            System.out.println(" error creating  service : " + e.getMessage());
            e.printStackTrace();


        }

        Optional<Service> service1 = this.GetOne(entity.getId());
        if ( !service1.isPresent() ){
            System.out.println(" service is null ");

            return null;
        } else {

            return service1.get();
            //System.out.println(" service is not  null ");

        }

    }

    @Override
    public Optional<Service> GetOne(Integer id) {

        User user = null ;

        Service service = null ;

        try( Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(getById  ) ) {

            // set the value of the bind variables
            stmt.setInt(1 , id );

            // try get  one service
            try ( ResultSet rs =   stmt.executeQuery() ) {

                if ( rs.next() ){

                    service = new Service();
                    service.setId( rs.getInt( "service_id"));
                    service.setName( rs.getString("name")  );
                    service.setPrice( rs.getBigDecimal("price"));


                }


            }
        }catch ( SQLException e  ){
            System.out.println( " error retrieving user " +  e.getMessage()  );
            e.printStackTrace();
        }

        return Optional.of( service ) ;

    }

    @Override
    public Service update(Service entity) {

        Connection connection = DBConnection.getConnection();

        try {
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(update);

            statement.setString(1 , entity.getName() );
            statement.setBigDecimal( 2 , entity.getPrice());
            statement.setInt(3 , entity.getId());

            statement.execute();
            connection.commit();
            statement.close();


        }catch ( SQLException e) {
            try {
                connection.rollback();

            }catch ( SQLException e1 ){
                DBConnection.handleSqlException( "update rollback" ,   e1  ); }

            DBConnection.handleSqlException("update " , e );
        }

        Service service12 = new Service();
        service12.setId(50);
        service12.setName("returned");

        // .get   means    // if the value in that optional  return it

        return this.GetOne( entity.getId()).get()  ;

    }

    @Override
    public void delete(Integer id)  {

        // delete operation      in a transaction

        Connection connection = DBConnection.getConnection();


        try  {

            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(delete);
            statement.setInt(1 , id );
            statement.executeUpdate();

            connection.commit();
            statement.close();

        }catch ( SQLException e  ){

            try {

                connection.rollback();

            }catch (SQLException e1 ){

                DBConnection.handleSqlException("delete rollback " , e1 );
                //System.out.println( " delete  rollback  " +  e.getMessage()  );
                //e1.printStackTrace();

            }

            DBConnection.handleSqlException("delete rollback " , e );
            //System.out.println( " delete    " +  e.getMessage()  );
            //e.printStackTrace();


        }

    }


    // process result set that came from   sql query

    // method process result  set
    // return   list < services >
    // input result set        // throws   sql  exception

    public List<Service> processResultSet (ResultSet rs )  throws SQLException {

        List<Service> services = new ArrayList<>();

        while ( rs.next() ){

            Service service = new Service();
            service.setId( rs.getInt("service_id")  );
            service.setName( rs.getString("name")   );
            service.setPrice( rs.getBigDecimal("price"));
            services.add( service );

        }
        return services;

    }






}
