
const int pinRouge = 11;
const int pinVert = 10;
const int pinBleu = 9;
 
//uncomment this line if using a Common Anode LED
//#define COMMON_ANODE
 
void setup(){
  pinMode(pinRouge, OUTPUT);
  pinMode(pinVert, OUTPUT);
  pinMode(pinBleu, OUTPUT);  
}
 
void setColor(int red, int green, int blue){
  analogWrite(pinRouge, red);
  analogWrite(pinVert, green);
  analogWrite(pinBleu, blue);  
}
 
void loop(){
  setColor(255, 0, 0);  // red
  delay(500);
  setColor(0, 255, 0);  // green
  delay(500);
  setColor(0, 0, 255);  // blue
  delay(500);
  setColor(255, 255, 0);  // yellow
  delay(500);  
  setColor(80, 0, 80);  // purple
  delay(500);
  setColor(0, 255, 255);  // aqua
  delay(500);
}
