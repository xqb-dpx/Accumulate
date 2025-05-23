from math import pi
import matplotlib.pyplot as plt
import numpy as np

# x = np.linspace(0, 2 * np.pi, 30)
# sin = np.sin(x)
# cos = np.cos(x)
# tan = np.tan(x)
px = np.linspace(-100, 2 * np.pi, 30)
parabolic = (px**2 + 3) / (px - 1)
# cot = 1 / np.tan(x)

# plt.plot(x, sin, "-.")
# plt.plot(x, cos, "-.")
# plt.plot(x, tan, "-.")
plt.plot(px, parabolic, "-o")
# plt.plot(x, cot, "-.")

plt.show()