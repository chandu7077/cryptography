# caeser encryption
alp="abcdefghijklmnopqrstuvwxyz"
alpdic={}
dicalp={}
for i,it in enumerate(alp):
    alpdic[it]=i
    dicalp[i]=it
key=int(input("Enter the key(integer shift value): "))
text=input("Enter data to encrypt: ")
def caeser_encrypt(text,key):
    cipher=""
    for i in text:
        if i==' ':
            cipher+=" "
        else:
            cipher+=dicalp[(alpdic[i]+key)%26]
    return cipher
enc=caeser_encrypt(text,key)
print("encrypted text(CIPHER): ",enc)
