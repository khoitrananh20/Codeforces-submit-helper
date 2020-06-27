#include <bits/stdc++.h>
using namespace std;

#define endl "\n"

#define ll long long
#define vii vector<int,int>
#define vll vector<ll,ll>
#define pii pair<int,int>
#define pll pair<ll,ll>

#define fori(i, n) for(int i = 0; i < n; ++i)

ll getRand(ll l, ll h)
{
    return l + ((ll)rand() * (RAND_MAX + 1) * (RAND_MAX + 1) * (RAND_MAX + 1) +
                (ll)rand() * (RAND_MAX + 1) * (RAND_MAX + 1) +
                (ll)rand() * (RAND_MAX + 1) +
                rand()) % (h - l + 1);
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);
    
    int a; cin >> a; cout << a+1;

    return 0;
}
