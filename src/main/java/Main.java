import java.util.Scanner;

public class Main
{

    public static void main(String[] args) 
    {
        Scanner keyboard = new Scanner(System.in);
        FileSystem fs = new FileSystem();

        while (true)
        {

            System.out.print("$ ");
            String choice = keyboard.nextLine ();
            String [] commands = choice.split (" ");
            String message;
            switch (commands[0]) 
            {
                case "ls":
                    message = fs.ls();
                    break;

                case "pwd":
                    message = fs.pwd();
                    break;

                case "mkdir":
                    if (commands.length == 2)
                        message = fs.mkdir(commands[1]);
                    else
                        message = "improper command";
                    break;

                case "touch":
                    if (commands.length == 2)
                        message = fs.touch(commands[1]);
                    else
                        message = "improper command";
                    break;
                case "mv":
                    if (commands.length == 3)
                        message =fs.mv(commands[1], commands[2]);
                    else
                    message = "improper command";
                    break;
                
                case "cd":
                    if (commands.length == 2)
                        message = fs.cd(commands[1]);
                    else
                        message = "improper command";
                    break;

                case "rm":
                    if (commands.length == 2)
                        message = fs.rm(commands[1]);
                    else
                        message = "improper command";
                    break;

                case "exit":
                    keyboard.close();
                    return;

                default:
                    message = "unknown command";
                    break;
            }
            System.out.println (message);
        }
        
    }
}
