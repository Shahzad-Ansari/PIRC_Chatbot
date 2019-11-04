
public class MyBotMain {
    
    public static void main(String[] args) throws Exception {
        
        // Now start our bot up.
        ChatBot bot = new ChatBot();
        
        // Enable debugging output.
        bot.setVerbose(true);
        
        // Connect to the IRC server.
        bot.connect("irc.freenode.net");

        // Join the #pircbot channel.
        bot.joinChannel("#SHAHTEST");
     
        
        bot.sendMessage("#SHAHTEST", "  Acceptable commands are: help, rates, time, iss and weather>city name");
        
    }
    
}
