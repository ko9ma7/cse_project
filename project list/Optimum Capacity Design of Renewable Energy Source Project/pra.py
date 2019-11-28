import matplotlib.pyplot as plt
import numpy as np

# P_load = [0.33, 0.32, 0.32, 0.31, 0.30, 0.30, 0.34, 0.40, 0.50, 0.70, 0.69, 0.60, 0.55, 0.70, 0.68, 0.60, 0.53,
#               0.5, 0.47, 0.45, 0.4, 0.36, 0.34, 0.33]
#
# x_pos = [x for x in range(24)]
# y_pos = [round(P_load[idx], 3) for idx, y in enumerate(P_load)]
#
# fig = plt.figure()
# ax1 = fig.add_subplot(211)
# ax2 = fig.add_subplot(212)
# ax1.plot(x_pos, label='sound data')
# ax1.legend()
#
# ax2.plot(y_pos, label='normalized sound data')
# ax2.legend()
# fig.show()
#
#

x = np.arange(5)
y = np.exp(x)
z = np.sin(x)
w = np.cos(x)

fig1 = plt.figure()
ax1 = fig1.add_subplot(111)

fig2 = plt.figure()
ax2 = fig2.add_subplot(111)

fig3 = plt.figure()
ax3 = fig3.add_subplot(111)

ax1.plot(x, w)
ax2.plot(x, y)
ax3.plot(x, z)

plt.show()