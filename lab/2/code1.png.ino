const int buz=8;

void setup() {
  // initialize serial:
  Serial.begin(9600);
  pinMode(buz, OUTPUT);
}

void loop() {
  // if there's any serial available, read it:
  if (Serial.available() > 0) {

    // look for the next valid integer in the incoming serial stream:
    //int i  = Serial.parseInt();
    // look for the next valid byte in the incoming serial stream:
    char buzzer_picked = Serial.read();

    int buzzer_freq  = Serial.parseInt();

    if (Serial.read()=='\n'){
      tone(buz, buzzer_freq); 
      //Serial.println(buzzer_freq); 
      Serial.println(buzzer_freq, HEX);
      Serial.println("la fréquence a changé");
    }

    //write_freq(buzzer_picked, buzzer_freq);
       
  }

}

void write_freq(char buzzer, int frequency)
{
    /*if (buzzer == 'f')
    {*/
        tone(buz, frequency);
        /*Serial.println(buzzer, DEC);*/
        Serial.println("Nouvelle fréquence : ");
        Serial.println(frequency);

        //return;
   /* }else{
      tone(buz, 450);
      return;
    }*/
    
    return;
}
