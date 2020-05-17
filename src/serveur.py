from serial import Serial
import mysql.connector as MS
from mysql.connector import Error
from mysql.connector import errorcode
from datetime import datetime


#Fonction d'insertion des données
def insererDonnees(dateE, evt):
    try:
        connexion = MS.connect(host='localhost',database='smartlock',user='root',password='')
        curseur = connexion.cursor()

        requete_insertion = """INSERT INTO historique (date_evenement, evenement) VALUES (%s, %s) """
        
        enregistrement = (dateE, evt)
        curseur.execute(requete_insertion, enregistrement)
        connexion.commit()
        print("Ligne insérée avec succès dans la table")
    
    except MS.Error as error:
        print("Echec d'insertion de la ligne dans la table {}".format(error))

    finally:
        if (connexion.is_connected()):
            curseur.close()
            connexion.close()
            #print("insertion à MYSQL terminée")


#Récupération des données à partir du port série Arduino
ser = Serial('/dev/ttyACM1',9600)

while True:
    data = ser.readline() #chaine de caractère comprenant les caractère \n et \r
    val = data[0] #on transforme la donnée sous la forme entière
    print(val)
    now = datetime.now()
    new_date = now.strftime('%Y-%m-%d %H:%M:%S')
    if val==118:
        action="deverrouille"
        insererDonnees(new_date, action)
    elif val == 100:
        action = "verrouille"
        insererDonnees(new_date, action)
