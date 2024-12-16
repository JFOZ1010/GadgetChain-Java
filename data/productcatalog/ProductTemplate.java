package data.productcatalog;

//import common.db.JdbcConnectionBuilder;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductTemplate implements Serializable {
    static final long serialVersionUID = 1L;

    private final String id;
    // private transient Product product;

    public ProductTemplate(String id) // Este es el constructor que setea el atributo id.
    {
        this.id = id;
    }
    //Y despues simplemente necesito el getter de ID y no más.
    public String getId() {
        return id;
    }
    /*
     * public Product getProduct()
     * {
     * return product;
     * }
     */
}
