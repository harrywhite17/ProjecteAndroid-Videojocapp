from flask import Flask, render_template, request, redirect, url_for
from models import db, Products  # Asegúrate de importar 'Products' en lugar de 'Product'

app = Flask(__name__)

# Configure the database URI
app.config['SQLALCHEMY_DATABASE_URI'] = 'oracle+oracledb://FLASK:flask@localhost:1521/?service_name=xe'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

# Start the database
db.init_app(app)

@app.route('/')
def index():
    products = Products.query.all()
    return render_template('index.html', products=products)

@app.route('/create', methods=['GET', 'POST'])
def create():
    if request.method == 'POST':
        name = request.form['name']
        description = request.form['description']
        price = float(request.form['price'])

        new_product = Products(name=name, description=description, price=price)
        db.session.add(new_product)
        db.session.commit()
        return redirect(url_for('index'))
    return render_template('create.html')

@app.route('/update/<int:id>', methods=['GET', 'POST'])
def update(id):
    # Usa 'Products' en lugar de 'Product'
    product = Products.query.get_or_404(id)
    if request.method == 'POST':
        product.name = request.form['name']
        product.description = request.form['description']
        product.price = float(request.form['price'])
        db.session.commit()
        return redirect(url_for('index'))
    return render_template('update.html', product=product)

@app.route('/delete/<int:id>', methods=['POST'])
def delete(id):
    product = Products.query.get_or_404(id)
    db.session.delete(product)
    db.session.commit()
    return redirect(url_for('index'))

if __name__ == '__main__':
    with app.app_context():
        db.create_all()
    app.run(debug=True)
