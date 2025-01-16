import oracledb

# Configura la conexió a la base de dades
dsn = oracledb.makedsn('localhost', 1521, service_name='xe')

try:
    # Conecta a la base de datos
    connection = oracledb.connect(user='FLASK', password='flask', dsn=dsn)

    # Executa una consulta
    cursor = connection.cursor()
    cursor.execute("SELECT table_name FROM user_tables")
    tables = cursor.fetchall()
    cursor.execute("INSERT INTO PRODUCTS (NAME, DESCRIPTION, PRICE) VALUES ('Bici', 'Mountain bike BTT', 879.89)")
    connection.commit()
    cursor.execute("SELECT * FROM PRODUCTS")
    products_table = cursor.fetchall()

    processed_rows = [
        (row[0], row[1], row[2].read() if isinstance(row[2], oracledb.LOB) else row[2], row[3])
        for row in products_table
    ]

    print("Taules de dades:", tables)
    print("Productes:", processed_rows)

except oracledb.DatabaseError as e:
    print("Error al conectar a la base de dades:", str(e))

finally:
    if 'connection' in locals() and connection:
        connection.close()
