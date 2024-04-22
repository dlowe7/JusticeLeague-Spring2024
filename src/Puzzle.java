public class Puzzle {
	private String puzzleID;
	private String question;
	private String answer;
	private String type;
	private String location;
	private int attempts;
	private boolean isSolved;
	private int hintsGiven;
	private boolean rewardsPuzzle;
	private String itemReward;
	private String[] hints;  // Store multiple hints for dynamic provision.

	// Constructor
	public Puzzle(String id, String type, String question, String answer, String location,
			boolean rewardsPuzzle, String itemReward, String[] hints) {
				this.puzzleID = id;
				this.type = type;
				this.question = question;
				this.answer = answer;
				this.location = location;
				this.attempts = 4;
				this.isSolved = false;
				this.hintsGiven = 0;
				this.rewardsPuzzle = rewardsPuzzle;
				this.itemReward = itemReward;
				this.hints = hints;
				this.isSolved = false;
	}

	// Method to return puzzle question for examination
	public String examine() {
		if (!isSolved) {
			return "Puzzle " + this.puzzleID + ": " + this.question + " (Type: " + this.type + ")";
		} else {
			return "Puzzle " + this.puzzleID + " has already been solved.";
		}
	}

	// Attempt to solve the puzzle
	public String solve(String playerInput) {
	    if (isSolved) {
	        return "This puzzle has already been solved.";
	    }
	    if (attempts > 0) {
	        attempts--;
	        if (this.answer.equalsIgnoreCase(playerInput.trim())) { // Trim input to avoid accidental space issues
	            isSolved = true;
	            handleReward();  // Ensure this method updates the game state appropriately
	            return "Correct! The puzzle is solved. " + (rewardsPuzzle ? "You receive: " + itemReward : "");
	        } else {
	            return "Incorrect. Try again. Attempts left: " + attempts;
	        }
	    } else {
	        return "No attempts left to solve this puzzle.";
	    }
	}

	public boolean isSolved() {
        return isSolved;
    }
	
	public int getAttempts() {
        return attempts;
    }

	// Provide a hint, respects hint limits
	public String giveHint() {
	    if (hintsGiven < hints.length) {
	        return hints[hintsGiven++];
	    } else {
	        return "No more hints available.";
	    }
	}

	// Handles rewards and actions after solving the puzzle
	private void handleReward() {
		if (rewardsPuzzle && !itemReward.isEmpty()) {
			System.out.println("Player receives: " + itemReward);
			// Further integration to provide item to player goes here.
		}
	}

	public String getPuzzleID() 
		{
			return puzzleID;
		}

	public void setPuzzleID(String puzzleID) 
		{
			this.puzzleID = puzzleID;
		}

	public String getType() 
		{
			return type;
		}

	public void setType(String type) 
		{
			this.type = type;
		}

	public String getQuestion() 
		{
			return question;
		}

	public void setQuestion(String question) 
		{
			this.question = question;
		}

	public String getAnswer() 
		{
			return answer;
		}

	public void setAnswer(String answer) 
		{
			this.answer = answer;
		}

	public String getLocation() 
		{
			return location;
		}

	public void setLocation(String location) 
		{
			this.location = location;
		}

	public boolean isRewardsPuzzle() 
		{
			return rewardsPuzzle;
		}

	public void setRewardsPuzzle(boolean rewardsPuzzle) 
		{
			this.rewardsPuzzle = rewardsPuzzle;
		}

	public String getItemReward() 
		{
			return itemReward;
		}

	public void setItemReward(String itemReward) 
		{
			this.itemReward = itemReward;
		}
}
