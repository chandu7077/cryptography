from des_keys import pc1,pc2,ip,ipinv,e,p,sboxes,f,xor,shift_left
def des_encrypt(text,key):
    noshifts=[1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1]
    keyplus="".join([key[pc1[i]-1] for i in range(0,len(pc1))])
    #print(keyplus)
    c,d=[],[]
    c.append(keyplus[0:28])
    d.append(keyplus[28:])
    for i in range(1,len(noshifts)+1):
        c.append(shift_left(c[i-1],noshifts[i-1]))
    for i in range(1,len(noshifts)+1):
        d.append(shift_left(d[i-1],noshifts[i-1]))
    k=[]
    for i in range(1,17):
        temp=list(c[i]+d[i])
        k.append("".join([temp[pc2[i]-1] for i in range(0,len(pc2))]))
    text="".join([text[ip[i]-1] for i in range(0,len(ip))])
    ltext,rtext=[],[]
    ltext.append(text[0:32])
    rtext.append(text[32:])
    #rl=""
    for i in range(1,len(k)+1):
        ltext.append(rtext[i-1])
        rtext.append(xor(ltext[i-1],f(rtext[i-1],k[i-1])))
        rl=rtext[-1]+ltext[-1]
        rl="".join([rl[ipinv[i]-1] for i in range(0,len(ipinv))])
        rl="".join([hex(int(rl[i:i+4],2))[2:] for i in range(0,len(rl),4)])
        print("ROUND {0}:{1}".format(i,rl.upper()))
    rl16=rtext[-1]+ltext[-1]
    rl16="".join([rl16[ipinv[i]-1] for i in range(0,len(ipinv))])
    finalhex="".join([hex(int(rl16[i:i+4],2))[2:] for i in range(0,len(rl16),4)])
    return finalhex.upper()

def des_enc(text,key):
    bool=input("is input hexadecimal(y/n)?")
    name=text
    if bool=="n":
        text="".join([bin(ord(name[i]))[2:].zfill(8) for i in range(0,len(name))])
        text="".join([hex(int(text[i:i+4],2))[2:] for i in range(0,len(text),4)])+"0D0A"
    else:
        pass
        #text+="0D0A"
    key="".join([bin(int(c,16))[2:].zfill(4) for c in key])
    while len(text)%16!=0:
        text+='0'
    text=text.upper()
    #print(text)
    finalhex=""
    for i in range(0,len(text),16):
        temp=text[i:i+16]
        temp="".join([bin(int(c,16))[2:].zfill(4) for c in temp])
        finalhex+=des_encrypt(temp,key)
    return finalhex

text=input("enter text to encrypt: ")
key=input("enter key which is atleast 64 bits: ")
bool=input("is key hexadecimal(y/n)?")
if bool=="n":
    key="".join([bin(ord(key[i]))[2:].zfill(8) for i in range(0,len(key))])
    key="".join([hex(int(key[i:i+4],2))[2:] for i in range(0,len(key),4)])
if len(key)>=16:
    key=key[0:16]
    key=key.upper()
    cipher=des_enc(text,key)
    print("Original text:--",text)
    print("Encrypted text:--",cipher)
else:
    print("key size is less than 64 bits")
