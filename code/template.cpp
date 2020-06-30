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
    srand(time(NULL));
    return l + ((ll)rand() * RAND_MAX * RAND_MAX * RAND_MAX +
                (ll)rand() * RAND_MAX * RAND_MAX +
                (ll)rand() * RAND_MAX +
                rand()) % (h - l + 1);
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);
    

    return 0;
}
