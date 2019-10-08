'''
    1028. 단색이 좋아좋아 HARD

    한 줄에 빨강, 파랑, 초록 색상의 공들이 섞여 있습니다.
    각 공의 색깔은 순서대로 R, B, G 로 표현합니다.

    여러분은 한 턴에 제일 앞의 공 혹은 제일 뒤의 공을 제거할 수 있습니다.
    한 색깔의 공만 남기기 위해서는 최소 몇 번의 턴이 필요할까요?
'''

RGB = list('RGGGBBGGB')

index = 0
count = 0
RGB_count = []
while index == len(RGB):
    if RGB[index] == RGB[index+1]:
        count += 1
    else:
        count += 1
        RGB_count.append(count)
        count = 0
    index += 1

print(RGB_count)