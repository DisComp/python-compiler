
a=[22, True, "una lista muy larga", [1, 2], []]
print a
a.append(69)
print a
print a.index(True,2)
print a.index(True)
a.insert(5,"soyNuevo")
print a
a.pop(1)
print a
a.append(22)
print a.count(22)
print a.extend(a).append(69).index(True,2)+100

