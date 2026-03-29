/**
 * Game Controller - Managed by Member C
 * Responsibilities: Game state, turn management, and timekeeping.
 */

// --- 1. Game State Definitions ---
const GameState = {
    PLAYING: 'playing',
    PAUSED: 'paused',
    FINISHED: 'finished'
};

let currentState = GameState.PLAYING;
let currentPlayer = 1; // 1 for Black, 2 for White [cite: 13]
let timer = null;
let timeLeft = 30; // 30 seconds per turn [cite: 14]

// --- 2. Turn Management ---

/**
 * Switches the turn between players.
 * This should be called after a valid move is validated by Member A.
 */
function switchTurn() {
    if (currentState !== GameState.PLAYING) return;

    // Toggle player: 1 -> 2 or 2 -> 1
    currentPlayer = (currentPlayer === 1) ? 2 : 1;

    console.log(`Current Turn: Player ${currentPlayer}`);

    // Reset the countdown for the new turn [cite: 14]
    resetTimer();
}

// --- 3. Timekeeping & Timeout Logic ---

/**
 * Starts the countdown timer for the current turn.
 */
function startTimer() {
    // Clear any existing timer to prevent memory leaks or speed-ups
    if (timer) clearInterval(timer);

    timer = setInterval(() => {
        timeLeft--;

        // Update UI - Calls the UI update function (to be implemented by Member B)
        if (typeof updateTimerUI === "function") {
            updateTimerUI(timeLeft);
        }

        // Check for timeout loss [cite: 14]
        if (timeLeft <= 0) {
            handleTimeout();
        }
    }, 1000);
}

/**
 * Resets the timer back to the initial value.
 */
function resetTimer() {
    timeLeft = 30;
    startTimer();
}

/**
 * Handles the scenario when a player runs out of time.
 */
function handleTimeout() {
    clearInterval(timer);
    currentState = GameState.FINISHED;

    // The current player loses due to timeout [cite: 14]
    const winner = (currentPlayer === 1) ? "White" : "Black";

    alert(`Time is up! ${winner} wins the game!`);
}

// Initialize the game timer on load
startTimer();