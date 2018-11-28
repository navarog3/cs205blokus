
public class Player {
	
		public String name;
		
		public int score;
		
		public boolean[] inventory = new boolean[21];
		
		
		public Player() {
			
			this.name = "Player";
			
			this.score = 0;
			
			int i = 0;
			for (i = 0; i < 21; i++) {
				this.inventory[i] = true;
			}
			
		}
		
		public Player(String name) {
			this.name = name;
			
			this.score = 0;
			
			int i = 0;
			for (i = 0; i < 21; i++) {
				this.inventory[i] = true;
			}
		}
		
}
