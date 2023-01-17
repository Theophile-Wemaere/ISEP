import numpy as np 
import csv
import matplotlib.pyplot as plt

x=[]    #abscisse des points du graphique
y=[]    #ordonnée des points du graphique

ta1=0   #Temps de début d'accélération
ta2=0   #Temps de fin d'accélération
td1=0   #Temps de début de décélération
td2=0   #Temps de fin de décélération
acceleration=0     #Accélération moyenne
deceleration=0     #Décélération moyenne 
g=9.81  #Force de gravité terrestre 

print('--------Données obtenues graphiquemment--------\n')

with open('montée_ascenseur_1.csv','r') as csvfile:
    plots = csv.reader(csvfile, delimiter = ';')
    for row in plots:
        if (float(row[0]) > 11.7 and float(row[0]) < 23):
            if (float(row[1]) > 1.007 ):
                x.append(float(row[0]))
                y.append(1.001)
            elif ( float(row[1]) < 0.993):
                x.append(float(row[0]))
                y.append(0.996)
            else:
                x.append(float(row[0]))
                y.append(float(row[1]))
        elif (float(row[0]) > 23 and float(row[0]) < 23.4 and float(row[1]) > 1.0075):
            x.append(float(row[0]))
            y.append(1.001)
        elif ((float(row[0]) > 11.3 and float(row[0]) < 11.9 and float(row[1]) <0.994)\
            or (float(row[0]) > 28 and float(row[0]) < 29 and float(row[1]) <0.994)):
            x.append(float(row[0]))
            y.append(0.996)
        else:
            x.append(float(row[0]))
            y.append(float(row[1]))

        if (float(row[1]) > 1.02 and ta1==0 ):
            ta1=float(row[0])
        if (float(row[0])>ta1 and float(row[1]) < 0.9945 and ta2==0 ):
            ta2=float(row[0])
        
        if (float(row[0])>22 and float(row[1]) < 0.97 and td1==0 ):
            td1=float(row[0])
        if (float(row[0])>24 and float(row[1]) > 1.01 and td2==0 ):
            td2=float(row[0])

    

with open('test_1(modif).csv','r') as csvfile:
    plots = csv.reader(csvfile, delimiter = ';')
    nb_valeur_acceleration =0;
    nb_valeur_deceleration =0;
    for row in plots:
        if (ta1<float(row[0])<ta2):
            acceleration += float(row[1])
            nb_valeur_acceleration+=1
        if (td1<float(row[0])<td2):
            deceleration += float(row[1])
            nb_valeur_deceleration+=1
    acceleration=(acceleration*g/nb_valeur_acceleration)-g
    deceleration=(deceleration*g/nb_valeur_deceleration)-g

dta = ta2-ta1
print('Valeurs caractéristiques de l\'accélération initiale\n',
'Temps accélération 1: ',ta1, '\n Temps accélération 2 : ', ta2, '\n Durée de l\acceleration dt = ' , dta,'\n')

dtd = td2-td1
print('Valeurs caractéristiques de la décélération initiale\n',
'Temps décélération 1: ',td1, '\n Temps décélération 2 : ', td2, '\n Durée de la décélération dt = ' , dtd,'\n')

print('Accélération moyenne: ', acceleration)
print('Décélération moyenne: ', deceleration)

print('\n--------Analyse des résultat et calcul de la distance parcourure--------\n')

print('On détermine la valeur de la vitesse obtenue après la phase d\'accélération par la formule a*(t1-t0)+v0 = v1 ')
vitesse=acceleration*dta
print('Vitesse atteinte v1=', vitesse)

print('\nOn repère grâce au graphique 3 phases différentes, à savoir : \n',
        '(1): Phase de déplacement rectiligne uniformément accéléré.\n',
        '(2): Phase de déplacement rectiligne uniforme.\n',
        '(3): Phase de déplacement rectiligne uniformément décéléré.\n')

print('Etude de (1): \n')
print('On détermine la valeur de la distance réalisée durant la phase d\'accélération par la formule x1(t)=a*t²+v0*t+x0')
distance1=acceleration*(dta*dta)
print('Distance réalisée pendant la phase (1): x1=', distance1)

print('\nEtude de (2): \n')
print('On détermine la valeur de la distance réalisée durant la phase (2) par la formule x2(t)=v1*t+x0')
distance2=vitesse*(td1-ta2)
print('Distance réalisée pendant la phase (2): x2=', distance2)

print('\nEtude de (3): \n')
print('On détermine la valeur de la distance réalisée durant la phase de décélération par la formule x3(t)=-a*t²+v1*t+x0')
distance3=-deceleration*(dta*dta)+vitesse*dta
print('Distance réalisée pendant la phase (3): x3=', distance3)

print('\nOn a donc parcourue sur l\'ensemble du déplacement Xtot= x1 + x2 + x3 ')
print('\nDistance totale parcourue : Xtot=', distance1+distance2+distance3)


plt.plot(x,y)
plt.xlabel('Temps (en seconde)')
plt.ylabel('Accélération (en m/s^-2')
plt.title("Accélération de la pesanteur soumise fonction du temps lors d'un déplacement dans un ascenseur")
plt.show()

