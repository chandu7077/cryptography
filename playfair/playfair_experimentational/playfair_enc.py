key=input("ENTER KEY FOR PLAYFAIR ENCRYPTION: ")
text=input("text to be encrypted: ")
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
    i1,i2=alplist[i],alplist[i+1]
    dicalp[i1]=0
    dicalp[i2]=0
    alpdic[0]=[i1,i2]
    alplist.remove(i1)
    alplist.remove(i2)
    temp.append(0)
    for i in alplist:
        temp.append(dicalp[i])
    for i in range(0,25,5):
        space[i//5]=temp[i:i+5]
    return space,alpdic,dicalp,temp
space,alpdic,dicalp,temp=playfair_space(key)

##encryption
def playfair_encrypt(text,key):
    textint=[]
    pairs=[]
    step=2
    extra="z"
    if len(text)%2==1:
        text+=extra
    for i in text:
        textint.append(dicalp[i])
    #textint=[17, 10, 9, 15, 15, 24]
    for i in range(0,len(textint),step):
        pairs.append(textint[i:i+step])
    enc_keys=[]
    for item in pairs:
        a,b=0,0
        ix1=temp.index(item[0])%5
        ix2=temp.index(item[1])%5
        cix1=temp.index(item[0])//5
        cix2=temp.index(item[1])//5
        #print(item)
        if ix1==ix2:
            ixx1=((temp.index(item[0])+5)%25)%5
            ixx2=((temp.index(item[1])+5)%25)%5
            row=temp.index(item[0])//5+1
            #print(row,ixx1)
            a=space[row%5][ixx1]
            row=temp.index(item[1])//5+1
            b=space[row%5][ixx2]
            
        elif cix1==cix2:
            col=temp.index(item[0])%5
            a=space[cix1][(col+1)%5]
            col=temp.index(item[1])%5
            b=space[cix2][(col+1)%5]
            
        else:
            ixx1=temp.index(item[0])//5
            ixx2=temp.index(item[1])%5
            a=space[ixx1][ixx2]
            ixx1=temp.index(item[1])//5
            ixx2=temp.index(item[0])%5
            b=space[ixx1][ixx2]
        enc_keys.append(a)
        enc_keys.append(b)
    enc=""
    for i in enc_keys:
        if type(alpdic[i])==list:
            enc+=alpdic[i][0]
        else:
            enc+=alpdic[i]
    return enc
cipher=playfair_encrypt(text,key)
print("ENCRYPTED TEXT: ",cipher)

