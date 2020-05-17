const int buz=5;

void setup() {
 
  Serial.begin(9600);  // on prépare un port de communication série
  pinMode(buz, OUTPUT); // on met le pin digital 5 comme sortie
}

void loop() {
  // if there's any serial available, read it:
  while (Serial.available() > 0) { //on vérifie si le nombre de byte reçu est positif
    
    char buzzer_picked = Serial.read(); // récupéré toutes les entrées
    int buzzer_freq  = Serial.parseInt(); //récupère le premier entier trouvé
    
    //int buzzer_freq = buzzer_picked.toInt();
    write_and_ajust(buzzer_picked, buzzer_freq); //appel de la fonction
       
  }

}

// fonction permettant d'ajuster et d'afficher les valeurs entrantes
void write_and_ajust(char buzzer_picked, int frequency)
{
    if (Serial.read()=='\n'){
      tone(buz, frequency); 
      //Serial.println(buzzer_freq); 
      //Serial.println(frequency, HEX);
      Serial.print("la fréquence a changé : ");
      //Serial.println(buzzer_picked);
      Serial.println(buzzer_picked, DEC); //Affiche la valeur entrée en décimal
    }else{
      noTone(buz);  //Le buzzer ne sonne pas
      Serial.println("la fréquence n'a pas changé ");
    }
    
    //return;
}
