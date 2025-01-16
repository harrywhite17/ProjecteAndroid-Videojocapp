from flask_sqlalchemy import SQLAlchemy
from sqlalchemy import Column, Integer, String, DateTime, Boolean, ForeignKey
from sqlalchemy.orm import relationship, declarative_base

db = SQLAlchemy()
Base = declarative_base()

class Products(db.Model):
    __tablename__ = 'PRODUCTS'
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(100), nullable=False)
    description = db.Column(db.Text, nullable=True)
    price = db.Column(db.Float, nullable=False)

    def __repr__(self):
        return f'<Product {self.name}>'





# Model de Passatgers
class Passatger(Base):
    __tablename__ = 'passatgers'
    id = Column(Integer, primary_key=True, autoincrement=True)
    nom_usuari = Column(String, unique=True, nullable=False)
    contrasenya = Column(String, nullable=False)
    nom_complet = Column(String, nullable=False)
    telefon_fix = Column(String)
    telefon_mobil = Column(String)
    adreca = Column(String)
    data_naixement = Column(DateTime)

    # Relació 1:N amb Reserva
    reserves = relationship("Reserva", back_populates="passatger")


# Model de Reserva
class Reserva(Base):
    __tablename__ = 'reserves'
    id = Column(Integer, primary_key=True, autoincrement=True)
    data_hora_realitzat = Column(DateTime, nullable=False)
    confirmada_conductor = Column(Boolean, default=False)
    confirmada_passatger = Column(Boolean, default=False)

    # Foreign Key per Viatge (1:N)
    viatge_id = Column(Integer, ForeignKey('viatges.id'), nullable=False)
    viatge = relationship("Viatge", back_populates="reserves")

    # Foreign Key per Passatger (1:N)
    passatger_id = Column(Integer, ForeignKey('passatgers.id'), nullable=False)
    passatger = relationship("Passatger", back_populates="reserves")


# Model de Viatge
class Viatge(Base):
    __tablename__ = 'viatges'
    id = Column(Integer, primary_key=True, autoincrement=True)
    data_hora_inici = Column(DateTime, nullable=False)
    num_places_inicials = Column(Integer, nullable=False)

    # Relació 1:N amb Reserva
    reserves = relationship("Reserva", back_populates="viatge")