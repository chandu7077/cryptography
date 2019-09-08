from des_keys import pc1,pc2,ip,ipinv,e,p,sboxes,f,xor,shift_left

def des_decrypt(text,key):
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
    for i in range(1,len(k)+1):
        #print(i)
        ltext.append(rtext[i-1])
        rtext.append(xor(ltext[i-1],f(rtext[i-1],k[15-(i-1)])))

    rl16=rtext[-1]+ltext[-1]
    rl16="".join([rl16[ipinv[i]-1] for i in range(0,len(ipinv))])
    finalhex="".join([hex(int(rl16[i:i+4],2))[2:] for i in range(0,len(rl16),4)])
    return finalhex.upper()

def des_dec(cipher,key):
    finalhex=""
    text=cipher
    key="".join([bin(int(c,16))[2:].zfill(4) for c in key])
    for i in range(0,len(text),16):
        temp=text[i:i+16]
        temp="".join([bin(int(c,16))[2:].zfill(4) for c in temp])
        finalhex+=des_decrypt(temp,key)
    bits="".join([bin(int(i,16))[2:].zfill(4) for i in finalhex])
    text="".join([chr(int(bits[i:i+8],2)) for i in range(0,len(bits),8) ])
    return text

cipher=input("enter cipher to decrypt: ")
key=input("enter key which is atleast 64 bits: ")
key="".join([bin(ord(key[i]))[2:].zfill(8) for i in range(0,len(key))])
key="".join([hex(int(key[i:i+4],2))[2:] for i in range(0,len(key),4)])
if len(key)>=16:
    key=key[0:16]
    key=key.upper()
    text=des_dec(cipher,key)
    print("Original cipher:--",cipher)
    print("Decrypted text:--",text)
else:
    print("key size is less than 64 bits")
