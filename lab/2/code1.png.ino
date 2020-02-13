const int buz=8;

void setup() {
  // initialize serial:
  Serial.begin(9600);
  pinMode(buz, OUTPUT);
}

void loop() {
  // if there's any serial available, read it:
  if (Serial.available() > 0) {
    
    char buzzer_picked = Serial.read();

    int buzzer_freq  = Serial.parseInt();

    afficher_frequence(buzzer_picked, buzzer_freq);
       
  }

}

void afficher_frequence(char buzzer, int frequency)
{
    if (Serial.read()=='\n'){
      tone(buz, frequency); 
      //Serial.println(buzzer_freq); 
      Serial.println(frequency, HEX);
      Serial.println("la fréquence a changé");
    }
    
    return;
}
