
def affine_encrypt(text,key):
    a,b=key[0],key[1]
    alp="abcdefghijklmnopqrstuvwxyz"
    alplist=list(alp)
    alpdic={}
    dicalp={}
    for i,it in enumerate(alp):
        alpdic[i]=it
        dicalp[it]=i
    #print(alpdic)
    enc=""
    for i in text:
        c=dicalp[i]*a+b
        enc+=alpdic[c%26]
    return enc
text=input("Enter string to encrypt: ")
key=list(map(int,input("enter key: ").split()))
cipher=affine_encrypt(text,key)
print("encrypted text: ",cipher)

