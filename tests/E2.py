print "*****test funcion con distinta firma y la última no existe********"
def f():
	print "sin parametros"
def f(a):
	print "parametro "+a
f()
f(1)
f(1,2)