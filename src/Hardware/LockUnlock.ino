#include "Servo.h"
#include "SoftwareSerial.h" //La bibliothèque SoftwareSerial permet la communication série sur d'autres broches autres que 0 et 1né 
#include <EEPROM.h>


//Ouverture de la communication série
SoftwareSerial MyBlue(2,3); // RX | TX
Servo myservo;

char state;
int i;

//Fonction d'initialisation
void setup() {
 myservo.attach(7);

//Initialisation/Reinitialisation de la position du servo-moteur
  if(EEPROM.read(0) == 1) 
    {                  
      myservo.write(70); // on remet le servo moteur à la position de départ de verrouillage
      delay(200);
    }
    
  else if(EEPROM.read(0) == 2)
    {
      myservo.write(120); // on remet le servo moteur à la position de départ de deverrouillage
      delay(200);
    }
 
   Serial.begin(9600);
   MyBlue.begin(9600); // démarre une communication série à la vitesse 9600
 
}

void loop() {
  if (MyBlue.available())// retourne le nombre de caractère à lire
  {
    state = MyBlue.read(); // retourne le prochain caractère reçu
     
 
  switch(state){
    
    case '1'://On verrouille
          Serial.println("verrouille");
          MyBlue.print("3"); //On envoie 3 à l'application android
           for(int a = 70; a <= 120; a++) // On verrouille la serrure
          {
            myservo.write(a);
            delay(15);  
           
          }
          EEPROM.write(0,1); //Sauvegarde de l'état courant de la serrure
          //l'état courant à 1 de la serrure est écrit à l'adresse 0 

      break;
      
    case '2': //On déverrouille
        Serial.println("deverrouille");
        MyBlue.print("4"); //On envoie 4 à l'application Android
        for(int x = 120; x >= 70; x--) // On déverrouille la serrure
          {
            myservo.write(x);
            delay(15);
          }
          EEPROM.write(0,2); //On stocke la valeur 2 à l'adresse 0
     
      break;
      
    case '3': //On récupère l'état actuel de notre serrure et on le renvoie à l'application
     
        if(EEPROM.read(0) == 1) // Si la serrure est verrouillée
        {
          //Serial.println("Cas 3 : Verrou");
          MyBlue.print("3");
        }
       
       else if(EEPROM.read(0) == 2) // Si la serrure est déverrouillée
        {
          //Serial.println("Cas 3 : Deverrou");
          MyBlue.print("4");
        }
        
       break;
   } 
 }
  

}
