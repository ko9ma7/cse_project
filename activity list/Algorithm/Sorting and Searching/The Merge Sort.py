def mergeSort(alist):
    # alist 배열의 길이가 1보다 크다면 더 작은 배열로 쪼개줘야 한다.
    if len(alist) > 1:
        mid = len(alist) // 2 # 중간 인덱스를 구하기 위한 mid
        lefthalf = alist[:mid] # 작은 배열의 왼쪽 부분
        righthalf = alist[mid:] # 작은 배열의 오른쪽 부분

        # 배열의 길이가 아직 1보다 크기 때문에 재귀 호출을 통해 계속해서 작게 쪼개준다.
        mergeSort(lefthalf)
        mergeSort(righthalf)

        print("lefthalf: ", lefthalf)
        print("righthalf: ", righthalf)
        i = 0
        j = 0
        k = 0

        # 병합하고자 하는 두 배열의 길이가 모두 0보다 크면
        while i < len(lefthalf) and j < len(righthalf):
            # 만약 왼쪽 배열의 값이 오른쪽 배열의 값보다 작다면 최종 배열에 왼쪽 배열의 값을 넣어야 한다.
            if lefthalf[i] <= righthalf[j]:
                alist[k] = lefthalf[i]
                i += 1
            else:
                alist[k] = righthalf[j]
                j += 1

            k += 1

        # 병합하고자 하는 배열 중 왼쪽 배열만 0보다 크고, 오른쪽 배열은 없다면
        while i < len(lefthalf):
            alist[k] = lefthalf[i]
            i += 1
            k += 1

        # 병합하고자 하는 배열 중 오른쪽 배열만 0보다 크고, 왼쪽 배열은 없다면
        while j < len(righthalf):
            alist[k] = righthalf[j]
            j += 1
            k += 1

        print("alist: ", alist)

    # alist 배열의 길이가 1이면 반환해준다.
    return alist

alist = [54,26,93,17,77,31,44,55,20]
mergeSort(alist)
print(alist)