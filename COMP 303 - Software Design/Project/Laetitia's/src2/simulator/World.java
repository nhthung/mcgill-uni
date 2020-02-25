package simulator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import simulator.Movable.Direction;


/*------------------------------------------------------------------------------
	World
--------------------------------------------------------------------------------*/

/**
World, keep track of each items position on a map and generate movements.

@author		Laetitia Fesselier
@since		April, 9th 2018
@version	1.0
*/
public class World implements Iterable<Item> {
	Item map[][];
	int width, height;
	JFrame window;
	
	ArrayList<Autonomous> aItems = new ArrayList<>();
	ItemIterator it;
	
	/*------------------------------------------------------------------------------
		World
	--------------------------------------------------------------------------------*/
	
	/**
	Constructor. Throws exception if the size of the map is negative.
	
	@param		int width, int height
	@throws		IllegalArgumentException
	@author		Laetitia Fesselier
	*/
	World(int width, int height) {
		if(width < 0 || height < 0 ) throw new IllegalArgumentException("The size of the map cannot be negative.");
		map = new Item[width][height];

		this.width = width;
		this.height = height;

		window = new JFrame("World");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setMinimumSize(new Dimension(800, 800));
		window.setVisible(true);
		
		it = iterator();
	}
	
	/*------------------------------------------------------------------------------
		step
	--------------------------------------------------------------------------------*/
	
	/**
	Render each frame of the simulation. Call Autonomous.step() to select a random movement and solveCollisions.
	When a movement has been selected (ar no movement possible), 
	resetDirections reset the autonomous list of possible directions in which it picks randomly one at each tries.
	
	@author		Laetitia Fesselier
	*/
	public void step() {
		for(int i = 0; i < aItems.size(); i++) {
			
			boolean isPosValid = false;
			int attemptsCount = 0;
			Autonomous auto = aItems.get(i);
			
			while(!isPosValid && attemptsCount < 4) {
				auto.step();			
				isPosValid = solveCollisions(auto);
				attemptsCount++;
			}
			auto.resetDirections();
		}
		display();
	}
	
	/*------------------------------------------------------------------------------
		update
	--------------------------------------------------------------------------------*/
	
	/**
	Called by any item which position has been updated to update its position on the map.
	
	@param		item Item
	@author		Laetitia Fesselier
	*/
	public void update(Item item) {
		int x = item.getX();
		int y = item.getY();
		int xPrev = item.getXPrev();
		int yPrev = item.getYPrev();
		
		map[x][y] = item;
		map[xPrev][yPrev] = null;
	}
	
	/*------------------------------------------------------------------------------
		solveCollisions
	--------------------------------------------------------------------------------*/
	
