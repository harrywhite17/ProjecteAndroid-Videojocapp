# __init__.py

# Importar les classes i errors del paquet
from .clients import Client
from .comandes import Comanda
from .productes import Producte
from .errors import ErrorGestioERP, ClientNoExisteix, ProducteJaExisteix, ProducteNoExisteix

# Definir quins elements seran accessibles quan s'importi el paquet
__all__ = ["Client", "Comanda", "Producte", "ErrorGestioERP", "ClientNoExisteix", "ProducteJaExisteix", "ProducteNoExisteix"]