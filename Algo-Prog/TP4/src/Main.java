import com.isep.rpg.Game;
import com.isep.utils.*;

public class Main
{
    public static void main(String [] args)
    {
        // Handle user interrupt, for exemple Ctrl + C                                                                                                    
        Runtime.getRuntime().addShutdownHook(new Thread()                                                                                   
        {                                                                                                                                   
            @Override                                                                                                                       
            public void run()                                                                                                               
            {                                                                                                                               
                System.out.println("\n\n\u001B[31m" + "/!\\ Interrupt detected /!\\\n" + "\u001B[0m");                                                                          
            }                                                                                                                               
        });
        
        InputParser parser = new ConsoleParser();
        Game game = new Game(parser);
        game.start();
    }
}