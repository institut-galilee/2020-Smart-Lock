const int led = 5;
 
void setup() {
 
  pinMode(led, OUTPUT);
  //analogWrite(led, 0);
}

void loop() {
  /*// A fond pendant 2 secondes
    analogWrite(led, 255);
    delay(2000);
 
    // Puis à 50% pendant 3 secondes
    analogWrite(led, 128);
    delay(3000);
 
    // Et enfin OFF pendant 1 seconde
    analogWrite(led, 0);
    delay(1000);
*/
for(int i = 0; i < 255; i++) {
    analogWrite(led, i);
    delay(10);
  }
}
