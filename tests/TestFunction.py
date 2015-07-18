def f():
	def fPrima():
		print "llegue"
	fPrima()
	True
def g():
	f()
def g(a,b,c):
	a=a+1
	b=b+1
	c.append(a)
	print a
	print b
	print c
x=1
y=10
z=[22, True, "una lista muy larga", [1, 2], []]
print "------preFunc-------"
print x
print y
print z
print "------inFunc-------"
g(x,y,z)
print "------postFunc-------"
print x
print y
print z.extend(z).size()+1