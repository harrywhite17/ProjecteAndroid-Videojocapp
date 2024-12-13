# comandes.py

# Classe per gestionar les comandes dels clients
class Comanda:
    # Constructor de la classe Comanda
    def __init__(self, id, productes):
        self.id = id
        self.productes = productes
        self.estat = "Pendent"

    # Mètode per afegir un producte a la comanda
    def afegir_producte(self, producte, quantitat):
        from errors import ProducteJaExisteix
        if producte in self.productes:
            raise ProducteJaExisteix
        self.productes[producte] = quantitat

    # Mètode per modificar l'estat de la comanda
    def modificar_estat(self, nou_estat):
        self.estat = nou_estat

    # Mètode per obtenir un resum de la comanda
    def resum(self):
        productes_str = ", ".join([f"{prod}: {quant}" for prod, quant in self.productes.items()])
        return f"Comanda {self.id} [{self.estat}]: {productes_str}"