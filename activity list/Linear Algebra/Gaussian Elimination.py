import numpy as np

A = np.array([[1, 1, 2], [-1, 3, 1], [0, 5, 2]])
E1 = np.array([[1, 0, 3], [0, 1, 0], [0, 0, 1]])

print(E1 @ A)