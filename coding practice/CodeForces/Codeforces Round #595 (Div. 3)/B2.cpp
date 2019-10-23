#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include<algorithm>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <cstring>
#include <map>
#include <set>
const int MOD = 1e9 + 7;
const int MAX = 1e5;
typedef long long ll;
using namespace std;
int arr[201], ans[201], visited[201];

int dfs(int i, int cnt) {
   if (visited[i]) return cnt;

   visited[i] = 1;

   return ans[i] = dfs(arr[i], cnt + 1);
}
int main() {
   int T; cin >> T;
   while (T--) {
      int n; cin >> n;
      memset(ans, 0, sizeof(ans));
      memset(visited, 0, sizeof(visited));
      for (int i = 1; i <= n; i++)
         cin >> arr[i];


      int cnt = 0;
      for (int i = 1; i <= n; i++) {
         if (!visited[i]) {
            visited[i] = 1;
            ans[i] = dfs(arr[i], 1);
         }
      }
      for (int i = 1; i < n; i++) cout << ans[i] << " ";
      cout << ans[n] << '\n';
   }
}