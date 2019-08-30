
#decryption
#space,alpdic,dicalp,temp=playfair_space(key)
from playfair_space import playfair_space
def playfair_decrypt(cipher,key):
    text=""
    strs=cipher.split()
    for it in strs:
        text+=" "+decrypt(it,key,opt=True)
    return text.lstrip()

#decryption
def decrypt(cipher,space,opt=False):
    space,alpdic,dicalp,temp=playfair_space(key)
    step=2
    pairs=[]
    textint=[dicalp[i] for i in cipher]
    #print(textint)
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
        if ix1==ix2:
            ixx1=((temp.index(item[0])+5)%25)%5
            ixx2=((temp.index(item[1])+5)%25)%5
            row=temp.index(item[0])//5-1
            #print(row,ixx1)
            a=space[row%5][ixx1]
            row=temp.index(item[1])//5-1
            b=space[row%5][ixx2]
        elif cix1==cix2:
            col=temp.index(item[0])%5
            a=space[cix1][(col-1)%5]
            col=temp.index(item[1])%5
            b=space[cix2][(col-1)%5]
        else:
            ixx1=temp.index(item[0])//5
            ixx2=temp.index(item[1])%5
            a=space[ixx1][ixx2]
            ixx1=temp.index(item[1])//5
            ixx2=temp.index(item[0])%5
            b=space[ixx1][ixx2]
        enc_keys.append(a)
        enc_keys.append(b)
    dec1=""
    dec2=""
    for i in enc_keys:
        if type(alpdic[i])==list:
            dec1+=alpdic[i][1]
            dec2+=alpdic[i][0]
        else:
            dec1+=alpdic[i]
            dec2+=alpdic[i]
    if opt:
        return dec1
    if dec1!=dec2:
        return [dec1,dec2]
    else:
        return dec1
cipher=input("enter cipher text: ")
import itertools
from nltk.corpus import words
key_length=3
#cipher=input("enter cipher text: ")
keyspace=[''.join(x) for x in itertools.product('abcdefghklmnopqrstuvwxyz', repeat=key_length)]
for key in keyspace:
    space,alpdic,dicalp,temp=playfair_space(key)
    dec=playfair_decrypt(cipher,space)
    if dec[-1]=='z':
        dec=dec[0:-1]
    leng=len(dec.split())
    cnt=0
    for word in dec.split():
        if word in words.words():
            cnt+=1
    if cnt/leng >0.4:
        print("KEY: ",key,"PLAIN TEXT MAY BE: ",dec)
