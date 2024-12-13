# Definició de les excepcions personalitzades per a la gestió de l'ERP

# Classe base per a totes les excepcions de gestió de l'ERP
class ErrorGestioERP(Exception):
    pass

# Excepció per indicar que un client no existeix
class ClientNoExisteix(ErrorGestioERP):
    pass

# Excepció per indicar que un producte ja existeix en una comanda
class ProducteJaExisteix(ErrorGestioERP):
    pass

# Excepció per indicar que un producte no existeix en una comanda
class ProducteNoExisteix(ErrorGestioERP):
    pass

