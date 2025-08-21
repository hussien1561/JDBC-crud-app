package data;

import data.dao.UserDAO;
import data.dao.serviceDao;
import data.entity.Service;
import data.entity.User;
import data.util.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class main {


    // to test the connection

    // if true
    // connection successful

    public static void main(String[] args) throws SQLException {

        try (Connection conn = DBConnection.getConnection() ){
            if( conn != null ){
                System.out.println(" connection successful ");
            }else {
                System.out.println("connection failed");
            }

        }catch( Exception e ){
            System.out.println("Connection error:" + e.getMessage()  );
            e.printStackTrace();
        }



        serviceDao serviceDao = new serviceDao();

        List<Service> services =  serviceDao.getAll();

        System.out.println("  get all services ");

        services.forEach(System.out::println );


        Service serviceOfPlanning = new Service();
        //serviceOfPlanning.setName("planning a sw  project ");
        //serviceOfPlanning.setPrice(BigDecimal.valueOf(250.5));
        //serviceOfPlanning.setId(4);

        //serviceOfPlanning =   serviceDao.update( serviceOfPlanning );

        System.out.println( serviceOfPlanning  );





        // get service by id // 2

        //Service service = new Service();
        // service =  serviceDao.GetOne(2);

        //  Optional<Service>     //   means the value may or may not be present

        //Optional<Service> service1 = serviceDao.GetOne( 2  );
        //System.out.println( "get the service " + service1   );


        // create service      values   planning 100.5

        // create (  service 0) ;

/*
        Service service5 = new Service();

        service5.setId(1);
        service5.setName("fixing");
        //service5.setPrice(BigDecimal.valueOf(100.5));

        service5.setPrice( new BigDecimal("99.5"));


        service5 = serviceDao.create(service5);




        // delete service of id 2

        System.out.println("delete service od id 2 ");

        serviceDao.delete(3);

        List<Service> services6 = new ArrayList<>();

        services6 =  serviceDao.getAll();

        System.out.println("  get all services ");

        services6.forEach(System.out::println );










        UserDAO userDAO = new UserDAO();


        User user1 = new User ();
        user1.setName("john");
        user1.setEmail("john345@gmail.com");

        userDAO.createUser( user1 );









        // update user

        // create user
        User user4 = new User();
        user4.setId(10);
        user4.setName("updated");
        user4.setEmail("updated123@gmail.com");

        boolean result = userDAO.updateUser( user4 );

        if (result ){
            System.out.println( "user updated ");
        }else {
            System.out.println("failed to update user ");
        }



        User  user12 = userDAO.getUser(17 );

        if ( user12!= null ){
            System.out.println( "user found : " + user12.getName() +  "--" +  user12.getEmail()  );

        }else {
            System.out.println("user not found ");
        }


        boolean result5  = userDAO.deleteUser(17);

        if ( result5 ) {
            System.out.println( "user deleted ");
        }else {
            System.out.println(  "user not found ");
        }





        // get all users


        List<User> users = userDAO.getAllUsers();

        if (  users.isEmpty() ){
            System.out.println( "no users is found ");
        } else {
            for ( User user3 : users ){

                System.out.println(user3.toString() );

                System.out.println( "id:   " +   user3.getId()    );
                System.out.println("name:   " +  user3.getName()  );
                System.out.println( "Email:   " + user3.getEmail()  ) ;
                System.out.println( "---------------------------------");


            }

        }


 */
    }



}
