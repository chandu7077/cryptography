##encryption
from playfair_space import playfair_space
def playfair_encrypt(text,key):
    #space,alpdic,dicalp,temp=playfair_space(key)
    cipher=""
    strs=text.split()
    for it in strs:
        cipher+=" "+encrypt(it,key)
    return cipher.lstrip()
def encrypt(text,key):
    space,alpdic,dicalp,temp=playfair_space(key)
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
        if item[0]==item[1]:
            item[1]=dicalp["z"]
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
key=input("ENTER KEY FOR PLAYFAIR ENCRYPTION: ").replace(" ","")
text=input("text to be encrypted: ")
cipher=playfair_encrypt(text,key)
print("encrypted text: ",cipher)
