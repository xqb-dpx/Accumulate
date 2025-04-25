class Main:
    L = []
    """
    len
    [:-1]
    [y:x]
    """

    j = int(input())
    if j > 30:
        i = j
        for i in range(i, 40, 2):
            L.append(i)

    print(L[0:-1])