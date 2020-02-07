import numpy as np

M = np.array([[1, 2, 3], [4, 5, 6], [7, 8, 9]])
v = np.array([[1], [2], [3]])

# Arithmetic Operations
print('1) M + M\n{}\n'.format(M + M))
print('2) M - M\n{}\n'.format(M - M))
print('3) M * M\n{}\n'.format(M * M))
print('4) M / M\n{}\n'.format(M / M))
print('5) M // M\n{}\n'.format(M // M))
print('6) M @ M\n{}\n'.format(M @ M))

# another calculate M @ M
from numpy.linalg import matrix_power

print('matrix_power(M, 2)\n{}\n'.format(matrix_power(M, 2)))

# Trnaspose
print('Transpose M: print(M.T)\n{}\n'.format(M.T))

# Inverse
from numpy.linalg import inv

A = np.array([[1, 1, 0], [0, 1, 1], [1, 1, 1]])
print('Inverse A: print(inv(A))\n{}\n'.format(inv(A)))

# Determinant
from numpy.linalg import det
print('Determinant A: print(det(M))\n{}\n'.format(det(M)))