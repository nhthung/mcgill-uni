Instructions for part A:

1. Configure XAMPP Apache settings to accept .py scripts as handler:

	Either add line:

	AddHandler cgi-script .py

	at the end of file /opt/lampp/etc/httpd.conf

	or add:

	.py

	to existing line: AddHandler cgi-script ... [add ".py" here]

	The path to httpd.conf can be different.
	It can be opened directly through the GUI.

2. Copy/move/extract 260793376 directory into XAMPP's htdocs directory if not done already.
   Then cd into PartA directory

6. Change the shebang in main.py in case the path is different on tested machine

3. Set permissions of all files in working directory to 777 for good measure:

	At terminal:

	chmod -R 777 PATH_TO_DIR/PartA

4. Start XAMPP server

5. Go to localhost/260793376/PartA in browser. Assignment was tested to work in Chrome.
