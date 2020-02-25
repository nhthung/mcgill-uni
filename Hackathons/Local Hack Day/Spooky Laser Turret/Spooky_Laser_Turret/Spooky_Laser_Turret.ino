#include <Servo.h>

// X-axis and Y-axis motors
Servo serX;
Servo serY;

String serialData;

void setup() {
  // put your setup code here, to run once
  // Connect each motors to the Pin #11 and #10
  serX.attach(10);
  serY.attach(11);

  // Start the serial, with a 9600 Baud Rate
  Serial.begin(9600);
  Serial.setTimeout(10);
}

void loop() {
  // put your main code here, to run repeatedly
}

void serialEvent() {
  // Read the data from the Serial : X00Y00
  serialData = Serial.readString();

  // Parse the data and set serX and serY accordingly
  serX.write(parseDataX(serialData));
  serY.write(parseDataY(serialData));
}

int parseDataX(String data) {
  // Parse the x-axis value
  // Remove everything starting at the Y pos
  data.remove(data.indexOf("Y"));

  // Remove the X
  data.remove(data.indexOf("X"), 1);

  return data.toInt();
}

int parseDataY(String data){
  // Parse the y-axis value
  // Remove everything before the Y pos including the Y char
  data.remove(0, data.indexOf("Y") + 1);

  return data.toInt();
}
