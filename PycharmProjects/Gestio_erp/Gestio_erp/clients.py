class Client:
    # Constructor de la classe Client
    def __init__(self, client_id, nom, email):
        self.client_id = client_id
        self.nom = nom
        self.email = email
        self.comandes = []

    # Mètode per afegir una comanda a la llista de comandes del client
    def afegir_comanda(self, comanda):
        self.comandes.append(comanda)

    # Mètode per consultar la llista de comandes del client
    def consultar_comandes(self):
        return self.comandes