from clients import Client
from comandes import Comanda
from productes import Producte
from errors import ProducteJaExisteix, ProducteNoExisteix

# Crear clients
anna = Client(1, "Anna", "anna@example.com")
pere = Client(2, "Pere", "pere@example.com")
joan = Client(3, "Joan", "joan@example.com")

# Crear comandes
comanda1 = Comanda(101, {"bicicleta": 1, "casc": 2})
comanda2 = Comanda(102, {"guants": 1})
comanda3 = Comanda(103, {"maillot": 1, "roda": 2})
comanda4 = Comanda(104, {"guants": 2})

# Assignar comandes als clients
anna.afegir_comanda(comanda1)
anna.afegir_comanda(comanda2)
pere.afegir_comanda(comanda3)
pere.afegir_comanda(comanda4)

# Mostrar comandes inicials
print("COMANDES DELS CLIENTS")
for client in [anna, pere, joan]:
    if client.consultar_comandes():
        print(f"Comandes del client {client.nom}: {len(client.consultar_comandes())}")
        for comanda in client.consultar_comandes():
            print(comanda.resum())
    else:
        print(f"El client {client.nom} no té cap comanda.")

# Afegir productes i gestionar errors
try:
    comanda1.afegir_producte("bicicleta", 1)  # Ja existeix
except ProducteJaExisteix:
    print("El producte bicicleta ja existeix a la comanda.")

try:
    comanda1.afegir_producte("patinet", 1)  # No existeix
except ProducteNoExisteix:
    print("El producte patinet no existeix a la comanda.")

# Modificar comanda
try:
    comanda1.afegir_producte("casc", 2)  # Ja existeix
except ProducteJaExisteix:
    print("El producte casc ja existeix a la comanda.")

comanda1.afegir_producte("pantalons", 1)
comanda1.modificar_estat("Enviada")

# Mostrar comandes finals
print("COMANDES DELS CLIENTS")
print(f"Comandes del client Anna: {len(anna.consultar_comandes())}")
for comanda in anna.consultar_comandes():
    print(comanda.resum())
