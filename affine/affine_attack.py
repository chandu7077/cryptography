from nltk.corpus import words
#kp=input("enter known plain text: ")
cp=input("enter cipher text: ")
import math
keyspace=[]
for i in range(0,26):
    if math.gcd(i,26)==1:
        keyspace.append(i)
inv={}
def minv(a,b): 
    x,y,u,v= 0,1,1,0
    while a != 0:
        q,r=b//a,b%a 
        m,n=x-u*q,y-v*q 
        b,a,x,y,u,v=a,r,u,v,m,n 
    gcd=b 
    return x%26
for i in keyspace:
    inv[i]=minv(i,26)
def affine_decrypt(enc,key):
    global inv
    a,b=key[0],key[1]
    dec=""
    alp="abcdefghijklmnopqrstuvwxyz"
    alplist=list(alp)
    alpdic={}
    dicalp={}
    for i,it in enumerate(alp):
        alpdic[i]=it
        dicalp[it]=i
    for i in enc:
        d=(dicalp[i]-b)*inv[a]
        dec+=alpdic[d%26]
    return dec
def affine_attack(cipher,kp):
    for i in keyspace:
        for j in range(0,26):
            enc=affine_decrypt(cipher,[i,j])
            if enc in words.words():
                print("KEY: ",[i,j]," TEXT: ",enc)
            else:
                print("KEY:",[i,j]," failed")
affine_attack(cp,kp="")

