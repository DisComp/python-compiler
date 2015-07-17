print "**********Programa2(variables and scopes tests)************"
i = 1
print i+") **Test functions**"
i=i+1
def f():
	a=3
	print "a dentro de f vale "+a
#************************
def g(a):
	a=10
	print "a en g vale "+a
#************************
def h(a,b,c):
	print "valores de entrada en h: "+a+", "+b+", "+c
#************************
def porDos(a):
	return a*2
#************************
a=1
print "a vale "+a
f()
print "a vale "+a
g(a)
print "a vale "+a
h(a,2,3)
print "a vale "+a
print "porDos(a) vale: "+porDos(a)
print "a vale "+a
print i+") **Test statements**"
i=i+1
#***************************
if(a>0):
	print "a mayor a 0"
else:
	print "a menor igual a 0"
if(a>10):
	print "a mayor a 10"
else:
	print "a menor igual a 10"
#***************************
if(a==1):
	aPrima =5
print "aPrima vale fuera del if que la define vale "+aPrima
iter=a
#***************************
print "while con contador:"
while(iter<=10):
	print "iteracion "+iter
	iter=iter+1
iter=a
#***************************
print "while con break:"
while(True):
	print "iteracion "+iter
	iter=iter+1
	if(iter==11):
		break
#***************************
edad = 0
print "for:"
for nom in ["juan","ana","pepe"]:
	print "hola "+nom

#***************************
print "while con continue"
while (edad < 18):
	edad = edad + 1
	if ((edad % 2) == 0):
		continue
	print "Felicidades, tienes " + str(edad)
#***************************
print "for con continue"
for num in[1,2,3,4,5,6]:
	if ((num % 2) != 0):
		continue
	print "par: "+num
print "FIN"


