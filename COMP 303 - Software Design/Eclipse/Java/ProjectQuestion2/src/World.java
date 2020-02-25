import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;

public class World {
    private Item[][] world;
    private ArrayList<Item> autonomousItems;
    private int maxX, maxY;
    static private JFrame window;
    private JPanel container;

    World(int width, int height) {
        if (width < 1 || height < 1)
            throw new IllegalArgumentException("Width and height must be at least 1.");

        this.maxX = width - 1;
        this.maxY = height - 1;

        world = new Item[width][height];

        autonomousItems = new ArrayList<>();

        window = new JFrame("World");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setMinimumSize(new Dimension(800, 800));
        container = new JPanel(new GridLayout(width, height));
        window.add(container);
        //window.setVisible(true);
    }

    public void step() {
        int nextStep;
        int x, y;

        for (Item item: autonomousItems) {
            x = item.getX();
            y = item.getY();

            nextStep = item.getDirection( getPossibleDirs(Item.DIRECTION_MASK, x, y) );

            nextStep = bump(nextStep, x, y);

            item.setNextStep(nextStep);

            item.step();

            updateMap(nextStep, x, y);
        }
    }

    public void display() {
        container.removeAll();
        for ( int x = 0; x <= maxX; x++ ) {
            for ( int y = 0; y <= maxY; y++ ) {
                Item item = world[x][y];
                JLabel label = new JLabel();
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setFont(new Font("Arial", Font.BOLD, 30));

                if(item != null) {
                    label.setText(String.valueOf(item.getToken()));
                }

                container.add(label);
            }
        }
        window.pack();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void add(Item item, int x, int y) throws IllegalAccessException {
        if ( x < 0 || x > maxX || y < 0 || y > maxY )
            throw new IllegalArgumentException("Position provided is out of this world.");

        else if ( world[x][y] != null )
            throw new IllegalAccessException("Position already occupied!");

        world[x][y] = item;

        if (item instanceof Autonomous)
            autonomousItems.add(item);

        item.setX(x);
        item.setY(y);
    }

    // NEED TO ADAPT FOR MOVEABLE
    private int getPossibleDirs(int directions, final int x, final int y) {
        int possibleDirs = directions;
        //Item curItem = world[x][y];

        if (y + 1 > maxY || world[x][y+1] instanceof Immovable)
            possibleDirs &= ~Item.DIRECTION_UP;

        if (x + 1 > maxX || world[x+1][y] instanceof Immovable)
            possibleDirs &= ~Item.DIRECTION_RIGHT;

        if (y - 1 < 0 || world[x][y-1] instanceof Immovable)
            possibleDirs &= ~Item.DIRECTION_DOWN;

        if (x - 1 < 0 || world[x-1][y] instanceof Immovable)
            possibleDirs &= ~Item.DIRECTION_LEFT;

        /*if (y + 1 > maxY ||
                (curItem instanceof Autonomous && (world[x][y+1] instanceof Autonomous || world[x][y+1] instanceof Immovable)) ||
                (curItem instanceof Moveable && world[x][y+1] != null))
            possibleDirs &= ~Item.DIRECTION_UP;

        if (x + 1 > maxX ||
                (curItem instanceof Autonomous && (world[x+1][y] instanceof Autonomous || world[x+1][y] instanceof Immovable)) ||
                (curItem instanceof Moveable && world[x+1][y] != null))
            possibleDirs &= ~Item.DIRECTION_RIGHT;

        if (y - 1 < 0 ||
                (curItem instanceof Autonomous && (world[x][y-1] instanceof Autonomous || world[x][y-1] instanceof Immovable)) ||
                (curItem instanceof Moveable && world[x][y-1] != null))
            possibleDirs &= ~Item.DIRECTION_DOWN;

        if (x - 1 < 0 ||
                (curItem instanceof Autonomous && (world[x-1][y] instanceof Autonomous || world[x-1][y] instanceof Immovable)) ||
                (curItem instanceof Moveable && world[x-1][y] != null))
            possibleDirs &= ~Item.DIRECTION_LEFT;*/

        return possibleDirs;
    }

    // OUT OF BOUNDS TO HANDLE
    private int bump(int nextStep, final int x, final int y) {
        Item nextItem;
        int nextX = x;
        int nextY = y;

        switch (nextStep) {
            case Item.DIRECTION_UP: nextY++; break;
            case Item.DIRECTION_LEFT: nextX--; break;
            case Item.DIRECTION_DOWN: nextY--; break;
            case Item.DIRECTION_RIGHT: nextX++; break;
        }

        if (nextX < 0 || nextX > maxX || nextY < 0 || nextY > maxY)
            return Item.NO_DIRECTION;

        else
            nextItem = world[nextX][nextY];

        if (nextItem == null)
            return nextStep;

        else if (nextItem instanceof Autonomous || nextItem instanceof Moveable){
            nextStep = getPossibleDirs(nextStep, nextX, nextY);
            
            if (nextStep == Item.NO_DIRECTION)
            	return nextStep;
            
            else {
            	nextStep = bump(nextStep, nextX, nextY);

            	nextItem.step(nextStep);

            	updateMap(nextStep, nextX, nextY);

            	return nextStep;
            }
        }

        else
            return Item.NO_DIRECTION;
    }

    private void updateMap(int nextStep, int x, int y) {
        switch (nextStep) {
            case Item.DIRECTION_UP:
                world[x][y + 1] = world[x][y];
                world[x][y] = null;
                break;

            case Item.DIRECTION_LEFT:
                world[x - 1][y] = world[x][y];
                world[x][y] = null;
                break;

            case Item.DIRECTION_DOWN:
                world[x][y - 1] = world[x][y];
                world[x][y] = null;
                break;

            case Item.DIRECTION_RIGHT:
                world[x + 1][y] = world[x][y];
                world[x][y] = null;
                break;
        }
    }
}
