

int led = 13;
void setup() {
  
  // put your setup code here, to run once:
  pinMode(led, OUTPUT); //Signale à l’Arduino que la connexion 13 doit pouvoir envoyer du courant

}

void loop() {
  
    digitalWrite(led,HIGH);// Demande à l’Arduino d’envoyer du courant dans la connexion 13
    delay(1000);// le temps d'allumage 
    digitalWrite(led,LOW);
    delay(1000);
    

  // put your main code here, to run repeatedly:

}
