// gameController.js
// Responsibility: Game state, turn control, undo logic, and merge mechanics.
// Role: Yutian Wang(Architecture & Control)

const GameState = {
    // Stage 1: Basic structures
    board: Array(15).fill(null).map(() => Array(15).fill(0)),
    currentPlayer: 1,
    gameStatus: 'IDLE',//Status:IDLE,PLAYING,ENDED
    timer: null,
    remainingTime: 30,//30 second per turn

    // Stage 2: Advanced mechanics
    moveHistory: [], // Stack to store history for "Undo" functionality
    isMergeEnabled: false // Toggle for the "Merge Pieces" mechanic
};
//Start or Reset the game
function startGame() {
    GameState.gameStatus = 'PLAYING';
    GameState.currentPlayer = 1;
    GameState.moveHistory = []; // Clear history for new game
    resetTurnTimer();
}
// Logic for placing a piece
//To be called by Yunlong Wang(UI) when a grid is clicked.
function handleMove(row, col) {
    if (GameState.gameStatus !== 'PLAYING') return;
    if (GameState.board[row][col] !== 0) return; // Cell already occupied

    // 1. Record move for Undo functionality
    GameState.moveHistory.push({
        row: row,
        col: col,
        player: GameState.currentPlayer
    });

       // 2. Update board data
        GameState.board[row][col] = GameState.currentPlayer;

        // 3. Stage 2: Check for Merge mechanic
        if (GameState.isMergeEnabled) {
            applyMergeLogic(row, col);
        }
            // 4. Call Jingzu Ge's winning algorithm
            // if (checkWin(row, col)) { endGame(); return; }

            switchTurn();
}
//Undo the last move (Stage 2 Feature)
//Uses a stack structure to revert the board state.
function undoMove() {
    if (GameState.moveHistory.length === 0) return;

    const lastMove = GameState.moveHistory.pop();
    GameState.board[lastMove.row][lastMove.col] = 0; // Clear the cell

// Revert turn and reset timer
    switchTurn();
}

//Apply the "Merge Pieces" special rule (Stage 2 Feature)
function applyMergeLogic(row, col) {
    // Placeholder for the specific merge rules you and Jingzu Ge define
    console.log(`Checking merge logic for move at [${row}, ${col}]`);
}
//Manage turn switching and countdown
function switchTurn() {
    clearInterval(GameState.timer);
    GameState.currentPlayer = (GameState.currentPlayer === 1) ? 2 : 1;
    GameState.remainingTime = 30;
    startCountdown();
}
function startCountdown() {
    GameState.timer = setInterval(() => {
        GameState.remainingTime--;
        // UI update call for Yunlong Wang goes here
        if (GameState.remainingTime <= 0) {
            handleTimeout();
        }
    }, 1000);
}
//Handle game end by timeout
function handleTimeout() {
    clearInterval(GameState.timer);
    GameState.gameStatus = 'ENDED';
    const winner = (GameState.currentPlayer === 1) ? 'White' : 'Black';
    console.log(`Timeout! ${winner} wins.`);
}