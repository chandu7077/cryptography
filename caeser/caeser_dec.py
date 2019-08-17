alp="abcdefghijklmnopqrstuvwxyz"
alpdic={}
dicalp={}
for i,it in enumerate(alp):
    alpdic[it]=i
    dicalp[i]=it
key=int(input("Enter the key(integer shift value): "))
text=input("Enter cipher to decrypt: ")
def caeser_decrypt(cipher,key):
    text=""
    for i in cipher:
        if i==' ':
            text+=" "
        else:
            text+=dicalp[(alpdic[i]-key)%26]
    return text
enc=caeser_decrypt(text,key)
print("decrypted text(PLAIN TEXT): ",enc)