	/**
	Called by step to solve possible collisions after Autonomous random movements.
	
	@param		Autonomous auto containing the new position
	@return 	boolean indicate success
	@author		Laetitia Fesselier
	*/
	private boolean solveCollisions(Autonomous auto) {		
		int xNext = auto.getXNext();
		int yNext = auto.getYNext();
		Direction direction = auto.getDirection();
		
		if(!isValidPosition(xNext, yNext)) return false;
		
		// If no collision return true
		if(map[xNext][yNext] == null) {
			auto.move(direction);
			return true;
		}
		
		try {
			// Solve collisions
			boolean hasCollision = true;
			it.setDirection(direction);
			it.moveTo(auto);
			
			// Find a grid space
			boolean hasSpace = false;
			while(it.hasNext()) {
				Item nextItem = it.next();
				if(nextItem == null) {
					hasSpace = true;
					break;
				}
				if(!(nextItem instanceof Movable)) return false;
			}
			if(!hasSpace) return false;
		
			// Reverse direction and go backward
			Direction oppositeDir = Direction.values()[(direction.ordinal()+2)%4];
			it.setDirection(oppositeDir);
			
			Movable prevItem;
			while(hasCollision && it.hasNext()) {	
				// Get previous item and shift it
				prevItem = (Movable)it.next(); 
				prevItem.move(direction);			
				if(prevItem == auto) hasCollision = false;
			}
			
			return true;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	/*------------------------------------------------------------------------------
		Getters/Setters
	--------------------------------------------------------------------------------*/
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	private Item getItem(int x, int y) {
		if(!isValidPosition(x, y)) throw new IllegalArgumentException("Your position is outside the map.");
		return map[x][y];
	}
	
	/*------------------------------------------------------------------------------
		close
	--------------------------------------------------------------------------------*/
	
	/**
	Close the simulation
	
	@author		Laetitia Fesselier
	*/
	public void close() {
		window.setVisible(false);
		window.dispose();
	} 
	
	/*------------------------------------------------------------------------------
		display
	--------------------------------------------------------------------------------*/
	
	/**
	Render the simulation
	
	@author		Laetitia Fesselier
	*/
	public void display() {
		JPanel container = new JPanel(new GridLayout(getHeight(), getWidth()));
		for(int y = 0; y < getHeight(); y++) {
			for(int x = 0; x < getWidth(); x++) {
				try {
					Item item = getItem(x, y);
					JLabel label = new JLabel();
					label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					label.setHorizontalAlignment(JLabel.CENTER);
					label.setFont(new Font("Arial", Font.BOLD, 30));
					
					if(item != null) {
						label.setText(String.valueOf(item.getToken()));
					}
					
					if(item instanceof Autonomous) {
						Autonomous auto = (Autonomous) item;
						label.setBackground(auto.getColor());
						label.setOpaque(true);
					}
					container.add(label);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
		window.setContentPane(container);
		window.revalidate();
				
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*------------------------------------------------------------------------------
		add
	--------------------------------------------------------------------------------*/
	
	/**
	Add an item on the map
	@param		item Item
	
	@author		Laetitia Fesselier
	*/
	// x, y redundant because already filled in item
	public void add(Item item) {
		int x = item.getX();
		int y = item.getY();
		
		if(!isValidPosition(x, y)) throw new IllegalArgumentException("Your position is outside the map.");
		if(getItem(x, y) != null) throw new IllegalArgumentException("Your position is already filled by an item.");
		
		item.setWorld(this);
		map[x][y] = item;
		
		if(item instanceof Autonomous) {
			aItems.add((Autonomous)item);
		}
	}
	
	/*------------------------------------------------------------------------------
		isValidPosition
	--------------------------------------------------------------------------------*/
	
	/**
	Validate the Item position on the map.False if negative or outside the bounds of the map.
	@param		x int x, y int
	@return 	boolean
	
	@author		Laetitia Fesselier
	*/
	private boolean isValidPosition(int x, int y) {
		if(x >= 0 && x < width && y >= 0 && y < height) {
			return true;
		}
		return false;
	}
	
	/*------------------------------------------------------------------------------
		iterator
	--------------------------------------------------------------------------------*/
	
	/**
	Iterator
	@return 	ItemIterator
	
	@author		Laetitia Fesselier
	*/
	@Override
	public ItemIterator iterator() {
	  return new ItemIterator();
	}
		
	private class ItemIterator implements Iterator<Item> {
		private int x, y;
		private Direction direction;
		 
		public ItemIterator() {
		 	x = 0;
		 	y = 0;
		 	direction = Direction.RIGHT;
		}
		 
		public void setDirection(Direction dir) {
			direction = dir;
		}
		
		public void moveTo(int x, int y) {			
			if(!isValidPosition(x, y)) throw new IllegalArgumentException("Your position is outside the map.");
		    this.x = x;
		    this.y = y;
		}
		
		public void moveTo(Item item) {
			int x = item.getX();
			int y = item.getY();
			moveTo(x, y);
		}
		
		@Override
		public boolean hasNext() {			
			switch (direction) {
			case UP:
				if(y < 1) return false;
				break;
				
			case RIGHT:
				if(x+1 >= getWidth()) return false;
				break;
				
			case DOWN:
				if(y+1 >= getHeight()) return false;
				break;
				
			case LEFT:
				if(x < 1) return false;
			}
			return true;
		}

		@Override
		public Item next() {
			if(!hasNext()) throw new NoSuchElementException("Your position is outside the map.");
			
			switch (direction) {
			case UP:
				moveTo(x, y-1);
				break;
				
			case RIGHT:
				moveTo(x+1, y);
				break;
				
			case DOWN:
				moveTo(x, y+1);
				break;
				
			case LEFT:
				moveTo(x-1, y);
			}
						
			return getItem(x, y);
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}