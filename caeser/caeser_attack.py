from nltk.corpus import words
enc=input("ENTER CIPHER ONLY: ")
alp="abcdefghijklmnopqrstuvwxyz"
alpdic={}
dicalp={}
for i,it in enumerate(alp):
    alpdic[it]=i
    dicalp[i]=it
def caeser_decrypt(cipher,key):
    text=""
    for i in cipher:
        if i==' ':
            text+=" "
        else:
            text+=dicalp[(alpdic[i]-key)%26]
    return text
for i in range(1,26):
    dec=caeser_decrypt(enc,i)
    leng=len(dec.split())
    cnt=0
    for word in dec.split():
        if word in words.words():
            cnt+=1
    if cnt/leng >0.4:
        print(dec," from key ",i)
    else:
        print("KEY ",i," failed")
