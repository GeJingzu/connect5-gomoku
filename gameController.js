// 状态定义
const GameState = {
    PLAYING: 'playing',
    PAUSED: 'paused',
    FINISHED: 'finished'
};

let currentState = GameState.PLAYING;
let currentPlayer = 1; // 1 代表黑棋, 2 代表白棋
let timer = null;
let timeLeft = 30; // 初始倒计时 30秒

// 切换回合的函数
function switchTurn() {
    if (currentState !== GameState.PLAYING) return;

    // 1. 切换玩家
    currentPlayer = (currentPlayer === 1) ? 2 : 1;

    // 2. 重置倒计时
    resetTimer();

    console.log(`现在轮到玩家 ${currentPlayer} 落子`);
}