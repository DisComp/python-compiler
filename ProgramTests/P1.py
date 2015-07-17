print "**********Programa1************"
i = 1
print i+") **Test listas**"
i=i+1
a=[22, True, "una lista muy larga", [1, 2], []]
print "a vale:"
print a
a.extend(a)
print "a.expand(a):"
print a
a.append(69)
print "a.append(69):"
print a
print "a.index(True,2): "+a.index(True,2)
print "a.index(True): "+ a.index(True)
a.insert(5,"soyNuevo")
print "a.insert(5,'soyNuevo'):"
print a
print "a.count(22): "+a.count(22)
print "a.count('pepe'): "+a.count('pepe')
print "a.extend(a).append(69).index(True,2)+100: "+(a.extend(a).append(69).index(True,2)+100)
print " \n"
print i+") **Test diccionarios**"
i=i+1
d = {
	"Love Actually" : "Richard Curtis",
	"Kill Bill": "Tarantino",
	"Amélie": "Jean-Pierre Jeunet",
}
print d
d1 = {"Love Actually" : "Richard Curtis","Kill Bill": "Tarantino","Amélie": "Jean-Pierre Jeunet"}
print d1
print "d.has_key('Love Actually'): "+d.has_key("Love Actually")
print "d.has_key('Love'): "+d.has_key("Love")
print "d.items(): "+d.items()
print "d.keys(): "+d.keys()
d.pop('Love Actually')
print "d.pop('Love Actually'): "+d
print "d.values(): "+d.values()
print " \n"
print i+") **Test strings**"
s="holahola"
print "s vale: "+s
#print s.count('hola')
print "s.find('la'): "+s.find('la')
print "s.find('pepe'): "+s.find('pepe')
print "s.find('la',3): "+s.find('la',3)
#print s.join([1,2,3,4])
#print s.split(',')
print "s.replace('hola','h'): "+s.replace("hola","h")
print "s.length(): "+s.length()

