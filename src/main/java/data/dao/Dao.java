package data.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao <T , Integer  >{

    List<T> getAll();
    T  create (T entity);
    Optional<T> GetOne( Integer  id   );
    T update (T entity);
    void delete ( Integer  id   ) throws SQLException;



}
