import javax.swing.*;
import java.awt.*;

public class WindowTray {
    JFrame window;
    int counter = 0;

    public static void main(String[] args) {
        new WindowTray();
    }

    public WindowTray(){
        //setup window size
        window = new JFrame();
        window.setSize(400,400);
        //set up the logo on title bar
        window.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("LPlogo.png")));

        //create a button
        JButton button = new JButton("0");
        //set the text in button
        button.setFont(new Font("Arial", Font.PLAIN,50));
        //add action listener to check if the button being clicked
        button.addActionListener(actionEvent -> {
            counter++;
            button.setText(String.valueOf(counter));
        });
        //add button to window
        window.add(button);
        //make the window is visible to customer
        window.setVisible(true);
        //check if the system tray is allowing
        if(SystemTray.isSupported()){
            window.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        }
        //initialize a system tray
        SystemTray tray = SystemTray.getSystemTray();
        //set up the icon on the tray
        TrayIcon icon = new TrayIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("LPlogo.png")));
        /* initialize a popup menu for right-click on the system tray icon */
        PopupMenu popupMenu = new PopupMenu();

        //initialize an option ==> show
        MenuItem show = new MenuItem("Show");
        //add listener to detect when clicked
        show.addActionListener(actionEvent -> window.setVisible(true));

        //next two same as previous two
        MenuItem exit = new MenuItem("Exit");
        exit.addActionListener(actionEvent -> System.exit(0));

        //add option to popup menu
        popupMenu.add(show);
        popupMenu.add(exit);

        //add the menu to icon
        icon.setPopupMenu(popupMenu);

        //add icon to the system tray
        try {
            tray.add(icon);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

    }
}