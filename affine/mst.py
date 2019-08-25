
maxint=10**5+1
class Graph(): 

    def __init__(self, vertices): 
        self.V = vertices 
        self.graph = [[0 for column in range(vertices)] for row in range(vertices)] 

    # A utility function to print the constructed MST stored in parent[] 
    def printMST(self, parent): 
        sum=0
        for i in range(1, self.V):
            sum+=self.graph[i][ parent[i] ]
        return sum

    # A utility function to find the vertex with 
    # minimum distance value, from the set of vertices 
    # not yet included in shortest path tree 
    def minKey(self, key, mstSet): 
        min = maxint 
        for v in range(self.V): 
            if key[v] < min and mstSet[v] == False: 
                min = key[v] 
                min_index = v 
        return min_index 
    # Function to construct and print MST for a graph 
    # represented using adjacency matrix representation 
    def primMST(self): 

    # Key values used to pick minimum weight edge in cut 
        key = [maxint] * self.V 
        parent = [None] * self.V # Array to store constructed MST 
    # Make key 0 so that this vertex is picked as first vertex 
        key[0] = 0
        mstSet = [False] * self.V 

        parent[0] = -1 # First node is always the root of 

        for cout in range(self.V): 

    # Pick the minimum distance vertex from 
    # the set of vertices not yet processed. 
    # u is always equal to src in first iteration 
            u = self.minKey(key, mstSet) 

    # Put the minimum distance vertex in 
    # the shortest path tree 
            mstSet[u] = True

    # Update dist value of the adjacent vertices 
    # of the picked vertex only if the current 
    # distance is greater than new distance and 
    # the vertex in not in the shotest path tree 
            for v in range(self.V): 
    # graph[u][v] is non zero only for adjacent vertices of m 
    # mstSet[v] is false for vertices not yet included in MST 
    # Update the key only if graph[u][v] is smaller than key[v] 
                if self.graph[u][v] > 0 and mstSet[v] == False and key[v] > self.graph[u][v]: 
                    key[v] = self.graph[u][v] 
                    parent[v] = u 

            self.printMST(parent) 

t=int(input())
for _ in range(t):
    n,m=map(int,input().split())
    g = Graph(n)
    for i in range(n):
        for j in range(n):
            if(i!=j):
                g.graph[i][j]=2
    for i in range(m):
        t1,t2=map(int,input().split())
        g.graph[t1-1][t2-1]=1
        g.graph[t2-1][t1-1]=1
print((g.primMST()))
print(g.graph)
