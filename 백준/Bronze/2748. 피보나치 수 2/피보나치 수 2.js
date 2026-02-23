const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const dp = new Array(91).fill(-1);

dp[0] = 0;
dp[1] = 1;

function fib(n) {
    if (dp[n] >= 0) {
        return dp[n];
    }
    dp[n] = BigInt(fib(n - 1)) + BigInt(fib(n - 2));
    return dp[n];
}
console.log(fib(Number(input[0])) + '');