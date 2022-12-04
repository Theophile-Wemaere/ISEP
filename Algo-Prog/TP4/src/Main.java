import com.isep.rpg.Game;
import com.isep.utils.*;

public class Main
{
    public static void main(String [] args)
    {
        // Handle keyboard interrupt, for exemple Ctrl + C                                                                                                    
        Runtime.getRuntime().addShutdownHook(new Thread()                                                                                   
        {                                                                                                                                   
            @Override                                                                                                                       
            public void run()                                                                                                               
            {                                                                                                                               
                System.out.println("\n\nUser interrupt detected\n");                                                                          
            }                                                                                                                               
        });

        InputParser parser = new ConsoleParser();
        Game game = new Game(parser);
        game.start();
    }
}