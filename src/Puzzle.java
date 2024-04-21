public class Puzzle 
	{
		private String puzzleID;
		private String question;
		private String answer;
		private String type; //This is needed for the requirements
		String location;
		private int attempts;
		private boolean isSolved;
		private int hintsGiven;
		private boolean rewardsPuzzle; //one of the puzzle rewards is unlocking a room while the rest give items.
		private String itemReward; //This will be the name of the item that will be given.
		private String[] hints;  // An array or list of hints
		private int hintIndex;

		// Constructor
		public Puzzle(String id, String type, String question, String answer, String location, boolean rewardsPuzzle, String itemReward, String[] hints) 
			{
				this.puzzleID = id;
				this.type = type;
				this.question = question;
				this.answer = answer;
				this.location = location;
				this.attempts = 4; // Default number of attempts
				this.isSolved = false;
				this.hints = hints;
				this.hintIndex = 0;
				this.rewardsPuzzle = rewardsPuzzle;
				this.itemReward = itemReward;
			}

		//Some of these methods should be in the player class, if not all.

		// Method to return puzzle question for examination
		public String examine() 
			{
				if (!isSolved) 
					{
						return "Puzzle " + this.puzzleID + ": " + this.question;
					} 
				else 
					{
						return "Puzzle " + this.puzzleID + " has already been solved.";
					}
			}

		//Important: the method should also give an item to the player when solved,
		//or unlock a room if prompted (This part can be worked on later).
		// Method to attempt to solve the puzzle, returns result as a string
		public String solve(String playerInput) 
			{
				if (!isSolved && attempts > 0) 
					{
						attempts--;
						if (this.answer.equalsIgnoreCase(playerInput)) 
							{
								isSolved = true;
								return "Correct! The puzzle is solved.";
							} 
						else 
							{
								return "Incorrect. Try again. Attempts left: " + attempts;
							}
					} 
				else 
					{
						return "No attempts left or puzzle already solved.";
					}
			}

		// Method to provide a hint, returns the hint or a message
		public String giveHint() 
			{
				if (!isSolved && hintIndex < hints.length) 
					{
						String hint = hints[hintIndex++];
						return "Hint for Puzzle " + this.puzzleID + ": " + hint;
					} 
				else 
					{
						return "No more hints available.";
					}
			}

		// Getters
		public boolean isSolved() 
			{
				return isSolved;
			}

		// Dummy implementation of generateHint(), should be properly implemented
		private String generateHint() 
			{
				// This method should generate a useful hint based on the puzzle's specifics
				return "This is a generic hint.";
			}


		public void handleReward(Controller controller) 
			{
				if (isSolved && rewardsPuzzle) 
					{
						if (!itemReward.isEmpty()) 
							{
								controller.givePlayerItem(itemReward);
								System.out.println("Player has received " + itemReward + ".");
							} 
						else 
							{
								controller.unlockRoom(location);  // Assuming location holds room to be unlocked
								System.out.println("Room " + location + " has been unlocked.");
							}
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
