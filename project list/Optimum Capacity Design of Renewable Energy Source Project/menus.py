from flask import Blueprint, render_template

menus_blueprint = Blueprint('menus', __name__)

@menus_blueprint.route('/dashboard')
def dashboard():
    return render_template(
        'dashboard.html', nav_menu="dash"
    )

if __name__ == '__main__':
    menus_blueprint.run(host="localhost", port="8080")