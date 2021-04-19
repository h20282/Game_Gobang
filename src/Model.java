
public class Model {
	public static final int PLAYER_BLACK = 1;
	public static final int PLAYER_WHITE = 0;
	public static final int PLAYER_NONE = -1;
	
	private int[][] grid;
	private int width;
	private int height;
	
	private int step = 1;
	
	public Model(int[][] grid, int width, int height) {
		super();
		this.grid = grid;
		this.width = width;
		this.height = height;
	}

	public void playOn(int col, int row){
		
		if (col>=0 && col<width && row>=0 && row<height){
			if ( grid[row][col] == 0 && get_winner()==PLAYER_NONE) {
				grid[row][col] = step++;
			}
		}
	}
	
	public void regret() {
		int max_pos_x = 0;
		int max_pos_y = 0;
		for( int x=0; x<width; x++ ) {
			for(int y=0; y<height; y++) {
				if (grid[max_pos_y][max_pos_x] < grid[y][x] ) {
					max_pos_x = x;
					max_pos_y = y;
				}
			}
		}
		grid[max_pos_y][max_pos_x] = 0;
		if (step > 0){
			step--;
		}
		
	}
	
	public int get_winner(){
		// 0 
		int max_pos_x = 0;
		int max_pos_y = 0;
		for ( int x=0; x<width; x++){
			for ( int y=0; y<height; y++){
				if (grid[y][x] > grid[max_pos_y][max_pos_x]){
					max_pos_x = x;
					max_pos_y = y;
				}
			}
		}
		int[] _x = {0,1,1,-1,  0,-1,-1,1};
		int[] _y = {1,0,1,1 ,  -1,0,-1,-1};
		for ( int player : new int[]{PLAYER_BLACK, PLAYER_WHITE}) {
			
//			for(int x=0; x<width; x++){
//				for ( int y=0; y<height; y++) {
					
					
					int start_x = max_pos_x;
					int start_y = max_pos_y;
					for (int direction=0; direction<_x.length; direction++){
//						System.out.println("direction = "+direction);
						boolean is_winner = true;
						for (int i=0; i<5; i++) {
							int curr_x = start_x + i*_x[direction];
							int curr_y = start_y + i*_y[direction];
							if (  !(curr_x>=0 && curr_x<width &&
									curr_y>=0 && curr_y<height &&
									grid[curr_y][curr_x] != 0 &&
									grid[curr_y][curr_x]%2==player)
							   ) {
//								System.out.println(curr_x + ", " + curr_y);
								is_winner = false;
							}else{
//								System.out.print(curr_x + ", " + curr_y+"|| ");
							}
						}
						if ( is_winner ) {
							return player;
						}
					}
					
//					System.out.printf("(%d, %d) is_winner = %b\n", start_x, start_y, is_winner);
					
					
//				}
//			}
			
		}
		
		
		return PLAYER_NONE;
	}
}
