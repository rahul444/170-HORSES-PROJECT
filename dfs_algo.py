input_file = open('./in/13.in')
lines = input_file.readlines()

V = int(lines[0])
matrix = [[0 for x in range(V)] for y in range(V)]

for i in range(1, V):
    line = lines[i].split()
    for j in range(len(line)):
        matrix[i-1][j] = int(line[j])
print(matrix)
