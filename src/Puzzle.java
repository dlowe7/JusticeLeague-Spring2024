public class Puzzle {
	
	private Map gameMap;
	private String puzzleID;
	private String question;
	private String answer;
	private String type;
	private String location;
	private int attempts;
    private int attemptsLeft;  // You should have a field that tracks the number of attempts left
	private boolean isSolved;
	private int hintsGiven;
	private boolean rewardsPuzzle;
	private String itemReward;
	private String[] hints;  // Store multiple hints for dynamic provision.
    private InventoryManager inventoryManager;



	// Constructor
	public Puzzle(String id, String type, String question, String answer, String location,
            boolean rewardsPuzzle, String itemReward, String[] hints, Map gameMap) {
				this.puzzleID = id;
				this.type = type;
				this.question = question;
				this.answer = answer;
				this.location = location;
				this.attempts = 4;
		        this.attemptsLeft = this.attempts;  // Initialize attemptsLeft
				this.isSolved = false;
				this.hintsGiven = 0;
				this.rewardsPuzzle = rewardsPuzzle;
				this.itemReward = itemReward;
				this.hints = hints;
				this.isSolved = false;
			    this.gameMap = gameMap; // Set the map reference


	}

	// Method to return puzzle question for examination
	public String examine() {
		if (!isSolved) {
			return "Puzzle Type: " + this.type + "\n" + "Question: " + this.question;
		} else {
			return "Puzzle " + this.puzzleID + " has already been solved.";
		}
	}

	// Attempt to solve the puzzle
	public String solve(String answer, Player player) {
	    if (isSolved) {
	        return "This puzzle has already been solved.";
	    }
	    if (attemptsLeft > 0) {
	        attemptsLeft--;
	        if (this.answer.equalsIgnoreCase(answer)) {
	            isSolved = true;
	            String solveMessage = "Correct! The puzzle is solved."; // Store success message first
	            handleReward(player); // Then handle the reward
	            return solveMessage; // Return the solve message
	        } else {
	            return "Incorrect. Try again. Attempts left: " + attemptsLeft;
	        }
	    } else {
	        return "No more attempts left to solve this puzzle.";
	    }
	}


	public boolean isSolved() {
        return isSolved;
    }
	
	public int getAttempts() {
        return attempts;
    }
	
	public boolean hasMoreAttempts() {
	    return attemptsLeft > 0;
	}

	public boolean canGiveHint() {
        return hintsGiven < hints.length;
    }
	// Provide a hint, respects hint limits
	public String giveHint() {
        if (canGiveHint()) {
            return hints[hintsGiven++];
        } else {
            return "No more hints available.";
        }
    }

	// Handles rewards and actions after solving the puzzle
	private void handleReward(Player player) {
        if (rewardsPuzzle && !itemReward.isEmpty()) {
            Items rewardItem = gameMap.fetchItemByName(itemReward);
            if (rewardItem != null) {
                player.addItemToInventory(rewardItem);
                System.out.println(View.GREEN + "\n\nPlayer receives: " + rewardItem.getName() + View.RESET);
            } else {
                System.out.println("Reward item '" + itemReward + "' not found.");
            }
        }
    }
	
	public void addItemToInventory(String itemName) {
        Items newItem = new Items(itemName);  // Assuming you have a constructor like this
        inventoryManager.addItem(newItem);  // Assuming InventoryManager handles item objects
    }

	public InventoryManager getInventoryManager() {
        return this.inventoryManager;
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
