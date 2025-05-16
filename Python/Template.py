from math import degrees
import turtle
# class Main:
#     L = []
#     """
#     len
#     [:-1]
#     [y:x]
#     """

#     j = int(input())
#     if j > 30:
#         i = j
#         for i in range(i, 40, 2):
#             L.append(i)

#     print(L[0:-1])
def Multiple():
    operand = 0;
    while operand == 0:
        try:
            operand = int(input("enter multiplier: "))
            break
        except:
            print("is not correct! TRY AGAIN...")

    arr = [1, 2, 3]
    for i in range(len(arr)):
        arr[i] = arr[i] * operand
        print("step: {" + str(i) + "} ---" + str(arr[:]) + f" operation: {arr[i]} * {operand}")


def Paint():
    forw = 0
    degree = 0
    times = 0
    while forw == 0 and degree == 0 and times == 0:
        try:
            forw = int(input("Enter pixels to go: "))
            degree = int(input("Enter degree to go: "))
            times = int(input("Enter times to go: "))
        except:
            print("A input is incorrect")

    for j in range(times):
        turtle.forward(forw)
        turtle.left(degree)

mode = "";
while mode == "":
    try:
        print("Enter switch mode: \n ---{ simple multiple [a] \n ---{ paint [b]")
        mode = str(input())
    except:
        print("Switch mode is incorrect! TRY AGAIN...")

match mode:
    case "a":
        Multiple()
    case "b":
        Paint()