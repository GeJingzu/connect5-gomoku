// --- 1. 游戏状态初始化 [cite: 14, 19] ---
const GameState = {
    PLAYING: 'playing',
    FINISHED: 'finished'
};

let currentState = GameState.PLAYING;
let currentPlayer = 1; // 1代表黑方，2代表白方
let timer = null;
let timeLeft = 30; // 设定每步倒计时

// --- 2. 核心状态管理逻辑  ---

/**
 * 切换回合函数
 * 当落子合法且未结束时，由组员 B 的交互逻辑调用此函数
 */
function switchTurn() {
    if (currentState !== GameState.PLAYING) return;

    // 切换玩家
    currentPlayer = (currentPlayer === 1) ? 2 : 1;

    // 重置倒计时
    resetTimer();

    console.log(`现在轮到玩家 ${currentPlayer} 落子`);
}

// --- 3. 倒计时与超时逻辑  ---

function startTimer() {
    // 确保不会有多个定时器同时运行
    clearInterval(timer);

    timer = setInterval(() => {
        timeLeft--;

        // 此处应调用组员 B 写的 UI 更新函数，显示剩余秒数
        if (typeof updateTimerUI === "function") {
            updateTimerUI(timeLeft);
        }

        // 超时检测
        if (timeLeft <= 0) {
            handleTimeout();
        }
    }, 1000);
}

function resetTimer() {
    timeLeft = 30;
    startTimer();
}

/**
 * 超时判负逻辑
 */
function handleTimeout() {
    clearInterval(timer);
    currentState = GameState.FINISHED;

    const loser = (currentPlayer === 1) ? "黑方" : "白方";
    const winner = (currentPlayer === 1) ? "白方" : "黑方";

    alert(`时间到！${loser}超时，恭喜${winner}获胜！`);
}

// 初始化启动
startTimer();