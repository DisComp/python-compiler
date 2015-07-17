print "**********Programa2(variables and scopes tests)************"
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