Instructions for part B:

1. In etc/http.conf

    Change:
    DocumentRoot "/opt/lampp/htdocs"
    <Directory "/opt/lampp/htdocs">

    to:
    DocumentRoot "/opt/lampp/htdocs/260793376/PartB"
    <Directory "/opt/lampp/htdocs/260793376/PartB">

2. Copy/move/extract 260793376 directory into XAMPP's htdocs directory if not done already.
   Then cd into PartB directory

3. Change the shebang in main.py in case the path is different on tested machine

4. Set permissions of all files in working directory to 777 for good measure:

    At terminal:

    chmod -R 777 PATH_TO_DIR/PartB

5. Start XAMPP server

6. Go to localhost/ in browser. Assignment was tested to work in Chrome.
