def playfair_space(key):
    alp="abcdefghijklmnopqrstuvwxyz"
    alplist=list(alp)
    alpdic={}
    dicalp={}
    for i,it in enumerate(alp):
        alpdic[i+1]=it
        dicalp[it]=i+1
    space=[[] for i in range(5)]
    temp=[]
    for i in range(0,len(key)):
        index=dicalp[key[i]]
        if index not in temp:
            temp.append(index)
            if key[i] in alplist:
                alplist.remove(key[i])
    i1,i2="i","j"
    dicalp[i1]=0
    dicalp[i2]=0
    alpdic[0]=[i1,i2]
    #print(alplist)
    flag=1
    for i in alplist:
        if i in ['i','j']:
            if flag:
                temp.append(0)
                flag=0
        else:
            temp.append(dicalp[i])
    #print(temp)
    for i in range(0,25,5):
        space[i//5]=temp[i:i+5]
    return space,alpdic,dicalp,temp
#space,alpdic,dicalp,temp=playfair_space(key)
