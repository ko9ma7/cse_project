/*
    1110. 징검다리 II

    광성이가 여자친구를 만나러 가기 위해서는 항상 징검다리를 건너야 합니다.
    그 징검다리의 각각의 돌에는 숫자가 쓰여 있는데, 1부터 쓰인 숫자 중 원하는 만큼 앞으로 전진할 수 있습니다.
    징검다리에 3이 씌어 있으면 1칸 혹은 2칸 혹은 3칸을 앞으로 갈 수 있습니다. 예를 들어 아래와 같이 징검다리가 있다면

        3, 2, 0, 5, 2, 1

    첫 번째, 네 번째를 밟고 다리를 건너갈 수 있습니다.
    다리에 쓰여진 숫자가 주어졌을 때, 광성이가 이 다리를 건너갈 수 있는지 판단하는 프로그램을 짜 주세요.

    # 입력

        첫 줄에는 징검다리의 돌의 개수 N (1 <= N <= 10, 000) 이 주어집니다.
        그 다음 줄에는 N개의 돌에 쓰여진 숫자가 공백으로 구분되어 주어집니다.

    # 출력
        건널 수 있으면  Yes, 건너지 못하면 No 를 출력해 주세요. (영문 대 소문자에 주의해 주세요)
*/

#include <iostream>
using namespace std;

int bridge[10001], N;

void dfs(int idx) {
	// 인덱스 위치가 배열의 길이를 넘어갈 경우 징검다리를 건넜다는 것을 알 수 있다.
	if (idx > N) {
		cout << "Yes" << "\n";
		exit(0);
	}

	for (int i = bridge[idx]; i >= 1; i--) {
		idx += i;
		dfs(idx);
		// dfs를 통해 건너갔을 때 징검다리의 숫자가 0일 경우 백트래킹
		// 원래 위치로 되돌아간다.
		idx -= i;
	}
}

int main(void) {
	cin.tie(NULL);
	cout.tie(NULL);
	ios_base::sync_with_stdio(false);

	cin >> N;
	for (int i = 1; i <= N; i++) cin >> bridge[i];
	dfs(1);
	cout << "No" << endl;
}