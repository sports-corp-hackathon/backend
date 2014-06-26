# set the path of your project

CURRENT_DIR=`pwd`

cd ~/Downloads

#
# Installing Flask: https://github.com/mitsuhiko/flask/tags
#
wget https://github.com/mitsuhiko/flask/archive/0.9.zip
unzip 0.9.zip
mv flask-0.9/flask $CURRENT_DIR/app/flask

#
# Installing Werkzeug: https://github.com/mitsuhiko/werkzeug/tags
#
wget https://github.com/mitsuhiko/werkzeug/archive/0.8.3.zip
unzip 0.8.3.zip
mv werkzeug-0.8.3/werkzeug $CURRENT_DIR/app/werkzeug

#
# Installing Jinja2: https://github.com/mitsuhiko/jinja2/tags
#
wget https://github.com/mitsuhiko/jinja2/archive/2.6.zip
unzip 2.6.zip
mv jinja2-2.6/jinja2 $CURRENT_DIR/app/jinja2

#
# Installing SimpleJson: https://github.com/simplejson/simplejson/tags
#
wget https://github.com/simplejson/simplejson/archive/v3.0.5.zip
unzip v3.0.5.zip
mv simplejson-3.0.5/simplejson $CURRENT_DIR/app/simplejson
