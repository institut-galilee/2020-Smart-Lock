
# Rapport tp 2

## Partie 2 : Communication série

Dans cette seconde partie, nous avions étudié le fonctionnement d'une communication série.

1. Dans un premier temps, il nous a été demandé de développer un programme arduino qui peut lire un octet saisi à partir du moniteur de communication série puis ajuste la fréquence de la passive buzzer en fonction de cette valeur entrée. Puis le programme doit renvoyé un message disant que la fréquence a changé.

Pour répondre à cette question, le programme que nous avions développé récupère toute valeur entière excepté les caractères (0-9 ou a-z). Si c'est une valeur entière, il renvoie un son en fonction de la valeur saisie et un message s'affiche indiquant que la fréquence a changé. Dans le cas contraire, aucune action n'est effectuée mais un message, indiquant que le buzzer ne sonne pas, est renvoyé.

Voici le programme correspondant :

```ino
const int buz=5;

void setup() {
  
  Serial.begin(9600); // préparation du port
  pinMode(buz, OUTPUT); //on met la sortie de pin digital à 5
}

void loop() {
 
  while (Serial.available() > 0) { //on vérifie si le nombre de byte reçu est positif
    
    char buzzer_picked = Serial.read(); // récupéré toutes les entrées
    int buzzer_freq  = Serial.parseInt(); //récupère le premier entier trouvé
    
    write_and_ajust(buzzer_picked, buzzer_freq); //appel de la fonction
       
  }

}

// fonction permettant d'ajuster et d'afficher les valeurs entrantes
void write_and_ajust(char buzzer_picked, int frequency)
{
    if (Serial.read()=='\n'){
      tone(buz, frequency); //génération d'un son en fonction de la fréquence
      Serial.print("la fréquence a changé : ");
      Serial.println(buzzer_picked, DEC); //Affiche la valeur entrée en décimal
    }else{
      noTone(buz);  //Le buzzer ne sonne pas
      Serial.println("la fréquence n'a pas changé ");
    }
    
}
  ```
  Nous avions ensuite effectué notre branchement.
  
       ![branchement buzzer](https://github.com/institut-galilee/2020-Smart-Lock/blob/master/lab/2/report/2/branchement_buzeer.jpeg)
   
   Voici une illustration des résultats renvoyés par le moniteur :
    
   ![resultat buzzer](https://github.com/institut-galilee/2020-Smart-Lock/blob/master/lab/2/report/2/resultat_moniteur_buzzer.png)
    
  2. Dans un second temps, nous avions effectué la connexion du LDR à arduino. 
  Pour celà nous avions effectué un branchement comprenant :
  - 1 ARDUINO UNO
  - 1 LDR
  - 1 resistor
  - 5 cables
  - 1 board
dont voici l'illustration :

 ![branchement_LDR](https://github.com/institut-galilee/2020-Smart-Lock/blob/master/lab/2/report/2/branchement%20LDR.jpeg)
 
 Ensuite nous avions écrit le programme qui y est lié :
   ```ino
    int sensorPin = A0; // select the input pin for LDR
    int sensorValue = 0; // variable to store the value coming from the sensor
    
  void setup() {
    Serial.begin(9600); //sets serial port for communication
  }
  
  void loop() {
    sensorValue = analogRead(sensorPin); // read the value from the sensor
    Serial.println(sensorValue); //prints the values coming from the sensor on the screen
    delay(100);
  }
   ```

Ce programme permet de récupéré les données captées par le LDR puis les affiche depuis le moniteur série, dont voici une illustration :

 ![données capturées par le LDR](https://github.com/institut-galilee/2020-Smart-Lock/blob/master/lab/2/report/2/ldr_capteur_haute_luminosit%C3%A9.png)

Pour tracer le graphe correspondant aux données entrantes dans le port série, nous nous sommes appuyé sur le programme python que voici.
Le port de communication par lequel les données sont envoyés est le port /dev/ttyACM0. Ceci est précisé dans le programme.

```python
from serial import Serial
import matplotlib.pyplot as plt

plt.ion()
i=0

ser = Serial('/dev/ttyACM0',9600) #Port utilisé /dev/ttyACM0
ser.close()
ser.open()
data = ser.readline()
splt=data.split()

if(len(splt)==1):
    fig,ax=plt.subplots()
    axs=[ax]
else:
	fig, axs = plt.subplots(len(splt), sharex=True)
	
while True:

	data = ser.readline()
	splt=data.split();
	for p in range(len(splt)):
		print(splt[p].decode())
		axs[p].scatter(i, float(splt[p].decode()))
		#axs[p].yaxis.set_major_locator(plt.MaxNLocator(4))
		
	print()
	i += 1
	plt.show()
	plt.pause(0.0001)  # Note this correction
 ```
Le graphe correspondant aux données captées par le LDR est :

![plot](https://github.com/institut-galilee/2020-Smart-Lock/blob/master/lab/2/report/2/plot.png)

### Fritzing sketch correspondant aux 2 branchements
![squelette](https://github.com/institut-galilee/2020-Smart-Lock/blob/master/lab/2/report/2/sketch.png)
  
