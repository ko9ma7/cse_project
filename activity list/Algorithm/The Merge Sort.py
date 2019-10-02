# def sort(arr):
#     i = 0
#     for i in range(len(arr)):
#         if i == len(arr) - 1:
#             break
#         if arr[i] > arr[i+1]:
#             arr[i], arr[i+1] = arr[i+1], arr[i]
#         i += 1
#
#     return arr
#
# def merge_sort(arr):
#
#     # Step1. 좌, 우로 쪼개기
#     idx = 0
#     left_arr = []
#     right_arr = []
#     for idx in range(len(arr)):
#         if idx < len(arr) / 2:
#             left_arr.append(arr[idx])
#         else:
#             right_arr.append(arr[idx])
#
#         idx += 1
#
#     # Step2. sorting하기
#     integrated_arr = []
#     result = []
#     if len(left_arr) == 2 or len(right_arr) == 2:
#         print("sorting 시작")
#         left_arr = sort(left_arr)
#         right_arr = sort(right_arr)
#         integrated_arr = sort(left_arr + right_arr)
#         print(integrated_arr)
#         return integrated_arr
#     else:
#         result = merge_sort(left_arr) + merge_sort(right_arr)
#         print(sort(result))
#
# arr = [5, 1, 9, 2, 7, 3, 8, 4]
# merge_sort(arr)


# def mergeSort(alist):
#     # alist 배열의 길이가 1보다 크다면 더 작은 배열로 쪼개줘야 한다.
#     if len(alist) > 1:
#         mid = len(alist) // 2 # 중간 인덱스를 구하기 위한 mid
#         lefthalf = alist[:mid] # 작은 배열의 왼쪽 부분
#         righthalf = alist[mid:] # 작은 배열의 오른쪽 부분
#
#         # 배열의 길이가 아직 1보다 크기 때문에 재귀 호출을 통해 계속해서 작게 쪼개준다.
#         mergeSort(lefthalf)
#         mergeSort(righthalf)
#
#         i = 0
#         j = 0
#         k = 0
#
#         # 병합하고자 하는 두 배열의 길이가 모두 0보다 크면
#         while i < len(lefthalf) and j < len(righthalf):
#             # 만약 왼쪽 배열의 값이 오른쪽 배열의 값보다 작다면 최종 배열에 왼쪽 배열의 값을 넣어야 한다.
#             if lefthalf[i] <= righthalf[j]:
#                 alist[k] = lefthalf[i]
#                 i += 1
#             else:
#                 alist[k] = righthalf[j]
#                 j += 1
#
#             k += 1
#
#         # 병합하고자 하는 배열 중 왼쪽 배열만 0보다 크고, 오른쪽 배열은 없다면
#         while i < len(lefthalf):
#             alist[k] = lefthalf[i]
#             i += 1
#             k += 1
#
#         # 병합하고자 하는 배열 중 오른쪽 배열만 0보다 크고, 왼쪽 배열은 없다면
#         while j < len(righthalf):
#             alist[k] = righthalf[j]
#             j += 1
#             k += 1
#
#     return alist



def mergeSort(alist):
    # alist 배열의 길이가 1보다 작거나 같다면 그 배열은 더 이상 쪼갤 필요없이 반환한다.
    if len(alist) <= 1:
        return alist
    # alist 배열의 길이가 1보다 크다면 더 작은 배열로 쪼개줘야 한다.
    else:
        mid = len(alist) // 2 # 중간 인덱스를 구하기 위한 mid
        lefthalf = alist[:mid] # 작은 배열의 왼쪽 부분
        righthalf = alist[mid:] # 작은 배열의 오른쪽 부분

        # 배열의 길이가 아직 1보다 크기 때문에 재귀 호출을 통해 계속해서 작게 쪼개준다.
        print("쪼개는 중")
        lefthalf = mergeSort(lefthalf)
        righthalf = mergeSort(righthalf)

        print("병합 시작")
        # 배열을 모두 쪼개고 나서 새롭게 병합을 해주어야 한다.
        return merge(lefthalf, righthalf)

# 병합해주는 함수
def merge(left, right):
    result = []
    while len(left) > 0 or len(right) > 0:
        # 병합하고자 하는 두 배열의 길이가 모두 0보다 크면
        if len(left) > 0 and len(right) > 0:
            if left[0] <= right[0]:
                result.append(left[0])
                left = left[1:]
            else:
                result.append(right[0])
                right = right[1:]
        # 병합하고자 하는 배열 중 왼쪽 배열만 0보다 크고, 오른쪽 배열은 없다면
        elif len(left) > 0:
            # 왼쪽 배열의 첫 번째 값을 하나씩 넣어준다.
            result.append(left[0])
            left = left[1:]
        # 병합하고자 하는 배열 중 오른쪽 배열만 0보다 크고, 왼쪽 배열은 없다면
        elif len(right) > 0:
            # 오른쪽 배열의 첫 번째 값을 하나씩 넣어준다.
            result.append(right[0])
            right = right[1:]

    print(result)

arr = [5, 1, 9, 2, 7, 3, 8, 4]
print(mergeSort(arr))