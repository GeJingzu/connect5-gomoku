// gameController.js
// Responsibility: Game state, turn control, undo logic, and merge mechanics.
// Role: Yutian Wang(Architecture & Control)

const GameState = {
    // Stage 1: Basic structures
    // 15x15 board: 0 for empty, 1 for Black, 2 for White
    board: Array(15).fill(null).map(() => Array(15).fill(0)),
    currentPlayer: 1,
    gameStatus: 'IDLE',//Status:IDLE,PLAYING,ENDED
    timer: null,
    remainingTime: 30,//30 second per turn

    // Stage 2: Advanced mechanics
    moveHistory: [], // Stack to store history for "Undo" functionality
    isMergeEnabled: false // Toggle for the "Merge Pieces" mechanic
};
