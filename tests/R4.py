def factorial(n):
	if n == 0:
		result = 1
	else:
		result = n * factorial(n-1)
	return result
i=1
while(i<=20):
	print "factorial("+i+")="+factorial(i)
	i=i+1