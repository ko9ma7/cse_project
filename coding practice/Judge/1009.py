'''
    1009. Endian

    엔디언(Endian or Endianness)은 컴퓨터의 메모리와 같은 1차원의 공간에 여러 개의 연속된 대상을 배열하는 방법을 뜻하며,
    바이트를 배열하는 방법을 특히 바이트 순서(Byte order)라 한다.
    엔디언은 보통 큰 단위가 앞에 나오는 빅 엔디언(Big-endian)과 작은 단위가 앞에 나오는 리틀 엔디언(Little-endian)으로 나눌 수 있다.

    예를 들어 20141127 을 big endian 과 little endian 으로 표현하면 아래와 같다.

        00000001 00110011 01010100 01000111 (Big endian)
        01000111 01010100 00110011 00000001 (Little endian)

    32bit signed ingeger 정수가 주어졌을때 서로 다른 엔디언으로 바꿔 10진수 형태로 출력하라.
'''

